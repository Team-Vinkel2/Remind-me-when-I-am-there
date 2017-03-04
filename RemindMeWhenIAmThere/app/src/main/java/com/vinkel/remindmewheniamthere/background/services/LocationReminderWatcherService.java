package com.vinkel.remindmewheniamthere.background.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.background.observers.base.ILocationObserver;
import com.vinkel.remindmewheniamthere.config.di.annotations.AppContext;
import com.vinkel.remindmewheniamthere.config.di.annotations.UnscopedIntentFactory;
import com.vinkel.remindmewheniamthere.config.di.modules.BackgroundModule;
import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.base.IServiceHelper;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import javax.inject.Inject;

public class LocationReminderWatcherService extends Service {
  private static final int SECOND = 1000;
  private static final int MINUTE = 60 * SECOND;
  private static final int LOCATION_CHECK_INTERVAL = 4 * MINUTE;
  private static final int TIMER_NO_DELAY = 0;

  public static final float MINIMUM_REMINDER_ACTIVATION_DISTANCE = 350f;

  private static final String COARSE_LOCATION_OBSERVER_TAG = "vinkel.COARSE_OBSERVER";

  private Intent startingIntent;
  private Disposable locationSubscription;

  @Inject
  public ILocationObserver locationObserver;

  @Inject
  public IReminderDatabase reminderDatabase;

  @Inject
  public IServiceHelper serviceHelper;

  @Inject
  @AppContext
  public Context appContext;

  @Inject
  @UnscopedIntentFactory
  IIntentFactory intentFactory;

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
    Log.e(COARSE_LOCATION_OBSERVER_TAG, "Creating service");

    this.injectMembers();
    if (this.timer != null) {
      this.timer.cancel();
    } else {
      this.timer = new Timer();
    }

    this.timer.scheduleAtFixedRate(
        new LocationObserverTimerTask(),
        TIMER_NO_DELAY,
        LOCATION_CHECK_INTERVAL);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();

    this.timer.cancel();

    if (this.locationSubscription != null && !this.locationSubscription.isDisposed()) {
      this.locationSubscription.dispose();
    }
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

      if (serviceHelper.isServiceRunning(PreciseLocationReminderWatcherService.class)) {
        Log.e(COARSE_LOCATION_OBSERVER_TAG, "Precise service is running");
        return;
      }

      if (locationSubscription != null && !locationSubscription.isDisposed()) {
        return;
      }


      Log.e(COARSE_LOCATION_OBSERVER_TAG, "Repeating");
      locationSubscription = locationObserver.getLocationOnce()
          .subscribeOn(Schedulers.io())
          .observeOn(Schedulers.io())
          .subscribe(new LocationConsumer());
    }
  }

  private class LocationConsumer implements Consumer<Location> {

    @Override
    public void accept(final Location currentLocation) throws Exception {
      reminderDatabase
          .getActiveLocationReminders()
          .observeOn(Schedulers.io())
          .subscribe(new Consumer<List<IReminder>>() {
            @Override
            public void accept(List<IReminder> activeReminders) throws Exception {

              Log.e(COARSE_LOCATION_OBSERVER_TAG, "ActiveGeoReminders: " + activeReminders.size());

              if (activeReminders.size() == 0) {
                Log.e(
                    COARSE_LOCATION_OBSERVER_TAG,
                    "No active location reminders found, stopping service");

                appContext.stopService(startingIntent);
                return;
              }

              for (IReminder reminder : activeReminders) {
                Location reminderLocation = new Location(reminder.getLocationName());
                reminderLocation.setLongitude(reminder.getLongitude());
                reminderLocation.setLatitude(reminder.getLatitude());

                Log.e(
                    COARSE_LOCATION_OBSERVER_TAG,
                    "Reminder "
                        + reminder.getId()
                        + " Location: "
                        + reminder.getLocationName());

                float distanceToReminder = currentLocation.distanceTo(reminderLocation);

                Log.e(
                    COARSE_LOCATION_OBSERVER_TAG,
                    "Distance to reminder: "
                        + distanceToReminder
                        + " m");

                if (distanceToReminder < MINIMUM_REMINDER_ACTIVATION_DISTANCE) {
                  Intent preciseLocationReminderWatcherServiceIntent =
                      intentFactory.getIntent(PreciseLocationReminderWatcherService.class);
                  Log.e(COARSE_LOCATION_OBSERVER_TAG, "Starting precise location listener");
                  appContext.startService(preciseLocationReminderWatcherServiceIntent);
                  break;
                }

              }

              if (locationSubscription != null && !locationSubscription.isDisposed()) {
                locationSubscription.dispose();
              }
            }
          });
    }
  }
}
