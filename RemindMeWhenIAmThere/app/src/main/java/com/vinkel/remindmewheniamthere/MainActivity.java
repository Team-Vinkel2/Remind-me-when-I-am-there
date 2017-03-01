package com.vinkel.remindmewheniamthere;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.home.HomeActivity;
import com.vinkel.remindmewheniamthere.views.intro.IntroActivity;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject
  public IIntentFactory intentFactory;

  @Inject
  public IApplicationSettingsManager applicationSettingsManager;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.injectMembers();

    Intent intent;
    if (applicationSettingsManager.getIsFirstLaunch()) {
      intent = intentFactory.getIntent(IntroActivity.class);
    } else {
      intent = intentFactory.getIntent(HomeActivity.class);
    }
    this.startActivity(intent);
    this.finish();
  }

  private void injectMembers() {
    ((RMWITApplication) getApplication())
        .getComponent()
        .getActivityComponent(new ActivityModule(this, this.getFragmentManager()))
        .inject(this);
  }
}
