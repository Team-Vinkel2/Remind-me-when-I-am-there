package com.vinkel.remindmewheniamthere.utils.base;

import android.app.Service;

public interface IServiceHelper {
  boolean isServiceRunning(Class<? extends Service> serviceClass);

  void checkStartService(Class<? extends Service> serviceClass);
}
