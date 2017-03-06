package com.vinkel.remindmewheniamthere.views.reminder_poup;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.data.base.IReminderDatabase;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.utils.ReminderManager;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;

public class ReminderPopupActivity extends AppCompatActivity {

  private static long[] VIBRATION_PATTERN = new long[]{0, 700, 500, 700, 800};

  private Intent intent;

  @Inject
  IApplicationSettingsManager applicationSettingsManager;

  @Inject
  IReminderDatabase reminderDatabase;

  @Inject
  MediaPlayer mediaPlayer;

  @Inject
  AudioManager audioManager;

  IReminder foundReminder;

  @Inject
  Vibrator vibrator;

  Button stopButton;
  TextView reminderTitleTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_reminder_popup);

    this.injectMembers();
    this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        + WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
        |
        +WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
        |
        +WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

    stopButton = (Button) this.findViewById(R.id.btn_stop_alarm);
    reminderTitleTextView = (TextView) this.findViewById(R.id.tv_alarm);

    this.intent = getIntent();
    this.start();
    this.startVibrating();
    this.startRinging();

    this.stopButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        finish();
      }
    });
  }


  private void injectMembers() {
    ((RMWITApplication) getApplication())
        .getComponent()
        .getActivityComponent(new ActivityModule(this, this.getSupportFragmentManager()))
        .inject(this);
  }

  private void start() {
    final int reminderId = this.intent.getIntExtra(ReminderManager.EXTRA_REMINDER_ID_KEY, 0);
    reminderDatabase.getAll()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<IReminder>>() {
          @Override
          public void accept(List<IReminder> reminders) throws Exception {

            for (IReminder reminder : reminders) {
              if (reminder.getId() == reminderId) {
                foundReminder = reminder;
              }

              String message = "";
              if (reminder.getDateString() == null) {
                message += "You arrived!";
                message += "\nNear" + reminder.getLocationName();
                message += "\n" + reminder.getTitle();
                message += "\n" + reminder.getContent();

              } else {
                message += "BRR BRRR!";
                message += "\n" + "IT IS TIME!";
                message += "\n" + reminder.getTitle();
                message += "\n" + reminder.getContent();
              }

              final String finalMessage = message;

              runOnUiThread(new Runnable() {
                @Override
                public void run() {
                  reminderTitleTextView.setText(finalMessage);
                }
              });
            }
          }
        });
  }

  private void startRinging() {
    try {
      audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, applicationSettingsManager.getAlarmVolume(), 0);
      this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
      this.mediaPlayer.setDataSource(this.getApplicationContext(), applicationSettingsManager.getRingtoneUri());
      this.mediaPlayer.setLooping(true);
      mediaPlayer.prepare();
      mediaPlayer.start();
    } catch (IOException ex) {
      this.mediaPlayer.stop();
    }
  }

  private void startVibrating() {
    vibrator.vibrate(VIBRATION_PATTERN, 0);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();

    foundReminder.setIsActive(false);
    foundReminder.setIsCompleted(true);
    reminderDatabase.update(foundReminder)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();

    try {
      mediaPlayer.stop();
      vibrator.cancel();
    } catch (Exception ex) {
    }
  }
}
