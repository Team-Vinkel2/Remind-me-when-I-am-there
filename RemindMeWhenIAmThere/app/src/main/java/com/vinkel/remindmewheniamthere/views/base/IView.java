package com.vinkel.remindmewheniamthere.views.base;

public interface IView<T extends IPresenter> {
  void setPresenter(T presenter);
}
