package com.vinkel.remindmewheniamthere.views.settings;


import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.views.settings.base.ISettingsContracts;

public class SettingsView extends Fragment implements ISettingsContracts.View {

  private ISettingsContracts.Presenter presenter;

  private SeekBar volControl;
  private Button selectRingtoneButton;
  private TextView currentRingtoneTextView;
  private static final int RINGTONE_PICKER_ACTIVITY_REQWUEST_CODE = 88;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_settings, container, false);
    prepareView(view);
    return view;
  }

  @Override
  public void setPresenter(ISettingsContracts.Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView(View view) {
    selectRingtoneButton = (Button) view.findViewById(R.id.btn_select_ringtone);
    volControl = (SeekBar) view.findViewById(R.id.volume_bar);
    currentRingtoneTextView = (TextView) view.findViewById(R.id.tv_current_ringtone);
    selectRingtoneButton = (Button) view.findViewById(R.id.btn_select_ringtone);

    volControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      int progress = 0;
      @Override
      public void onStopTrackingTouch(SeekBar arg0) {
        presenter.saveAudioVolume(progress);
      }

      @Override
      public void onStartTrackingTouch(SeekBar arg0) {
      }

      @Override
      public void onProgressChanged(SeekBar seekBar, int progress, boolean changed) {
        this.progress = progress;
      }
    });

    selectRingtoneButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        startRingtonePicker();
      }
    });
  }

  @Override
  public void setMaxVolume(int maxVolume) {
    volControl.setMax(maxVolume);
  }

  @Override
  public void setCurrentVolume(int curVolume) {
    volControl.setProgress(curVolume);
  }

  @Override
  public void setCurrentRingtone(String ringtoneName) {
    this.currentRingtoneTextView.setText(ringtoneName);
  }

  private void startRingtonePicker() {
    Intent ringtoneManagerIntent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
    this.startActivityForResult(ringtoneManagerIntent, RINGTONE_PICKER_ACTIVITY_REQWUEST_CODE);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if(requestCode == RINGTONE_PICKER_ACTIVITY_REQWUEST_CODE && resultCode == SettingsActivity.RESULT_OK) {
      Uri uri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
      this.presenter.saveRingtone(uri);
    }
  }
}
