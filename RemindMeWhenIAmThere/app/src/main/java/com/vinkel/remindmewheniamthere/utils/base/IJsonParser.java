package com.vinkel.remindmewheniamthere.utils.base;


import java.lang.reflect.Type;

public interface IJsonParser {

    String toJson(Object src);

    <T> T fromJson(String json, Type classOfT);

    String getDirectMember(String json, String memberName);
}
