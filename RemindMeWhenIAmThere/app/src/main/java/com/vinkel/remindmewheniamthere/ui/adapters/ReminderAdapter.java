package com.vinkel.remindmewheniamthere.ui.adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.models.Reminder;
import com.vinkel.remindmewheniamthere.models.base.IReminder;

import org.w3c.dom.Text;

import java.util.ArrayList;

import javax.inject.Inject;

public class ReminderAdapter extends ArrayAdapter<IReminder> {

  private ArrayList<IReminder> dataSet;
  Context context;

  private static class ViewHolder {
    TextView title;
    TextView content;
    TextView dateoOrPlace;

  }

  public ReminderAdapter(Context context, ArrayList<IReminder> data) {
    super(context, R.layout.fragment_reminder, data);
    this.context = context;
    dataSet = data;
  }

  private int lastPosition = -1;

  @NonNull
  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    IReminder reminder = getItem(position);
    ViewHolder viewHolder; //check again

    final View result;

    if (convertView == null) {

      viewHolder = new ViewHolder();
      LayoutInflater inflater = LayoutInflater.from(getContext());
      convertView = inflater.inflate(R.layout.fragment_reminder, parent, false);
      viewHolder.title = (TextView) convertView.findViewById(R.id.reminder_name);
      viewHolder.content = (TextView) convertView.findViewById(R.id.reminder_content);
      viewHolder.dateoOrPlace = (TextView) convertView.findViewById(R.id.reminder_date_or_place);


      result = convertView;

      convertView.setTag(viewHolder);
    } else {
      viewHolder = (ViewHolder) convertView.getTag();
      result = convertView;
    }

    lastPosition = position;

    viewHolder.title.setText(Html.fromHtml("<b> Title: </b>" + reminder.getTitle()));
    viewHolder.content.setText(Html.fromHtml("<b> Content: </b>" + reminder.getContent()));
    if (reminder.getDateString() == null) {
      viewHolder.dateoOrPlace.setText(Html.fromHtml("<b> Location: </b>" + reminder.getLocationName()));
    } else {
      viewHolder.dateoOrPlace.setText(Html.fromHtml("<b> Date and Time: </b>" + reminder.getDateString()));
    }

    return convertView;
  }
}
