package com.vinkel.remindmewheniamthere.utils;

import android.content.Context;
import android.widget.Toast;

import com.vinkel.remindmewheniamthere.config.di.annotations.ActivityContext;
import com.vinkel.remindmewheniamthere.utils.base.INotificator;

import javax.inject.Inject;


public class Notificator implements INotificator {

  private final Context context;

  @Inject
  public Notificator(@ActivityContext Context context) {
    this.context = context;
  }
  
  @Override
  public void showMessage(String message) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show();
  }
}
