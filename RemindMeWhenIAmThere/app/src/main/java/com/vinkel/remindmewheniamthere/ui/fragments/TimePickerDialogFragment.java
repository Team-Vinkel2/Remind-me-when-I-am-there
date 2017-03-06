package com.vinkel.remindmewheniamthere.ui.fragments;


import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import java.util.Calendar;


public class TimePickerDialogFragment extends DialogFragment {

  private TimePickerDialog.OnTimeSetListener listener;

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    final Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);

    return new TimePickerDialog(getActivity(), this.listener, hour, minute, true);
  }

  public void setOnTimeSetListener(TimePickerDialog.OnTimeSetListener listener) {
    this.listener = listener;
  }
}
