package com.vinkel.remindmewheniamthere.views.sign_in;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.sign_in.base.ISignInContracts;

public class SignInView extends Fragment implements ISignInContracts.View {

    private IPresenter<ISignInContracts.View> presenter;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup contrainer, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, contrainer, false);
        prepareView(view);
        return view;
    }
    @Override
    public void setPresenter(ISignInContracts.Presenter presenter) {

    }

    public void prepareView(View view) {

    }
}
