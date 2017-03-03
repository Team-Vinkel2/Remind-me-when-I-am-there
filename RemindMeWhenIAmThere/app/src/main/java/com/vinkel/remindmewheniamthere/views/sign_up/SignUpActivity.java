package com.vinkel.remindmewheniamthere.views.sign_up;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.views.sign_up.base.ISignUpContracts;

import javax.inject.Inject;

public class SignUpActivity extends AppCompatActivity {

    @Inject
    ISignUpContracts.Presenter signUpPresenter;
    ISignUpContracts.View signUpView;

    @Inject
    FragmentManager fragmentManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.injectMembers();
        this.initializeViews();
        this.setup();
        this.signUpPresenter.start();
    }

    private void initializeViews() {
        this.signUpView = (ISignUpContracts.View) fragmentManger.findFragmentById(R.id.fragment_sign_up);
    }

    private void setup() {
        signUpPresenter.setView(signUpView);
        signUpView.setPresenter(signUpPresenter);
    }

    private void injectMembers() {
        ((RMWITApplication) this.getApplication())
                .getComponent()
                .getActivityComponent(new ActivityModule(this, this.getSupportFragmentManager()))
                .inject(this);
    }
}
