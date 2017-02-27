package com.vinkel.remindmewheniamthere.presenters.base;

import com.vinkel.remindmewheniamthere.views.base.IView;

public interface IPresenter<T> {
  void setView(T view);

  interface SamplePresenter extends IPresenter<IView.SampleView> {
    void help();
  }
}
