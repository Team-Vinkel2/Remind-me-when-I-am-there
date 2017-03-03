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
<<<<<<< HEAD
=======
import com.vinkel.remindmewheniamthere.views.sign_in.SignInActivity;
import com.vinkel.remindmewheniamthere.views.sign_up.SignUpActivity;

>>>>>>> 6723dc6883d88268d668b13134298c5487161144
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
<<<<<<< HEAD
      intent = intentFactory.getIntent(HomeActivity.class);
=======
      intent = intentFactory.getIntent(SignInActivity.class);
>>>>>>> 6723dc6883d88268d668b13134298c5487161144
    }
    this.startActivity(intent);
    this.finish();
  }

  private void injectMembers() {
    ((RMWITApplication) getApplication())
        .getComponent()
        .getActivityComponent(new ActivityModule(this, this.getSupportFragmentManager()))
        .inject(this);
  }
}
