package com.vinkel.remindmewheniamthere.views.settings;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.views.settings.base.ISettingsContracts;
import com.vinkel.remindmewheniamthere.views.sign_in.base.ISignInContracts;

import javax.inject.Inject;

public class SettingsActivity extends AppCompatActivity {

    @Inject
    ISettingsContracts.Presenter settingsPresenter;
    ISettingsContracts.View settingsView;

    @Inject
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.injectMembers();
        this.settingsPresenter.start();
    }

    private void initializeViews() {
        this.settingsView = (ISettingsContracts.View) fragmentManager.findFragmentById(R.id.fragment_settings);
    }

    private void setup() {
        settingsPresenter.setView(settingsView);
        settingsView.setPresenter(settingsPresenter);
    }

    private void injectMembers() {
        ((RMWITApplication) this.getApplication())
                .getComponent()
                .getActivityComponent(new ActivityModule(this, this.getSupportFragmentManager()))
                .inject(this);
    }


}
