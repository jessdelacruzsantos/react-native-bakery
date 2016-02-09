package com.squareup.apiparser;

import com.squareup.wire.schema.Location;
import com.squareup.wire.schema.internal.parser.ProtoFileElement;
import com.squareup.wire.schema.internal.parser.ProtoParser;
import com.squareup.wire.schema.internal.parser.ServiceElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import okio.BufferedSource;
import okio.Okio;

public class ProtoIndexer {
  private List<ConnectType> protoTypes;
  private List<ConnectService> protoServices;

  public ProtoIndexer() {
    this.protoTypes = new ArrayList<>();
    this.protoServices = new ArrayList<>();
  }

  public ProtoIndex indexProtos(String[] protoPaths) {
    ProtoIndex index = new ProtoIndex();
    for (String path : protoPaths) {
      try {
        Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
          @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (Pattern.matches(".*\\.proto\\Z", file.toString())) {
              addProtoFile(file.toFile(), index);
            }
            return FileVisitResult.CONTINUE;
          }
        });
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    index.populate(this.protoTypes, this.protoServices);
    return index;
  }

  private void addProtoFile(File file, ProtoIndex index) {
    try (BufferedSource buffer = Okio.buffer(Okio.source(file))) {
      final Location l = Location.get(file.getCanonicalPath());
      final ProtoFileElement proto = ProtoParser.parse(l, buffer.readUtf8());

      for (TypeElement t : proto.types()) {
        addType(t, proto.packageName(), null);
      }

      for (ServiceElement service : proto.services()) {
        this.protoServices.add(new ConnectService(service, proto.packageName()));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void addType(TypeElement datatype, String packageName, ConnectType parent) {
    ConnectType ct = new ConnectType(datatype, packageName, Optional.ofNullable(parent));
    this.protoTypes.add(ct);
    for (TypeElement subType : datatype.nestedTypes()) {
      addType(subType, packageName, ct);
    }
  }
}
