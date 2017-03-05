package com.vinkel.remindmewheniamthere.utils.base;


import com.vinkel.remindmewheniamthere.providers.base.IHttpResponse;

import java.util.Map;

import io.reactivex.Observable;

public interface IRequster {
    Observable<IHttpResponse> post(final String url, final Map<String, String> body);
}
