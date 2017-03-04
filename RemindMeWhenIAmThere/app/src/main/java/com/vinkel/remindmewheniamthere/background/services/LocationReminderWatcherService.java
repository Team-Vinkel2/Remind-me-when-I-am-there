package com.vinkel.remindmewheniamthere.background.services;

import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.background.observers.base.ILocationObserver;
import com.vinkel.remindmewheniamthere.config.di.modules.BackgroundModule;
import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;

public class LocationReminderWatcherService extends Service{
  private static final int SECOND = 1000;
  private static final int MINUTE = 60 * SECOND;
  private static final int LOCATION_CHECK_INTERVAL = 3 * MINUTE ;
  private static final int TIMER_NO_DELAY = 0;

  private Location turteiteLocation;
  private final double lat = Double.valueOf("42.607224");
  private final double lng = Double.valueOf("23.103707");

  private Intent startingIntent;
  private Disposable locationSubscription;

  @Inject
  public ILocationObserver locationObserver;

  @Inject
  public IReminderDatabase reminderDatabase;

  private Timer timer = null;

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    super.onStartCommand(intent, flags, startId);

    this.startingIntent = intent;
    return START_STICKY;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Log.e("VINKEL_COARSE_LOCATION", "Creating service");
    this.turteiteLocation = new Location("turteiteLocation");
    turteiteLocation.setLatitude(lat);
    turteiteLocation.setLongitude(lng);

    this.injectMembers();
    if (this.timer != null) {
      this.timer.cancel();
    } else {
      this.timer = new Timer();
    }

    this.timer.scheduleAtFixedRate(new LocationObserverTimerTask(), TIMER_NO_DELAY, 10 * SECOND);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();

    this.timer.cancel();

  }

  private void injectMembers() {
    RMWITApplication
        .getInstance()
        .getComponent()
        .getBackgroundComponent(new BackgroundModule())
        .inject(this);
  }


  class LocationObserverTimerTask extends TimerTask {

    @Override
    public void run() {

      if(locationSubscription != null && !locationSubscription.isDisposed()) {
        return;
      }

      Log.e("VINKEL_COURSE_LOCATION", "Repeating");
      locationSubscription = locationObserver.getLocationOnce()
          .subscribeOn(Schedulers.io())
          .observeOn(Schedulers.io())
          .subscribe(new Consumer<Location>() {
            @Override
            public void accept(Location location) throws Exception {
              Log.e("VINKEL_COARSE_LOCATION", location.distanceTo(turteiteLocation) + "metres");
              if(!locationSubscription.isDisposed()) {
                locationSubscription.dispose();
              }
            }
          })
          ;
    }
  }
}
