package com.vinkel.remindmewheniamthere.views.sign_up;


import android.widget.EditText;
import android.widget.Toast;

import com.vinkel.remindmewheniamthere.models.base.IUser;
import com.vinkel.remindmewheniamthere.models.json.ApiError;
import com.vinkel.remindmewheniamthere.network.UserData;
import com.vinkel.remindmewheniamthere.network.base.IUserData;
import com.vinkel.remindmewheniamthere.providers.UserFactory;
import com.vinkel.remindmewheniamthere.providers.base.IUserFactory;
import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.sign_up.base.ISignUpContracts;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignUpPresenter implements ISignUpContracts.Presenter {

  private ISignUpContracts.View view;
  private IUserData userData;
  private IUserFactory factory;

  @Inject
  public SignUpPresenter(IUserData userData, IUserFactory factory) {
    this.userData = userData;
    this.factory = factory;
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
    IUser user = factory.getSignUpUser(username, firstname, password, email);

    userData.signUp(user)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<IUser>() {
          @Override
          public void onSubscribe(Disposable d) {

          }

          @Override
          public void onNext(IUser value) {
            view.completed();
          }

          @Override
          public void onError(Throwable e) {

          }

          @Override
          public void onComplete() {

          }
        });
  }
}
