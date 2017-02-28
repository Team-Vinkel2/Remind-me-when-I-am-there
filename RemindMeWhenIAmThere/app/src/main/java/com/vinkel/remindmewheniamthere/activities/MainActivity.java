package com.vinkel.remindmewheniamthere.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


  @Inject
  public IApplicationSettingsManager applicationSettingsManager;

  @Inject
  public IIntentFactory intentFactory;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.injectMembers();

    if (this.applicationSettingsManager.getIsFirstLaunch()) {
      Intent introActivityIntent = this.intentFactory.getIntent(IntroActivity.class);
      this.startActivity(introActivityIntent);
    }

  }

  private void injectMembers() {
    ((RMWITApplication) this.getApplication())
        .getComponent()
        .getActivityComponent(new ActivityModule(this, this.getFragmentManager()))
        .inject(this);
  }
}
