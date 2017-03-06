package com.vinkel.remindmewheniamthere.views.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.models.Reminder;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.providers.ReminderFactory;
import com.vinkel.remindmewheniamthere.ui.adapters.ReminderAdapter;
import com.vinkel.remindmewheniamthere.ui.fragments.ToolbarFragment;
import com.vinkel.remindmewheniamthere.views.base.IPresenter;
import com.vinkel.remindmewheniamthere.views.home.base.IHomeContracts;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import javax.inject.Inject;

public class HomeView extends ToolbarFragment implements IHomeContracts.View {

  private IPresenter<IHomeContracts.View> presenter;

  private TextView tvTitle;
  private SwipeRefreshLayout swipeRefreshLayout;
  ListView listView;
  private static ReminderAdapter adapter;

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
    this.listView = (ListView) view.findViewById(R.id.lv_reminders);
    this.tvTitle = (TextView) view.findViewById(R.id.tv_title);
    this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);

    //this needs to be checked and prob injected

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        //refresh
        swipeRefreshLayout.setRefreshing(false);
      }
    });
  }
  public void setReminders(ArrayList<IReminder> reminders) {
    adapter = new ReminderAdapter(getContext(), reminders);
    listView.setAdapter(adapter);
  }
}
