package com.vinkel.remindmewheniamthere.views.sign_in;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.utils.base.INotificator;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.sign_in.base.ISignInContracts;

import javax.inject.Inject;

public class SignInView extends Fragment implements ISignInContracts.View {

  private ISignInContracts.Presenter presenter;

  EditText username;
  EditText password;
  Button signInButton;

  @Inject
  INotificator notificator;

  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup contrainer, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_sign_in, contrainer, false);
    injectMembers();
    prepareView(view);
    return view;
  }

  @Override
  public void setPresenter(ISignInContracts.Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView(View view) {
    username = (EditText) view.findViewById(R.id.signin_username);
    password = (EditText) view.findViewById(R.id.signin_password);
    signInButton = (Button) view.findViewById(R.id.btn_sign_in);

    signInButton.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        String usernameSend = username.getText().toString();
        String passwordSend = password.getText().toString();

        presenter.signIn(usernameSend, passwordSend);
      }
    });
  }

  public void completed() {
    getActivity().finish();
  }

  public void displayMessage(String message) {
    notificator.showMessage(message);
  }

  private void injectMembers() {
    RMWITApplication.getInstance()
        .getComponent()
        .getActivityComponent(new ActivityModule(getActivity()))
        .inject(this);
  }
}
