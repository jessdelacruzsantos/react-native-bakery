package com.squareup.apiparser;

import org.json.JSONObject;

/**
 * Created by barlow on 8/31/15.
 */
public interface APIParser {
  JSONObject parseAPI(ProtoIndex index);
}
