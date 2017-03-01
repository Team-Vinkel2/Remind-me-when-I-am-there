package com.vinkel.remindmewheniamthere.views.home;

import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.home.base.IHomeContracts;
import javax.inject.Inject;

public class HomePresenter implements IHomeContracts.Presenter {

  private IHomeContracts.View view;

  private final IApplicationSettingsManager applicationSettingsManager;

  @Inject
  public HomePresenter(IApplicationSettingsManager applicationSettingsManager) {
    this.applicationSettingsManager = applicationSettingsManager;
  }

  @Override
  public void setView(IHomeContracts.View view) {
    this.view = view;
  }

  @Override
  public void start() {
    this.view.setTitle(this.applicationSettingsManager.getRingtoneUri().getPath());
  }
}
