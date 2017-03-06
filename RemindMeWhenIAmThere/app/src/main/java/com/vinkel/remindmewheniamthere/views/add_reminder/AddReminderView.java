package com.vinkel.remindmewheniamthere.views.add_reminder;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.TimePicker;
import com.schibstedspain.leku.LocationPickerActivity;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.annotations.IntentFactoryForActivity;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.ui.fragments.DatePickerDialogFragment;
import com.vinkel.remindmewheniamthere.ui.fragments.TimePickerDialogFragment;
import com.vinkel.remindmewheniamthere.utils.base.IDateTimeHelper;
import com.vinkel.remindmewheniamthere.views.add_reminder.base.IAddReminderContracts;
import java.util.Calendar;
import javax.inject.Inject;

public class AddReminderView extends Fragment implements
    IAddReminderContracts.View,
    DatePickerDialog.OnDateSetListener,
    TimePickerDialog.OnTimeSetListener {

  private IAddReminderContracts.Presenter presenter;

  private static final String REMINDER_TIME_PICKER_TAG = "Reminder time picker";
  private static final String REMINDER_DATE_PICKER_TAG = "Reminder date picker";
  private static final int LOCATION_ACTIVITY_REQUEST_CODE = 42;

  private double latitude = Double.MIN_VALUE;
  private double longitude = Double.MIN_VALUE;
  private int year;
  private int month;
  private int day;
  private int hour;
  private int minutes;

  private RadioButton radioButtonLocation;
  private RadioButton radioButtonDateTime;
  private EditText titleEditText;
  private EditText contentEditText;
  private Button datePickerButton;
  private Button setLocationButton;
  private EditText locationNameEditText;
  private TextView locationOrDateInfo;
  private Button addReminderButton;


  @Inject
  @IntentFactoryForActivity
  IIntentFactory intentFactory;

  @Inject
  IDateTimeHelper dateTimeHelper;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_add_reminder, container, false);
    injectMembers();
    prepareView(view);
    return view;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {

    if (requestCode == LOCATION_ACTIVITY_REQUEST_CODE) {
      if (resultCode == Activity.RESULT_OK) {
        this.latitude = data.getDoubleExtra(LocationPickerActivity.LATITUDE, Double.MIN_VALUE);
        this.longitude = data.getDoubleExtra(LocationPickerActivity.LONGITUDE, Double.MIN_VALUE);

        if (this.latitude != Double.MIN_VALUE && this.longitude != Double.MIN_VALUE) {
          this.setLocationOrDateInfoText("Latitude: " + this.latitude + "\nLongitude: " + longitude);
        }
      }
    }
  }

  @Override
  public void setPresenter(IAddReminderContracts.Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView(View view) {
    datePickerButton = (Button) view.findViewById(R.id.btn_open_date_picker);
    radioButtonDateTime = (RadioButton) view.findViewById(R.id.radio_datetime);
    titleEditText = (EditText) view.findViewById(R.id.add_reminder_title);
    contentEditText = (EditText) view.findViewById(R.id.add_reminder_content);
    radioButtonLocation = (RadioButton) view.findViewById(R.id.radio_location);
    setLocationButton = (Button) view.findViewById(R.id.btn_set_location);
    locationNameEditText = (EditText) view.findViewById(R.id.edittext_location);
    locationOrDateInfo = (TextView) view.findViewById(R.id.tv_location_or_date_info);
    addReminderButton = (Button) view.findViewById(R.id.btn_add_reminder);

    radioButtonLocation.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        resetLocationOrDateInfoTextView();
        datePickerButton.setVisibility(View.GONE);
        setLocationButton.setVisibility(View.VISIBLE);
        locationNameEditText.setVisibility(View.VISIBLE);
      }
    });

    radioButtonDateTime.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        resetLocationOrDateInfoTextView();
        datePickerButton.setVisibility(View.VISIBLE);
        setLocationButton.setVisibility(View.GONE);
        locationNameEditText.setVisibility(View.GONE);
      }
    });

    datePickerButton.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        DatePickerDialogFragment datePickerDialog = new DatePickerDialogFragment();
        datePickerDialog.setOnDateSetListener(AddReminderView.this);
        datePickerDialog.show(getFragmentManager(), REMINDER_DATE_PICKER_TAG);
      }
    });

    setLocationButton.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = intentFactory.getIntent(LocationPickerActivity.class);
        startActivityForResult(intent, LOCATION_ACTIVITY_REQUEST_CODE);
      }
    });

    addReminderButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (titleEditText.getText().toString().trim().equals("")) {
          titleEditText.setError("Title required");
          return;
        }
        if (contentEditText.getText().toString().trim().equals("")) {
          contentEditText.setError("Content required");
          return;
        }
        if (locationOrDateInfo.getText().toString().equals("")) {
          return;
        }
        if (radioButtonDateTime.isChecked()) {
          presenter.addLocalReminderForDate(
              titleEditText.getText().toString(),
              contentEditText.getText().toString(),
              year,
              month,
              day,
              hour,
              minutes);
        } else if (radioButtonLocation.isChecked()) {
          if (locationNameEditText.getText().toString().trim().equals("")) {
            locationNameEditText.setError("Location name is required");
            return;
          }

          if (latitude == Double.MIN_VALUE || longitude == Double.MIN_VALUE) {
            locationNameEditText.setError("Location not set");
            return;
          }
          presenter.addLocalReminderForLocation(
              titleEditText.getText().toString(),
              contentEditText.getText().toString(),
              latitude,
              longitude,
              locationNameEditText.getText().toString());
        }
      }
    });
  }

  @Override
  public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
    this.year = year;
    this.month = month;
    this.day = dayOfMonth;

    TimePickerDialogFragment timePickerDialog = new TimePickerDialogFragment();
    timePickerDialog.setOnTimeSetListener(this);
    timePickerDialog.show(getFragmentManager(), REMINDER_TIME_PICKER_TAG);
  }

  @Override
  public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
    this.hour = hourOfDay;
    this.minutes = minute;

    Calendar calendar = Calendar.getInstance();
    calendar.set(this.year, this.month, this.day, this.hour, this.minutes);
    String dateString = dateTimeHelper.parseCalendarToString(calendar);
    this.setLocationOrDateInfoText(dateString);
  }

  private void resetLocationOrDateInfoTextView() {
    this.locationOrDateInfo.setText("");
  }

  private void setLocationOrDateInfoText(String text) {
    this.locationOrDateInfo.setText(text);
  }

  @Override
  public void finish() {
    getActivity().finish();
  }

  private void injectMembers() {
    RMWITApplication.getInstance()
        .getComponent()
        .getActivityComponent(new ActivityModule(getActivity()))
        .inject(this);
  }

}
