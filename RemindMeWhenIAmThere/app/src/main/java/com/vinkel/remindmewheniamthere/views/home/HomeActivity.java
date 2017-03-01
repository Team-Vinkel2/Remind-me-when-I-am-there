package com.vinkel.remindmewheniamthere.views.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.views.home.base.IHomeContracts;
import javax.inject.Inject;

public class HomeActivity extends AppCompatActivity {

  @Inject
  IHomeContracts.Presenter homePresenter;
  IHomeContracts.View homeView;

  @Inject
  FragmentManager fragmentManager;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_home);
    this.injectMembers();
    this.initializeViews();
    this.setup();
    this.homePresenter.start();
  }

  private void initializeViews() {
    this.homeView = (IHomeContracts.View) fragmentManager.findFragmentById(R.id.fragment_home);
  }

  private void setup() {
    homePresenter.setView(homeView);
    homeView.setPresenter(homePresenter);
  }
  private void injectMembers() {
    ((RMWITApplication) this.getApplication())
        .getComponent()
        .getActivityComponent(new ActivityModule(this, this.getSupportFragmentManager()))
        .inject(this);
  }
}
