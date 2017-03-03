package com.vinkel.remindmewheniamthere.views.sign_up;


import com.vinkel.remindmewheniamthere.utils.base.IApplicationSettingsManager;
import com.vinkel.remindmewheniamthere.views.sign_up.base.ISignUpContracts;

import javax.inject.Inject;

public class SignUpPresenter implements ISignUpContracts.Presenter {

    private ISignUpContracts.View view;

    @Inject
    public SignUpPresenter() {
    }

    @Override
    public void setView(ISignUpContracts.View view) {
        this.view = view;
    }

    @Override
    public void start() {
    }
}
