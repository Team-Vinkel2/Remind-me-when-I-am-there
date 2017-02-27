package com.vinkel.remindmewheniamthere.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.vinkel.remindmewheniamthere.AppScope;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

  @Inject
  @AppScope
  public Context context;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ((RMWITApplication) this.getApplication()).component().inject(this);
    Log.e("TestContext", context.toString());
  }
}
