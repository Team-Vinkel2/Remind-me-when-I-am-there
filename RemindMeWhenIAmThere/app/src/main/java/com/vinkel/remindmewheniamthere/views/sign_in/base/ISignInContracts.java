package com.vinkel.remindmewheniamthere.views.sign_in.base;


import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.base.IView;

public interface ISignInContracts {

  interface View extends IView<Presenter> {
    void completed();
    void displayMessage(String message);
  }

  interface Presenter extends IPresenter<View> {
    void signIn(String username, String password);
  }
}
