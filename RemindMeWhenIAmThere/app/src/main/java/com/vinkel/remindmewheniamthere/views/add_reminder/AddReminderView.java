package com.vinkel.remindmewheniamthere.views.add_reminder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.ui.fragments.DatePickerDialogFragment;
import com.vinkel.remindmewheniamthere.ui.fragments.TimePickerDialogFragment;
import com.vinkel.remindmewheniamthere.views.add_reminder.base.IAddReminderContracts;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;

public class AddReminderView extends Fragment implements IAddReminderContracts.View {

    private IPresenter<IAddReminderContracts.View> presenter;

    private RadioButton radioButtonLocation;
    private RadioButton radioButtonDateTime;
    private Button datePickerButton;
    private Button timePickerButton;
    private Button setLocationButton;
    private EditText locationName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_reminder, container, false);
        prepareView(view);
        return view;
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
    }
}
