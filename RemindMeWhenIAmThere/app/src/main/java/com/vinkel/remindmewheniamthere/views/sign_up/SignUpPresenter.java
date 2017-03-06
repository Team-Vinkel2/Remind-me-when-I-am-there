package com.vinkel.remindmewheniamthere.views.sign_up;


import android.widget.EditText;

import com.vinkel.remindmewheniamthere.network.UserData;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.sign_up.base.ISignUpContracts;

import javax.inject.Inject;

public class SignUpPresenter implements ISignUpContracts.Presenter {

  private ISignUpContracts.View view;
  private UserData userData;

  @Inject
  public SignUpPresenter(UserData userData) {
    this.userData = userData;
  }

  @Override
  public void setView(ISignUpContracts.View view) {
    this.view = view;
  }

  @Override
  public void start() {
  }

  @Override
  public void signUp(String username, String firstname, String password, String email) {
    userData.signUp()
  }
}
