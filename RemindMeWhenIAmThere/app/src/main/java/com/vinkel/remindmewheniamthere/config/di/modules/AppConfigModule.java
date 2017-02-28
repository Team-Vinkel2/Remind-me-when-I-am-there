package com.vinkel.remindmewheniamthere.config.di.modules;

import android.Manifest;
import android.os.Build;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

@Module
public class AppConfigModule {
  @Provides
  @Named("requestedAppPermissions")
  String[] provideRequestedAppPermissions(){
    return new String[]{
        Manifest.permission.ACCESS_COARSE_LOCATION,
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
  }

  @Provides
  @Named("sdkVersion")
  int provideBuildNumber() {
    return Build.VERSION.SDK_INT;
  }

  @Provides
  @Named("isRunningOnMarshmallow")
  boolean provideIsRunningOnMarshmallow() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
  }
}
