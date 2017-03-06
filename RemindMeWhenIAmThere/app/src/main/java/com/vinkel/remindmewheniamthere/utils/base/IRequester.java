package com.vinkel.remindmewheniamthere.utils.base;


import android.support.annotation.Nullable;
import com.vinkel.remindmewheniamthere.providers.base.IHttpResponse;
import io.reactivex.Observable;
import java.util.Map;

public interface IRequester {

  Observable<IHttpResponse> postJson(String url, String body, Map<String, String> headers);

  Observable<IHttpResponse> putJson(final String url, @Nullable final String jsonBody, final Map<String, String> headers);

  Observable<IHttpResponse> getJson(final String url, final Map<String, String> headers);
}
