package com.vinkel.remindmewheniamthere;

import com.vinkel.remindmewheniamthere.activities.MainActivity;
import dagger.Component;
import javax.inject.Singleton;


@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
  void inject(RMWITApplication application);

  void inject(MainActivity activity);
}
