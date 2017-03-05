package com.vinkel.remindmewheniamthere.providers.base;


import java.util.List;
import java.util.Map;

public interface IHttpResponseFactory {

    IHttpResponse createResponse(
            Map<String, List<String>> headers,
            String body,
            String message,
            int code);
}
