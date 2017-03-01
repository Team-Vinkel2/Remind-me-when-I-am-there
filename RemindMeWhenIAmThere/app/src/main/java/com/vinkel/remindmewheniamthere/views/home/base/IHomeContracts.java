package com.vinkel.remindmewheniamthere.views.home.base;

import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.base.IView;

public interface IHomeContracts {

  interface View extends IView<Presenter> {
    void setTitle(String title);
  }

  interface Presenter extends IPresenter<View> {}

}
