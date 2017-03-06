package com.vinkel.remindmewheniamthere.views.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.ui.fragments.ToolbarFragment;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.home.base.IHomeContracts;

import java.util.Timer;

public class HomeView extends ToolbarFragment implements IHomeContracts.View {

  private IPresenter<IHomeContracts.View> presenter;

  private TextView tvTitle;
  private SwipeRefreshLayout swipeRefreshLayout;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_home, container, false);
    this.prepareView(view);
    return view;
  }

  @Override
  public void setPresenter(IHomeContracts.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void setTitle(String title) {
    this.tvTitle.setText(title);
  }

  public void prepareView(View view) {
    this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
    this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        //refresh
        swipeRefreshLayout.setRefreshing(false);
      }
    });
  }
}
