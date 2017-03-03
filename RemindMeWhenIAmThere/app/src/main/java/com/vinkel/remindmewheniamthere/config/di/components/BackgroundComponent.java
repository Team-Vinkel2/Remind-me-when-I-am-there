package com.vinkel.remindmewheniamthere.config.di.components;

import com.vinkel.remindmewheniamthere.background.receivers.BootReceiver;
import com.vinkel.remindmewheniamthere.background.receivers.ReminderReceiver;
import com.vinkel.remindmewheniamthere.config.di.modules.BackgroundModule;
import dagger.Subcomponent;

@Subcomponent(modules = {BackgroundModule.class})
public interface BackgroundComponent {
  void inject(ReminderReceiver reminderReceiver);

  void inject(BootReceiver bootReceiver);
}
