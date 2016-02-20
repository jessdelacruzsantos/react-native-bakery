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
import java.util.regex.Pattern;
import okio.BufferedSource;
import okio.Okio;

public class ProtoIndexer {
  private final List<ConnectType> protoTypes = new ArrayList<>();
  private final List<ConnectService> protoServices = new ArrayList<>();

  public ProtoIndex indexProtos(String[] protoPaths) throws IOException, AnnotationException {
    final ProtoIndex index = new ProtoIndex();
    for (String path : protoPaths) {
      Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
        @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
          if (Pattern.matches(".*\\.proto\\Z", file.toString())) {
            addProtoFile(file.toFile());
          }
          return FileVisitResult.CONTINUE;
        }
      });
    }
    index.populate(this.protoTypes, this.protoServices);
    return index;
  }

  private void addProtoFile(File file) throws IOException {
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
      throw e;
    }
  }

  private void addType(TypeElement datatype, String packageName, ConnectType parent) {
    ConnectType ct = new ConnectType(datatype, packageName, parent);
    this.protoTypes.add(ct);
    for (TypeElement subType : datatype.nestedTypes()) {
      addType(subType, packageName, ct);
    }
  }
}
