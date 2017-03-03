package com.vinkel.remindmewheniamthere.views.sign_up;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.sign_up.base.ISignUpContracts;

public class SignUpView extends Fragment implements ISignUpContracts.View {

    private IPresenter<ISignUpContracts.View> presenter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        prepareView(view);
        return view;
    }

    @Override
    public void setPresenter(ISignUpContracts.Presenter presenter) {
        this.presenter = presenter;
    }

    public void prepareView(View view) {

    }
}
