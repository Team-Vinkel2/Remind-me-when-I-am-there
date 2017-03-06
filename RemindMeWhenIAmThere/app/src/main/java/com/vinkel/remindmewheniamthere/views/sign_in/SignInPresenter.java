package com.vinkel.remindmewheniamthere.views.sign_in;


import com.vinkel.remindmewheniamthere.models.base.IUser;
import com.vinkel.remindmewheniamthere.network.base.IUserData;
import com.vinkel.remindmewheniamthere.providers.base.IUserFactory;
import com.vinkel.remindmewheniamthere.views.sign_in.base.ISignInContracts;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignInPresenter implements ISignInContracts.Presenter {

  private ISignInContracts.View view;
  private IUserData userData;
  private IUserFactory userFactory;

  @Inject
  public SignInPresenter(IUserData userData, IUserFactory userFactory) {
    this.userData = userData;
    this.userFactory = userFactory;
  }

  @Override
  public void setView(ISignInContracts.View view) {
    this.view = view;
  }

  @Override
  public void start() {

  }

  @Override
  public void signIn(String username, String password) {
    IUser user = userFactory.getSignInUser(username, password);

    userData.signIn(user)
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
