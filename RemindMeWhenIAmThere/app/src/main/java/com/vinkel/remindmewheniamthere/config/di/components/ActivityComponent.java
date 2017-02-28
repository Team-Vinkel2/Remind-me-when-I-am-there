package com.vinkel.remindmewheniamthere.config.di.components;

import com.vinkel.remindmewheniamthere.activities.IntroActivity;
import com.vinkel.remindmewheniamthere.activities.MainActivity;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import dagger.Subcomponent;

@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
  void inject(MainActivity mainActivity);
  
  void inject(IntroActivity introActivity);
}
