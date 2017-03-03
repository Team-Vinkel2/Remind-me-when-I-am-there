package com.vinkel.remindmewheniamthere.views.sign_in;


import com.vinkel.remindmewheniamthere.views.sign_in.base.ISignInContracts;

import javax.inject.Inject;

public class SignInPresenter implements ISignInContracts.Presenter {

    private ISignInContracts.View view;

    @Inject
    public SignInPresenter() {

    }

    @Override
    public void setView(ISignInContracts.View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }
}
