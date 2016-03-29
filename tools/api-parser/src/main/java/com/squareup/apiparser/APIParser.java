package com.squareup.apiparser;

/**
 * Created by barlow on 8/31/15.
 */
public interface APIParser {
  ConnectAPIParser.JsonAPI parseAPI(ProtoIndex index);
}
