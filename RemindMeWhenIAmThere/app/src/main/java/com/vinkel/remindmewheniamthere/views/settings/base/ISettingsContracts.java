package com.vinkel.remindmewheniamthere.views.settings.base;


import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.base.IView;

public interface ISettingsContracts {

    interface  View extends IView<Presenter> {

    }

    interface Presenter extends IPresenter<View> {

    }

}
