package com.vinkel.remindmewheniamthere.views.settings;


import android.media.AudioManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.views.settings.base.ISettingsContracts;

import javax.inject.Inject;

public class SettingsView extends Fragment implements ISettingsContracts.View {

  private ISettingsContracts.Presenter presenter;

  private SeekBar volControl;
  private Button selectRingtone;

  @Inject
  AudioManager audioManager;

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
    selectRingtone = (Button) view.findViewById(R.id.btn_select_ringtone);
    volControl = (SeekBar) view.findViewById(R.id.volume_bar);

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
      public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
        progress = arg1;
      }
    });
  }

  @Override
  public void setMaxVolume(int maxVolume) {
    presenter.saveAudioVolume(maxVolume);
  }

  @Override
  public void setCurrentVolume(int curVolume) {
    presenter.saveAudioVolume(curVolume);
  }
}
