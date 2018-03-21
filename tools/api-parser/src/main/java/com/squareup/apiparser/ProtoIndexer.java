package com.squareup.apiparser;

import com.google.common.collect.ImmutableList;
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

class ProtoIndexer {

  // TODO OMG-471: Remove the orders protos from this blacklist once the GO version of the protos
  // are deleted.
  private static final ImmutableList<String> IGNORED_PROTOS = ImmutableList.of(
      "squareup/connect/v2/common/options.proto",
      "squareup/connect/v2/orders/resources/order.proto",
      "squareup/connect/v2/orders/service/batch_retrieve_orders.proto",
      "squareup/connect/v2/orders/service/create_order.proto",
      "squareup/connect/v2/orders/service/orders_service.proto");

  private final List<ConnectType> protoTypes = new ArrayList<>();
  private final List<ConnectService> protoServices = new ArrayList<>();
  private final boolean ignoreOneofs;

  ProtoIndexer(boolean ignoreOneofs) {
    this.ignoreOneofs = ignoreOneofs;
  }

  ProtoIndexer() {
    this(false);
  }

  ProtoIndex indexProtos(List<String> protoPaths) throws IOException {
    final ProtoIndex index = new ProtoIndex(new ExampleResolver(protoPaths), ignoreOneofs);
    for (String path : protoPaths) {
      Files.walkFileTree(Paths.get(path), new SimpleFileVisitor<Path>() {
        @Override public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
            throws IOException {
          if (shouldIgnoreProto(file.toString())) {
            return FileVisitResult.CONTINUE;
          }
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

      ReleaseStatus releaseStatus =
          ProtoOptions.getReleaseStatus(proto.options(), "common.file_status");

      proto.types()
          .forEach(t -> addType(releaseStatus, t, proto.packageName(), Optional.empty()));

      for (ServiceElement service : proto.services()) {
        this.protoServices.add(new ConnectService(releaseStatus, service));
      }
    }
  }

  private void addType(ReleaseStatus releaseStatus, TypeElement datatype, String packageName,
      Optional<ConnectType> parent) {
    ConnectType ct = new ConnectType(releaseStatus, datatype, packageName, parent);

    this.protoTypes.add(ct);
    for (TypeElement subType : datatype.nestedTypes()) {
      addType(ct.getReleaseStatus(), subType, packageName, Optional.of(ct));
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
