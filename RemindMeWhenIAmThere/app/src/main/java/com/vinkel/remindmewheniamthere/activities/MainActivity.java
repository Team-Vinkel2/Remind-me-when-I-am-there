package com.vinkel.remindmewheniamthere.activities;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.annotations.AppScope;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject
  @AppScope
  public Context context;

  @Inject
  @AppScope
  public IApplicationSettingsManager settingsManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ((RMWITApplication) this.getApplication()).component().inject(this);
    Log.e("TestContext", context.toString());

    Toast.makeText(context, "Max volume: " + settingsManager.getAlarmVolume(), Toast.LENGTH_LONG)
        .show();

    Log.e("TestSettings", this.settingsManager.getRingtoneUri().getPath());

    Ringtone ringtone = RingtoneManager.getRingtone(this.context, this.settingsManager.getRingtoneUri());
    Log.e("Ringtone name" , ringtone.getTitle(this.context));

  }
}
