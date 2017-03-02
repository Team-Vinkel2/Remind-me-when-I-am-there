package com.vinkel.remindmewheniamthere.views.sign_up.base;

import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.base.IView;

public interface ISignUpContracts {

    interface View extends IView<Presenter> {
    }

    interface Presenter extends IPresenter<View> {}
}
