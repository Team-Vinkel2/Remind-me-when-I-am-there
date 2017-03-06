package com.vinkel.remindmewheniamthere.views.settings.base;


import android.net.Uri;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.base.IView;

public interface ISettingsContracts {

  interface View extends IView<Presenter> {
    void setMaxVolume(int maxVolume);

    void setCurrentVolume(int curVolume);

    void setCurrentRingtone(String ringtoneName);

  }

  interface Presenter extends IPresenter<View> {
    void saveAudioVolume(int volume);

    void saveRingtone(Uri ringtone);

    void loadRingtoneTitle();
  }

}
