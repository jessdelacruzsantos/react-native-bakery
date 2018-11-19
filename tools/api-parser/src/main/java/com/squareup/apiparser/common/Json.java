package com.squareup.apiparser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

class Json {
  static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
}
