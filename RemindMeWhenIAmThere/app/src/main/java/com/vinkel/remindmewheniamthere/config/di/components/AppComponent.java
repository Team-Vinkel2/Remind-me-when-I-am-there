package com.vinkel.remindmewheniamthere.config.di.components;

import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.activities.MainActivity;
import com.vinkel.remindmewheniamthere.config.di.modules.AppModule;
import com.vinkel.remindmewheniamthere.config.di.modules.UtilsModule;
import dagger.Component;
import javax.inject.Singleton;


@Singleton
@Component(modules = {AppModule.class, UtilsModule.class})
public interface AppComponent {
  void inject(RMWITApplication application);

  void inject(MainActivity activity);
}
