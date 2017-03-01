package com.vinkel.remindmewheniamthere.views.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;

public class HomeActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    this.injectMembers();
  }

  private void injectMembers() {
    ((RMWITApplication) this.getApplication())
        .getComponent()
        .getActivityComponent(new ActivityModule(this, this.getFragmentManager()))
        .inject(this);
  }
}
