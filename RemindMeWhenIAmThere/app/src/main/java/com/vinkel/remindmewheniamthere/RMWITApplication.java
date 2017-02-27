package com.vinkel.remindmewheniamthere;

import android.app.Application;


@SuppressWarnings("CheckStyle")
public class RMWITApplication extends Application {
  private static RMWITApplication instance;

  private AppComponent component;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    this.component = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
    component.inject(this);
  }

  public static RMWITApplication getInstance() {
    return instance;
  }

  public AppComponent component() {
    return component;
  }
}
