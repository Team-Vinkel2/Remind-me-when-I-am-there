package com.vinkel.remindmewheniamthere.background.observers.base;

import android.location.Location;
import io.reactivex.Observable;

public interface ILocationObserver {
  Observable<Location> getLocationObserver(final long minUpdateTime, final float minUpdateDistance, final String observerTag);

  Observable<Location> getLocationOnce();
}
