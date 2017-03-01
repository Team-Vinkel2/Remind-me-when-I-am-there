package com.vinkel.remindmewheniamthere.views.base;

public interface IPresenter<T extends IView> {
  void setView(T view);
}
