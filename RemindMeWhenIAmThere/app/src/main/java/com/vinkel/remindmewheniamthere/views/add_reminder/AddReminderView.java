package com.vinkel.remindmewheniamthere.views.add_reminder;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.ui.fragments.DatePickerDialogFragment;
import com.vinkel.remindmewheniamthere.ui.fragments.TimePickerDialogFragment;
import com.vinkel.remindmewheniamthere.views.add_reminder.base.IAddReminderContracts;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;

public class AddReminderView extends Fragment implements IAddReminderContracts.View {

    private IPresenter<IAddReminderContracts.View> presenter;

    private RadioGroup radioGroup;
    private Button datePickerButton;
    private Button timePickerButton;

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
      radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_type_reminder);
      timePickerButton = (Button) view.findViewById(R.id.btn_open_time_picker);
      datePickerButton = (Button) view.findViewById(R.id.btn_open_date_picker);

      radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
          if(checkedId == 0) {

          } else {

          }
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
