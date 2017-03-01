package com.vinkel.remindmewheniamthere.config.di.components;

import com.vinkel.remindmewheniamthere.MainActivity;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.views.home.HomeActivity;
import com.vinkel.remindmewheniamthere.views.intro.IntroActivity;
import dagger.Subcomponent;

@Subcomponent(modules = {ActivityModule.class})
public interface ActivityComponent {
  void inject(MainActivity mainActivity);

  void inject(HomeActivity homeActivity);

  void inject(IntroActivity introActivity);

}
