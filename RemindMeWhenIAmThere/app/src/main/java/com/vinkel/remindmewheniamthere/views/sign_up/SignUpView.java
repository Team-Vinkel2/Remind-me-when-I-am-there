package com.vinkel.remindmewheniamthere.views.sign_up;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.models.User;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.sign_up.base.ISignUpContracts;

public class SignUpView extends Fragment implements ISignUpContracts.View {

  private ISignUpContracts.Presenter presenter;

  EditText firstname;
  EditText username;
  EditText password;
  EditText confirmPassword;
  EditText email;
  Button signUpButton;

  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
    prepareView(view);
    return view;
  }

  @Override
  public void setPresenter(ISignUpContracts.Presenter presenter) {
    this.presenter = presenter;
  }

  public void prepareView(View view) {
    firstname = (EditText) view.findViewById(R.id.signup_firstname);
    username = (EditText) view.findViewById(R.id.signup_username);
    password = (EditText) view.findViewById(R.id.signup_password);
    confirmPassword = (EditText) view.findViewById(R.id.signup_confirm_password);
    email = (EditText) view.findViewById(R.id.signup_email_address);
    signUpButton = (Button) view.findViewById(R.id.sign_up_btn);

    signUpButton.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        String firstnameSend = firstname.getText().toString();
        String usernameSend = username.getText().toString();
        String passwordSend = password.getText().toString();
        String confirmPasswordSend = confirmPassword.getText().toString();
        String emailSend = email.getText().toString();

        if (passwordSend == confirmPasswordSend) {
          presenter.signUp(usernameSend, firstnameSend, passwordSend, emailSend);
        } else {
          Toast.makeText(getContext() , "Passwords don't match", Toast.LENGTH_SHORT).show();
        }

      }
    });
  }
}
