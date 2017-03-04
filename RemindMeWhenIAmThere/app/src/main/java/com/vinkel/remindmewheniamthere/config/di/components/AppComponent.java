package com.vinkel.remindmewheniamthere.config.di.components;

import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.config.di.modules.AppConfigModule;
import com.vinkel.remindmewheniamthere.config.di.modules.AppModule;
import com.vinkel.remindmewheniamthere.config.di.modules.BackgroundModule;
import com.vinkel.remindmewheniamthere.config.di.modules.LocalDatabaseModule;
import com.vinkel.remindmewheniamthere.config.di.modules.ModelModule;
import com.vinkel.remindmewheniamthere.config.di.modules.ObserverModule;
import com.vinkel.remindmewheniamthere.config.di.modules.UtilsModule;
import dagger.Component;

@Component(modules = {
    AppModule.class,
    ModelModule.class,
    LocalDatabaseModule.class,
    ObserverModule.class,
    AppConfigModule.class,
    UtilsModule.class})
public interface AppComponent {
  ActivityComponent getActivityComponent(ActivityModule activityModule);

  BackgroundComponent getBackgroundComponent(BackgroundModule backgroundModule);
}
