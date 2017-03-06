package com.vinkel.remindmewheniamthere.utils;

import android.app.ActivityManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import com.vinkel.remindmewheniamthere.config.di.annotations.AppContext;
import com.vinkel.remindmewheniamthere.config.di.annotations.UnscopedIntentFactory;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.base.IServiceHelper;
import javax.inject.Inject;

public class ServiceHelper implements IServiceHelper {

  private final ActivityManager activityManager;
  private final Context appContext;
  private final IIntentFactory intentFactory;

  @Inject
  public ServiceHelper(
      ActivityManager activityManager,
      @AppContext Context appContext,
      @UnscopedIntentFactory IIntentFactory intentFactory) {
    this.activityManager = activityManager;
    this.appContext = appContext;
    this.intentFactory = intentFactory;
  }


  public boolean isServiceRunning(Class<? extends Service> serviceClass) {
    for (ActivityManager.RunningServiceInfo service :
        activityManager.getRunningServices(Integer.MAX_VALUE)) {
      if (serviceClass.getName().equals(service.service.getClassName())) {
        return true;
      }
    }

    return false;
  }

  @Override
  public void checkStartService(Class<? extends Service> serviceClass) {
    if (this.isServiceRunning(serviceClass)) {
      return;
    }

    Intent serviceIntent = intentFactory.getIntent(serviceClass);
    appContext.startService(serviceIntent);
  }
}
