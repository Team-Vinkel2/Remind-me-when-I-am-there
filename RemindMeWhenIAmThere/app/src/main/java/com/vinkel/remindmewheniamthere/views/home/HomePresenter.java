package com.vinkel.remindmewheniamthere.views.home;

import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.providers.base.IReminderFactory;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.home.base.IHomeContracts;

import java.util.ArrayList;

import javax.inject.Inject;

public class HomePresenter implements IHomeContracts.Presenter {

  private IHomeContracts.View view;

  IReminderFactory factory;

  private final IApplicationSettingsManager applicationSettingsManager;

  @Inject
  public HomePresenter(IApplicationSettingsManager applicationSettingsManager, IReminderFactory factory) {
    this.applicationSettingsManager = applicationSettingsManager;
    this.factory = factory;
  }

  @Override
  public void setView(IHomeContracts.View view) {
    this.view = view;
  }

  @Override
  public void start() {
    this.view.setTitle(this.applicationSettingsManager.getRingtoneUri().getPath());

    ArrayList<IReminder> reminders = new ArrayList<IReminder>();
    reminders.add(factory.getLocalDateReminder("Test Title", "Test Content", "Test Date"));
    reminders.add(factory.getLocalDateReminder("Test Title", "Test Content", "Test Date"));
    reminders.add(factory.getLocalLocationReminder("Test Title", "Test Content", 50, 50, "Test Location"));
    reminders.add(factory.getLocalLocationReminder("Test Title", "Test Content", 50, 50, "Test Location"));
    reminders.add(factory.getLocalDateReminder("Test Title", "Test Content", "Test Date"));
    reminders.add(factory.getLocalDateReminder("Test Title", "Test Content", "Test Date"));
    reminders.add(factory.getLocalDateReminder("Test Title", "Test Content", "Test Date"));
    reminders.add(factory.getLocalDateReminder("Test Title", "Test Content", "Test Date"));
    reminders.add(factory.getLocalDateReminder("Test Title", "Test Content", "Test Date"));
    reminders.add(factory.getLocalDateReminder("Test Title", "Test Content", "Test Date"));

    this.view.setReminders(reminders);
  }
}
