package com.vinkel.remindmewheniamthere.utils;


import android.support.annotation.Nullable;
import com.vinkel.remindmewheniamthere.providers.base.IHttpResponse;
import com.vinkel.remindmewheniamthere.providers.base.IHttpResponseFactory;
import com.vinkel.remindmewheniamthere.utils.base.IRequester;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import java.util.Map;
import javax.inject.Inject;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Requester implements IRequester {

  public static final MediaType JSON
      = MediaType.parse("application/json");

  private final IHttpResponseFactory responseFactory;
  private final OkHttpClient httpClient;

  @Inject
  public Requester(IHttpResponseFactory responseFactory) {
    this.responseFactory = responseFactory;
    this.httpClient = new OkHttpClient();
  }

  @Override
  public Observable<IHttpResponse> postJson(final String url, final String jsonBody, final Map<String, String> headers) {
    return Observable.create(new ObservableOnSubscribe<IHttpResponse>() {
      @Override
      public void subscribe(ObservableEmitter<IHttpResponse> e) throws Exception {
        RequestBody body = createRequestBodyJson(jsonBody);
        Request request = createBasicRequestBuiler(url, headers)
            .post(body)
            .build();

        Response response = httpClient.newCall(request).execute();

        IHttpResponse responseCreated = responseFactory.createResponse(
            response.headers().toMultimap(),
            response.body().string(),
            response.message(),
            response.code()
        );

        e.onNext(responseCreated);
      }
    });
  }

  @Override
  public Observable<IHttpResponse> getJson(final String url, final Map<String, String> headers) {
    return Observable.create(new ObservableOnSubscribe<IHttpResponse>() {
      @Override
      public void subscribe(ObservableEmitter<IHttpResponse> e) throws Exception {
        Request request = createBasicRequestBuiler(url, headers)
            .get()
            .build();

        Response response = httpClient.newCall(request).execute();

        IHttpResponse responseCreated = responseFactory.createResponse(
            response.headers().toMultimap(),
            response.body().string(),
            response.message(),
            response.code()
        );

        e.onNext(responseCreated);
      }
    });
  }

  @Override
  public Observable<IHttpResponse> putJson(final String url, @Nullable final String jsonBody, final Map<String, String> headers) {
    return Observable.create(new ObservableOnSubscribe<IHttpResponse>() {
      @Override
      public void subscribe(ObservableEmitter<IHttpResponse> e) throws Exception {
        RequestBody body;

        if (jsonBody == null) {
          body = createRequestBodyJson("");
        } else {
          body = createRequestBodyJson(jsonBody);
        }

        Request request = createBasicRequestBuiler(url, headers)
            .put(body)
            .build();

        Response response = httpClient.newCall(request).execute();

        IHttpResponse responseCreated = responseFactory.createResponse(
            response.headers().toMultimap(),
            response.body().string(),
            response.message(),
            response.code()
        );

        e.onNext(responseCreated);
      }
    });
  }

  private RequestBody createRequestBodyJson(String json) {
    return RequestBody.create(JSON, json);
  }

  private Request.Builder createBasicRequestBuiler(String url, Map<String, String> headers) {
    Request.Builder builder = new Request.Builder();
    builder.url(url);

    if (headers != null) {
      for (Map.Entry<String, String> kvp: headers.entrySet()) {
        builder.addHeader(kvp.getKey(), kvp.getValue());
      }
    }
    return builder;
  }
}
