package com.squareup.apiparser;


import com.squareup.wire.schema.Location;
import com.squareup.wire.schema.internal.parser.ProtoParser;
import com.squareup.wire.schema.internal.parser.ProtoFileElement;
import com.squareup.wire.schema.internal.parser.ServiceElement;
import com.squareup.wire.schema.internal.parser.TypeElement;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okio.Okio;

public class ProtoIndexer {

  private List<ProtoFileElement> protoFiles;


  public ProtoIndexer() {
    protoFiles = new ArrayList<ProtoFileElement>();
  }

  public static ProtoIndex indexProtos(String[] protoPaths) {

    ProtoIndex index = new ProtoIndex();

    for (String path : protoPaths) {
      File file = new File(path);
      try {
        if (file.getCanonicalPath().endsWith(".proto")) {
          addProtoFile(file, index);
        } else if (file.isDirectory()) {
          recursivelyAddProtos(file, index);
        } else {
          continue;
        }
      } catch (IOException e) {

      }
    }
    return index;
  }

  private static void recursivelyAddProtos(File directory, ProtoIndex index) {
    File[] children = directory.listFiles();
    for (File child : children) {
      try {
        if (child.isDirectory()) {
          recursivelyAddProtos(child, index);
        } else if (child.getCanonicalPath().endsWith(".proto")) {
          addProtoFile(child, index);
        } else {
          continue;
        }
      } catch (IOException e) {

      }
    }
  }

  private static void addProtoFile(File file, ProtoIndex index) {
    try {
      ProtoFileElement protoFile = ProtoParser.parse(Location.get(file.getCanonicalPath()),
        Okio.buffer(Okio.source(file)).readUtf8());

      for (TypeElement datatype : protoFile.types()) {
        index.addDatatype(datatype, protoFile.packageName());
      }

      for (ServiceElement service : protoFile.services()) {
        index.addService(service, protoFile.packageName());
      }
    } catch (IOException e) {

    }
  }
}
