package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.ServiceElement;

/**
 * Created by barlow on 2/2/16.
 */
public class ConnectService {
  private final ServiceElement rootService;
  private final String packageName;

  public ConnectService(ServiceElement rootService, String packageName) {
    this.rootService = rootService;
    this.packageName = packageName;
  }

  public ServiceElement getRootService() {
    return this.rootService;
  }
}
