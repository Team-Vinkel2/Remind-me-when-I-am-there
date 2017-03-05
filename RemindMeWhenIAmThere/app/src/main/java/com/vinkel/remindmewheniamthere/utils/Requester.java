package com.vinkel.remindmewheniamthere.utils;


import com.vinkel.remindmewheniamthere.providers.base.IHttpResponse;
import com.vinkel.remindmewheniamthere.providers.base.IHttpResponseFactory;
import com.vinkel.remindmewheniamthere.utils.base.IRequster;

import java.io.IOException;
import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Requester implements IRequster{

    private final IHttpResponseFactory responseFactory;
    private final OkHttpClient httpClient;

    @Inject
    public Requester(IHttpResponseFactory responseFactory) {
        this.responseFactory = responseFactory;
        this.httpClient = new OkHttpClient();
    }

    private Observable<IHttpResponse> createResponse(Request request) {
        try {
            Response response = httpClient.newCall(request).execute();

            IHttpResponse responseCreated = responseFactory.createResponse(
                    response.headers().toMultimap(),
                    response.body().string(),
                    response.message(),
                    response.code());

            return Observable.just(responseCreated);
        } catch (IOException exception) {
            return Observable.error(exception);
        }
    }

    private RequestBody createBody(Map<String, String> bodyMap) {
        FormBody.Builder bodyBuilder = new FormBody.Builder();

        for (Map.Entry<String, String> set : bodyMap.entrySet()) {
            bodyBuilder.add(set.getKey(), set.getValue());
        }

        RequestBody requestBody = bodyBuilder.build();
        return requestBody;
    }

}
