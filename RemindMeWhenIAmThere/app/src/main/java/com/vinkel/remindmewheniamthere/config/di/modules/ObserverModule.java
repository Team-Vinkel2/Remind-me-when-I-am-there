package com.vinkel.remindmewheniamthere.config.di.modules;

import android.location.LocationManager;
import com.vinkel.remindmewheniamthere.background.observers.LocationObserver;
import com.vinkel.remindmewheniamthere.background.observers.base.ILocationObserver;
import dagger.Module;
import dagger.Provides;

@Module
public class ObserverModule {

  private ILocationObserver locationObserver;

  @Provides
  ILocationObserver provideLocationObserver(LocationManager locationManager) {
    if(this.locationObserver == null) {
      this.locationObserver = new LocationObserver(locationManager);
    }

    return this.locationObserver;
  }
}
