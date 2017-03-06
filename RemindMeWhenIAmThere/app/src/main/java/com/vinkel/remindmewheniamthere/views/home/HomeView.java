package com.vinkel.remindmewheniamthere.views.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.models.base.IReminder;
import com.vinkel.remindmewheniamthere.ui.adapters.ReminderAdapter;
import com.vinkel.remindmewheniamthere.ui.fragments.ToolbarFragment;
import com.vinkel.remindmewheniamthere.views.home.base.IHomeContracts;
import java.util.ArrayList;

public class HomeView extends ToolbarFragment implements IHomeContracts.View {

  private IHomeContracts.Presenter presenter;

  private SwipeRefreshLayout swipeRefreshLayout;
  private ListView listView;
  private ReminderAdapter adapter;

  private boolean adapterIsSet = false;


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


  public void prepareView(View view) {
    this.listView = (ListView) view.findViewById(R.id.lv_reminders);
    this.swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
    this.adapter = new ReminderAdapter(getContext(), new ArrayList<IReminder>());

    swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override
      public void onRefresh() {
        presenter.loadReminders();
      }
    });
  }

  public void setReminders(ArrayList<IReminder> reminders) {
    this.adapter.clear();
    this.adapter.addAll(reminders);
    if (!this.adapterIsSet) {
      this.listView.setAdapter(this.adapter);
      this.adapterIsSet = true;
    }
  }

  @Override
  public void setRefreshing(boolean value) {
    swipeRefreshLayout.setRefreshing(value);
  }
}
