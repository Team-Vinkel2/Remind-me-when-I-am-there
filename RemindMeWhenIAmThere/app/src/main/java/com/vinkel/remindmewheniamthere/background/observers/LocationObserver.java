package com.vinkel.remindmewheniamthere.background.observers;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.vinkel.remindmewheniamthere.background.observers.base.ILocationObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Action;
import java.util.TreeMap;
import javax.inject.Inject;

public class LocationObserver implements ILocationObserver {
  private static final String TAG = "VinkelLocationObserver";
  private TreeMap<String, LocationListener> activeLocationListeners;
  private final LocationManager locationManager;

  @Inject
  public LocationObserver(LocationManager locationManager) {
    this.locationManager = locationManager;
    this.activeLocationListeners = new TreeMap<>();
  }

  public Observable<Location> getLocationObserver(final long minUpdateTime, final long minUpdateDistance, final String observerTag) {
    return Observable.create(new ObservableOnSubscribe<Location>() {
      @Override
      public void subscribe(final ObservableEmitter<Location> e) throws Exception {
        if (activeLocationListeners.containsKey(observerTag)) {
          e.onError(new IllegalArgumentException("You've created observable for with this tag"));
        } else {

          LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
              e.onNext(location);
            }
          };

          activeLocationListeners.put(observerTag, listener);

          try {
            locationManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, minUpdateTime, minUpdateDistance,
                listener, Looper.getMainLooper());
          } catch (java.lang.SecurityException ex) {
            Log.i(TAG, "failed to request location update, ignore", ex);
          } catch (IllegalArgumentException ex) {
            Log.d(TAG, "network provider does not exist, " + ex.getMessage());
          }
        }
      }
    }).doOnDispose(new Action() {
      @Override
      public void run() throws Exception {
        if (!activeLocationListeners.containsKey(observerTag)) {
          return;
        }

        try {
          locationManager.removeUpdates(activeLocationListeners.get(observerTag));
        } catch (SecurityException ex) {
          Log.i(TAG, "failed to remove location listners due to security exception", ex);
        } catch (Exception ex) {
          Log.i(TAG, "failed to remove location listners, ignore", ex);
        }

      }
    });
  }

  public Observable<Location> getLocationOnce() {
    return Observable.create(new ObservableOnSubscribe<Location>() {
      @Override
      public void subscribe(final ObservableEmitter<Location> e) throws Exception {
        Log.d(TAG, "subscribed");
        LocationListener listener = new LocationListener() {

          @Override
          public void onLocationChanged(Location location) {
            Log.d(TAG, "location" + location);
              e.onNext(location);

              try {
                locationManager.removeUpdates(this);
              } catch (SecurityException ex) {
                Log.i(TAG, "failed to remove location listners due to security exception", ex);
              } catch (Exception ex) {
                Log.i(TAG, "failed to remove location listners, ignore", ex);
              }
          }
        };

        try {
          locationManager.requestLocationUpdates(
              LocationManager.GPS_PROVIDER, 0, 0,
              listener, Looper.getMainLooper());
        } catch (java.lang.SecurityException ex) {
          Log.i(TAG, "fail to request location update, ignore", ex);
        } catch (IllegalArgumentException ex) {
          Log.d(TAG, "gps provider does not exist, " + ex.getMessage());
        }
      }
    });
  }

  private abstract class LocationListener implements android.location.LocationListener {

    boolean gpsEnabled = false;

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
      if (provider.equals(LocationManager.GPS_PROVIDER)) {
        this.gpsEnabled = true;
      }
    }

    @Override
    public void onProviderDisabled(String provider) {
      if (provider.equals(LocationManager.GPS_PROVIDER)) {
        this.gpsEnabled = false;
      }


      if (!gpsEnabled) {
        // Sent notification to user "Please turn on location services"
      }
    }
  }

}