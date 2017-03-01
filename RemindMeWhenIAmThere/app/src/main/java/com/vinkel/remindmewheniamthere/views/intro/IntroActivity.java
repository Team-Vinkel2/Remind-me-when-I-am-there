package com.vinkel.remindmewheniamthere.views.intro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.home.HomeActivity;
import javax.inject.Inject;
import javax.inject.Named;

public class IntroActivity extends AppIntro {

  @Inject
  @Named("requestedAppPermissions")
  public String[] requiredPermissions;

  @Inject
  @Named("isRunningOnMarshmallow")
  public boolean isRunningOnMarshmallow;

  @Inject
  public IApplicationSettingsManager applicationSettingsManager;

  @Inject
  public IIntentFactory intentFactory;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.injectMembers();

    this.setupWelcomeScreen();
  }


  private void setupWelcomeScreen() {
    this.setupSlides();
    this.showSkipButton(false);
  }

  @SuppressWarnings("deprecation")
  private void setupSlides() {
    int mainSlideColor = getResources().getColor(R.color.appMainColor);
    //Welcome slide
    this.addSlide(
        AppIntroFragment.newInstance(
            getResources().getString(R.string.welcome_slide_title),
            getResources().getString(R.string.welcome_slide_message),
            R.drawable.app_logo,
            mainSlideColor));

    //Offline functionality
    this.addSlide(
        AppIntroFragment.newInstance(
            getResources().getString(R.string.offline_mode_slide_title),
            getResources().getString(R.string.offline_mode_slide_content),
            R.drawable.offline_mode,
            mainSlideColor));

    //Online features
    this.addSlide(
        AppIntroFragment.newInstance(
            getResources().getString(R.string.online_mode_slide_title),
            getResources().getString(R.string.online_mode_slide_content),
            R.drawable.connect,
            mainSlideColor));

    this.addSlide(
        AppIntroFragment.newInstance(
            getResources().getString(R.string.online_mode_sync_slide_title),
            getResources().getString(R.string.online_mode_sync_slide_content),
            R.drawable.sync,
            mainSlideColor));
    //Permission
    if (this.isRunningOnMarshmallow) {
      this.addSlide(
          AppIntroFragment.newInstance(
              getResources().getString(R.string.permissions_slide_title),
              getResources().getString(R.string.permissions_slide_content),
              R.drawable.permission,
              mainSlideColor));
      this.askForPermissions(this.requiredPermissions, this.getSlides().size());
    }

    //End slide
    this.addSlide(
        AppIntroFragment.newInstance(
            getResources().getString(R.string.end_slide_title),
            getResources().getString(R.string.end_slide_content),
            R.drawable.reminder_list,
            mainSlideColor));
  }

  @Override
  public void onDonePressed(Fragment currentFragment) {
    super.onDonePressed(currentFragment);
    this.applicationSettingsManager.setIsFirstLaunch(false);
    Intent homeActivityIntent =
        intentFactory.getIntentWithNoAnimatedTransitionFlag(HomeActivity.class);
    this.startActivity(homeActivityIntent);
    this.finish();
  }

  private void injectMembers() {
    ((RMWITApplication) getApplication())
        .getComponent()
        .getActivityComponent(new ActivityModule(this, this.getSupportFragmentManager()))
        .inject(this);
  }

}
