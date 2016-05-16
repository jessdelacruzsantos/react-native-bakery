package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
import com.squareup.wire.schema.Location;
import com.squareup.wire.schema.internal.parser.ProtoFileElement;
import com.squareup.wire.schema.internal.parser.ProtoParser;
import com.squareup.wire.schema.internal.parser.ServiceElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import okio.BufferedSource;
import okio.Okio;

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

public class ProtoIndexer {

  private static final ImmutableList<String> IGNORED_PROTOS = ImmutableList.of(
      "squareup/connect/v2/common/options.proto");

  private final List<ConnectType> protoTypes = new ArrayList<>();
  private final List<ConnectService> protoServices = new ArrayList<>();

  public ProtoIndex indexProtos(ApiReleaseType apiReleaseType, List<String> protoPaths)
      throws IOException, AnnotationException {
    final ProtoIndex index = new ProtoIndex(apiReleaseType, new ExampleResolver(protoPaths));
    for (String path : protoPaths) {
      Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
        @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
          if (shouldIgnoreProto(file.toString())) {
            return FileVisitResult.CONTINUE;
          }
          if (Pattern.matches(".*\\.proto\\Z", file.toString())) {
            addProtoFile(apiReleaseType, file.toFile());
          }
          return FileVisitResult.CONTINUE;
        }
      });
    }
    index.populate(this.protoTypes, this.protoServices);
    return index;
  }

  private void addProtoFile(ApiReleaseType apiReleaseType, File file) throws IOException {
    try (BufferedSource buffer = Okio.buffer(Okio.source(file))) {
      final Location l = Location.get(file.getCanonicalPath());
      final ProtoFileElement proto = ProtoParser.parse(l, buffer.readUtf8());

      if (!apiReleaseType.shouldInclude(proto.options(), "common.file_status")) {
        return;
      }

      proto.types().stream()
          .forEach(t -> addType(apiReleaseType, t, proto.packageName(), Optional.empty()));

      for (ServiceElement service : proto.services()) {
        this.protoServices.add(new ConnectService(service));
      }
    }
  }

  private void addType(ApiReleaseType apiReleaseType, TypeElement datatype, String packageName,
      Optional<ConnectType> parent) {
    ConnectType ct = new ConnectType(datatype, packageName, parent);
    if (!apiReleaseType.shouldInclude(ct.getReleaseStatus())) {
      return;
    }
    this.protoTypes.add(ct);
    for (TypeElement subType : datatype.nestedTypes()) {
      addType(apiReleaseType, subType, packageName, Optional.of(ct));
    }
  }

  private boolean shouldIgnoreProto(String protoFile) {
    for (String ignored : IGNORED_PROTOS) {
      if (protoFile.endsWith(ignored)) {
        return true;
      }
    }
    return false;
  }
}
