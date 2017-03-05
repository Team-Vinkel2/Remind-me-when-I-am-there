package com.vinkel.remindmewheniamthere.views.add_reminder;


import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.schibstedspain.leku.LocationPickerActivity;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.annotations.IntentFactoryForActivity;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.ui.fragments.DatePickerDialogFragment;
import com.vinkel.remindmewheniamthere.ui.fragments.TimePickerDialogFragment;
import com.vinkel.remindmewheniamthere.views.add_reminder.base.IAddReminderContracts;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;

import javax.inject.Inject;

public class AddReminderView extends Fragment implements IAddReminderContracts.View {

  private IPresenter<IAddReminderContracts.View> presenter;

  private RadioButton radioButtonLocation;
  private RadioButton radioButtonDateTime;
  private Button datePickerButton;
  private Button timePickerButton;
  private Button setLocationButton;
  private EditText locationName;

  @Inject
  @IntentFactoryForActivity
  IIntentFactory intentFactory;

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

    if (requestCode == 1) {
      if (resultCode == Activity.RESULT_OK) {
        double latitude = data.getDoubleExtra(LocationPickerActivity.LATITUDE, 0);
        Log.d("LATITUDE****", String.valueOf(latitude));
        double longitude = data.getDoubleExtra(LocationPickerActivity.LONGITUDE, 0);
        Log.d("LONGITUDE****", String.valueOf(longitude));
        String address = data.getStringExtra(LocationPickerActivity.LOCATION_ADDRESS);
        Log.d("ADDRESS****", String.valueOf(address));
        String postalcode = data.getStringExtra(LocationPickerActivity.ZIPCODE);
        Log.d("POSTALCODE****", String.valueOf(postalcode));
        Bundle bundle = data.getBundleExtra(LocationPickerActivity.TRANSITION_BUNDLE);
        Address fullAddress = data.getParcelableExtra(LocationPickerActivity.ADDRESS);
        Log.d("FULL ADDRESS****", fullAddress.toString());
      }
      if (resultCode == Activity.RESULT_CANCELED) {
        //Write your code if there's no result
      }
    }
  }

  @Override
  public void setPresenter(IAddReminderContracts.Presenter presenter) {
  }

  public void prepareView(View view) {
    timePickerButton = (Button) view.findViewById(R.id.btn_open_time_picker);
    datePickerButton = (Button) view.findViewById(R.id.btn_open_date_picker);
    radioButtonDateTime = (RadioButton) view.findViewById(R.id.radio_datetime);
    radioButtonLocation = (RadioButton) view.findViewById(R.id.radio_location);
    setLocationButton = (Button) view.findViewById(R.id.btn_set_location);
    locationName = (EditText) view.findViewById(R.id.edittext_location);


    radioButtonLocation.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        datePickerButton.setVisibility(View.GONE);
        timePickerButton.setVisibility(View.GONE);
        setLocationButton.setVisibility(View.VISIBLE);
        locationName.setVisibility(View.VISIBLE);
      }
    });

    radioButtonDateTime.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        datePickerButton.setVisibility(View.VISIBLE);
        timePickerButton.setVisibility(View.VISIBLE);
        setLocationButton.setVisibility(View.GONE);
        locationName.setVisibility(View.GONE);
      }
    });

    timePickerButton.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogFragment timePickerDialog = new TimePickerDialogFragment();
        timePickerDialog.show(getFragmentManager(), "Test");
      }
    });

    datePickerButton.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogFragment datePickerDialog = new DatePickerDialogFragment();
        datePickerDialog.show(getFragmentManager(), "Test");
      }
    });

    setLocationButton.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = intentFactory.getIntent(LocationPickerActivity.class);
        startActivityForResult(intent, 1);
      }
    });
  }

  private void injectMembers() {
    RMWITApplication.getInstance()
        .getComponent()
        .getActivityComponent(new ActivityModule(getActivity()))
        .inject(this);
  }
}
