package com.squareup.apiparser;

import com.google.gson.JsonObject;

/**
 * Created by barlow on 8/31/15.
 */
public interface APIParser {
  JsonObject parseAPI(ProtoIndex index);
}
