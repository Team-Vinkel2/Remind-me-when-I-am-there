package com.vinkel.remindmewheniamthere.views.sign_in;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.views.sign_in.base.ISignInContracts;

import javax.inject.Inject;

public class SignInActivity extends AppCompatActivity {

    @Inject
    ISignInContracts.Presenter signInPresenter;
    ISignInContracts.View signInView;

    @Inject
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.injectMembers();
        this.initializeViews();
        this.setup();
        this.signInPresenter.start();
    }

    private void initializeViews() {
        this.signInView = (ISignInContracts.View) fragmentManager.findFragmentById(R.id.fragment_sign_in);
    }

    private void setup() {
        signInPresenter.setView(signInView);
        signInView.setPresenter(signInPresenter);
    }

    private void injectMembers() {
        ((RMWITApplication) this.getApplication())
                .getComponent()
                .getActivityComponent(new ActivityModule(this, this.getSupportFragmentManager()))
                .inject(this);
    }
}
