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
import com.vinkel.remindmewheniamthere.utils.ReminderManager;
import com.vinkel.remindmewheniamthere.views.reminder_poup.ReminderPopupActivity;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;

public class PreciseLocationReminderWatcherService extends Service {

  private static final int SECOND = 1000;

  private static final float OPTIMAL_ALARM_RANGE_TRIGGER = 100f;
  private static final int UPDATE_LOCATION_INTERVAL = 15 * SECOND;
  private static final float MIN_UPDATE_LOCATION_DISTANCE = 0;
  private static final String PRECISE_LOCATION_OBSERVER_TAG = "vinkel.PRECISE_OBSERVER";

  private Intent startIntent;

  @Inject
  ILocationObserver locationObserver;

  @Inject
  IReminderDatabase reminderDatabase;

  @Inject
  @AppContext
  Context appContext;

  @Inject
  @UnscopedIntentFactory
  IIntentFactory intentFactory;

  private Disposable locationSubscriber;

  @Nullable
  @Override
  public IBinder onBind(Intent intent) {
    return null;
  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    super.onStartCommand(intent, flags, startId);
    Log.e(PRECISE_LOCATION_OBSERVER_TAG, "Precise service started");
    this.startIntent = intent;
    return START_STICKY;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    this.injectMembers();

    locationSubscriber = locationObserver
        .getLocationObserver(UPDATE_LOCATION_INTERVAL, MIN_UPDATE_LOCATION_DISTANCE, PRECISE_LOCATION_OBSERVER_TAG)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.io())
        .subscribe(new LocationConsumer());
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
              int nearbyRemindersCount = 0;

              Log.e(
                  PRECISE_LOCATION_OBSERVER_TAG,
                  "active reminders count: "
                      + activeReminders.size()
              );

              for (IReminder reminder : activeReminders) {
                Location reminderLocation = new Location(reminder.getLocationName());
                reminderLocation.setLongitude(reminder.getLongitude());
                reminderLocation.setLatitude(reminder.getLatitude());

                float distanceToReminder = currentLocation.distanceTo(reminderLocation);

                if (distanceToReminder < LocationReminderWatcherService.MINIMUM_REMINDER_ACTIVATION_DISTANCE) {
                  nearbyRemindersCount++;
                }

                Log.e(
                    PRECISE_LOCATION_OBSERVER_TAG,
                    "distance to location "
                        + reminder.getLocationName()
                        + " "
                        + distanceToReminder + " m"
                );

                if (distanceToReminder < OPTIMAL_ALARM_RANGE_TRIGGER) {
                  Intent reminderIntent = intentFactory.getIntent(ReminderPopupActivity.class);
                  reminderIntent.putExtra(ReminderManager.EXTRA_REMINDER_ID_KEY, (int)(long) reminder.getId());
                  reminderIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                  appContext.startActivity(reminderIntent);
                  nearbyRemindersCount--;
                  reminder.setIsActive(false);
                  reminderDatabase.update(reminder)
                    .observeOn(Schedulers.io())
                    .subscribe();

                  Log.e(
                      PRECISE_LOCATION_OBSERVER_TAG,
                      "near "
                          + reminder.getLocationName()
                          + " "
                          + distanceToReminder + " m"
                  );

                }
              }

              if (nearbyRemindersCount <= 0) {
                Log.e(PRECISE_LOCATION_OBSERVER_TAG, "No nearby alarms");
                appContext.stopService(startIntent);
              }
            }
          });
    }
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    if (this.locationSubscriber != null && !this.locationSubscriber.isDisposed()) {
      Log.e(PRECISE_LOCATION_OBSERVER_TAG, "On destroy");
      this.locationSubscriber.dispose();
    }
  }

  private void injectMembers() {
    RMWITApplication
        .getInstance()
        .getComponent()
        .getBackgroundComponent(new BackgroundModule())
        .inject(this);
  }
}
