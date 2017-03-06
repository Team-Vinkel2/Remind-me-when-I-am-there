package com.vinkel.remindmewheniamthere.network.base;

import com.vinkel.remindmewheniamthere.config.base.IApiConfig;
import com.vinkel.remindmewheniamthere.utils.base.IJsonParser;
import com.vinkel.remindmewheniamthere.utils.base.IRequester;

public abstract class BaseData<T> {
  protected final IRequester httpRequester;
  protected final IApiConfig apiConfig;
  protected final IJsonParser jsonParser;
  protected final Class<? extends T> classSingle;
  protected final Class<? extends T[]> classArray;

  protected BaseData(
      IRequester httpRequester,
      IApiConfig apiConfig,
      IJsonParser jsonParser,
      Class<? extends T> classSingle,
      Class<? extends T[]> classArray) {
    this.httpRequester = httpRequester;
    this.apiConfig = apiConfig;
    this.jsonParser = jsonParser;
    this.classSingle = classSingle;
    this.classArray = classArray;
  }

  protected String parseApiError(String json) {
    String errorJson = jsonParser.getDirectMember(json, apiConfig.getApiErrorJsonKey());
    String errorMessage = jsonParser.getDirectSimpleMember(errorJson, apiConfig.getApiErrorMessageKey());
    return errorMessage;
  }
}
