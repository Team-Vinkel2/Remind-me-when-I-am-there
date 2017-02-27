package com.vinkel.remindmewheniamthere.config.di.components;

import com.vinkel.remindmewheniamthere.AppModule;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.activities.MainActivity;
import dagger.Component;
import javax.inject.Singleton;


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
  void inject(RMWITApplication application);

  void inject(MainActivity activity);
}
