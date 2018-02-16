package com.squareup.apiparser;

import com.squareup.wire.schema.internal.parser.ServiceElement;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by barlow on 2/2/16.
 */
class ConnectService {
  private final ServiceElement rootService;
  private final ReleaseStatus releaseStatus;

  ConnectService(ReleaseStatus releaseStatus, ServiceElement rootService) {
    this.releaseStatus = releaseStatus;
    this.rootService = checkNotNull(rootService);
  }

  ServiceElement getRootService() {
    return this.rootService;
  }

  public ReleaseStatus getReleaseStatus() {
    return releaseStatus;
  }
}
