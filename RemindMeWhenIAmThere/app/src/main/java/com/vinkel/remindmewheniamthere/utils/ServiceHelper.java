package com.vinkel.remindmewheniamthere.utils;

import android.app.ActivityManager;
import android.app.Service;
import com.vinkel.remindmewheniamthere.utils.base.IServiceHelper;

public class ServiceHelper implements IServiceHelper {

  private final ActivityManager activityManager;

  public ServiceHelper(ActivityManager activityManager) {
    this.activityManager = activityManager;
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
}
