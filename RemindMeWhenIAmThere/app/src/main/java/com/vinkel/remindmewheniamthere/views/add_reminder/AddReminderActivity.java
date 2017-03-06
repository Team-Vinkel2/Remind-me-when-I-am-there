package com.vinkel.remindmewheniamthere.views.add_reminder;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.views.add_reminder.base.IAddReminderContracts;
import javax.inject.Inject;

public class AddReminderActivity extends AppCompatActivity {

  @Inject
  IAddReminderContracts.Presenter addReminderPresenter;
  IAddReminderContracts.View addReminderView;

  @Inject
  FragmentManager fragmentManager;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_reminder);
    this.injectMembers();
    this.initializeViews();
    this.setup();
    this.addReminderPresenter.start();
  }

  private void initializeViews() {
    this.addReminderView = (IAddReminderContracts.View) fragmentManager.findFragmentById(R.id.fragment_add_reminder);
  }

  private void setup() {
    addReminderPresenter.setView(addReminderView);
    addReminderView.setPresenter(addReminderPresenter);
  }

  private void injectMembers() {
    ((RMWITApplication) this.getApplication())
        .getComponent()
        .getActivityComponent(new ActivityModule(this, this.getSupportFragmentManager()))
        .inject(this);
  }
}
