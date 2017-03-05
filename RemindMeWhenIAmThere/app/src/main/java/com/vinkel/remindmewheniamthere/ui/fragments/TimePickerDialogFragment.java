package com.vinkel.remindmewheniamthere.ui.fragments;


import android.app.Dialog;

import android.app.TimePickerDialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

import java.util.Calendar;


public class TimePickerDialogFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

  @NonNull
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    final Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    int minute = calendar.get(Calendar.MINUTE);

    return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
  }

  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

  }
}
