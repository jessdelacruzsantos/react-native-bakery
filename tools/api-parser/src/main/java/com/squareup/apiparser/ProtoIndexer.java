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
  private List<ConnectType> protoTypes;
  private List<ConnectService> protoServices;


  public ProtoIndexer() {
    this.protoFiles = new ArrayList<ProtoFileElement>();
    this.protoTypes = new ArrayList<ConnectType>();
    this.protoServices = new ArrayList<ConnectService>();
  }

  public ProtoIndex indexProtos(String[] protoPaths) {

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
    index.populate(this.protoTypes, this.protoServices);
    return index;
  }

  private void recursivelyAddProtos(File directory, ProtoIndex index) {
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

  private void addProtoFile(File file, ProtoIndex index) {
    try {
      ProtoFileElement protoFile = ProtoParser.parse(Location.get(file.getCanonicalPath()),
        Okio.buffer(Okio.source(file)).readUtf8());

      for (TypeElement datatype : protoFile.types()) {
        addType(datatype, protoFile.packageName(), null);
      }

      for (ServiceElement service : protoFile.services()) {
        this.protoServices.add(new ConnectService(service, protoFile.packageName()));
      }
    } catch (IOException e) {

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
