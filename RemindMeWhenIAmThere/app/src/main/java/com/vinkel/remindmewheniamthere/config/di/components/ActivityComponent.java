package com.vinkel.remindmewheniamthere.config.di.components;

import com.vinkel.remindmewheniamthere.MainActivity;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.config.di.modules.PresenterModule;
import com.vinkel.remindmewheniamthere.config.di.modules.ViewModule;
import com.vinkel.remindmewheniamthere.ui.fragments.ToolbarFragment;
import com.vinkel.remindmewheniamthere.views.add_reminder.AddReminderActivity;
import com.vinkel.remindmewheniamthere.views.add_reminder.AddReminderView;
import com.vinkel.remindmewheniamthere.views.home.HomeActivity;
import com.vinkel.remindmewheniamthere.views.intro.IntroActivity;
import com.vinkel.remindmewheniamthere.views.settings.SettingsActivity;
import com.vinkel.remindmewheniamthere.views.sign_in.SignInActivity;
import com.vinkel.remindmewheniamthere.views.sign_up.SignUpActivity;
import com.vinkel.remindmewheniamthere.views.sign_up.SignUpView;
import dagger.Subcomponent;

@Subcomponent(modules = {ActivityModule.class, PresenterModule.class, ViewModule.class})
public interface ActivityComponent {
  void inject(MainActivity mainActivity);

  void inject(HomeActivity homeActivity);

  void inject(IntroActivity introActivity);

  void inject(SignUpActivity signUpActivity);

  void inject(SignInActivity signInActivity);

  void inject(ToolbarFragment toolbarFragment);

  void inject(SettingsActivity settingsActivity);

  void inject(AddReminderActivity addReminderActivity);

  void inject(AddReminderView addReminderView);

}
