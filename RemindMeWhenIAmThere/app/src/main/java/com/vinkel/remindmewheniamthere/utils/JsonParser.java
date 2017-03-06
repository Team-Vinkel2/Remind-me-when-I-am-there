package com.vinkel.remindmewheniamthere.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.vinkel.remindmewheniamthere.utils.base.IJsonParser;

import java.lang.reflect.Type;

public class JsonParser implements IJsonParser {
  @Override
  public String toJson(Object src) {
    Gson gson = new Gson();
    String json = gson.toJson(src);

    return json;
  }

  @Override
  public <T> T fromJson(String json, Type classOfT) {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    T result = gson.fromJson(json, classOfT);

    return result;
  }

  @Override
  public <T> T[] fromJsonArray(String json, Type arrayClassOfT) {
    GsonBuilder builder = new GsonBuilder();
    Gson gson = builder.create();
    T[] result = gson.fromJson(json, arrayClassOfT);

    return result;
  }

  @Override
  public String getDirectMember(String json, String memberName) {
    com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
    JsonObject parent = jsonParser
        .parse(json)
        .getAsJsonObject();

    JsonObject member = parent.getAsJsonObject(memberName);

    return member.toString();
  }

  public String getDirectSimpleMember(String json, String memberName) {
    com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
    JsonObject parent = jsonParser
        .parse(json)
        .getAsJsonObject();

    JsonPrimitive primitive = parent.getAsJsonPrimitive(memberName);
    return primitive.toString();
  }

}
