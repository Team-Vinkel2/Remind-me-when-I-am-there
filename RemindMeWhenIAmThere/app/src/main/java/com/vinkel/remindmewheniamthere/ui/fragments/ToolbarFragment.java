package com.vinkel.remindmewheniamthere.ui.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.MenuRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.vinkel.remindmewheniamthere.R;
import com.vinkel.remindmewheniamthere.RMWITApplication;
import com.vinkel.remindmewheniamthere.config.di.annotations.IntentFactoryForActivity;
import com.vinkel.remindmewheniamthere.config.di.modules.ActivityModule;
import com.vinkel.remindmewheniamthere.providers.base.IIntentFactory;
import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawer;
import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawerItem;
import com.vinkel.remindmewheniamthere.ui.components.drawer.base.IDrawerItemFactory;
import com.vinkel.remindmewheniamthere.ui.fragments.base.IToolbar;
import com.vinkel.remindmewheniamthere.utils.base.IUserSession;
import com.vinkel.remindmewheniamthere.views.add_reminder.AddReminderActivity;
import com.vinkel.remindmewheniamthere.views.home.HomeActivity;
import com.vinkel.remindmewheniamthere.views.settings.SettingsActivity;
import com.vinkel.remindmewheniamthere.views.sign_in.SignInActivity;
import com.vinkel.remindmewheniamthere.views.sign_up.SignUpActivity;
import javax.inject.Inject;

public class ToolbarFragment extends Fragment implements IToolbar {

  @Inject
  IDrawer navigationDrawer;
  @Inject
  @IntentFactoryForActivity
  IIntentFactory intentFactory;
  @Inject
  IDrawerItemFactory drawerItemFactory;
  @Inject
  IUserSession session;

  private Toolbar toolbar;
  private ActionBar actionBar;
  private AppCompatActivity currentActivity;

  public ToolbarFragment() {

  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_toolbar, container, false);
  }

  @Override
  public void onStart() {
    super.onStart();
    injectMembers();

    currentActivity = (AppCompatActivity) getActivity();
    toolbar = (Toolbar) currentActivity.findViewById(R.id.toolbar);
    currentActivity.setSupportActionBar(toolbar);
    actionBar = currentActivity.getSupportActionBar();
  }

  @Override
  public void inflateMenu(@MenuRes int menuRes, Menu menu, MenuInflater menuInflater) {
    super.onCreateOptionsMenu(menu, menuInflater);
    menu.clear();
    currentActivity.getMenuInflater().inflate(menuRes, menu);
  }

  public void setNavigationOnClickListener() {
    setNavigationOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        NavUtils.navigateUpFromSameTask(currentActivity);
      }
    });
  }

  @Override
  public void setNavigationOnClickListener(View.OnClickListener clickListener) {
    actionBar.setDisplayHomeAsUpEnabled(true);
    actionBar.setDisplayShowHomeEnabled(true);
    toolbar.setNavigationOnClickListener(clickListener);
  }

  @Override
  public void setNavigationDrawer(@LayoutRes long selectedItem) {
    createDrawerBuilder();

    final Intent homeIntent = intentFactory.getIntentWithSetToFrontFlag(HomeActivity.class);
    final Intent homeIntentNoAnimation = intentFactory.getIntentWithNoAnimatedTransitionFlag(HomeActivity.class);
    final Intent signUpIntent = intentFactory.getIntentWithSetToFrontFlag(SignUpActivity.class);
    final Intent signInIntent = intentFactory.getIntentWithSetToFrontFlag(SignInActivity.class);
    final Intent addReminderIntent = intentFactory.getIntentWithSetToFrontFlag(AddReminderActivity.class);
    final Intent settingsIntent = intentFactory.getIntentWithSetToFrontFlag(SettingsActivity.class);
    boolean isLogged = session.isUserLoggedIn();
    IDrawerItem signOut = drawerItemFactory
        .createPrimaryDrawerItem()
        .withName(R.string.drawer_log_out);

    IDrawerItem signIn = drawerItemFactory
        .createPrimaryDrawerItem()
        .withIdentifier(R.layout.activity_sign_in)
        .withName(R.string.drawer_sign_in);

    IDrawerItem signUp = drawerItemFactory
        .createPrimaryDrawerItem()
        .withIdentifier(R.layout.activity_sign_up)
        .withName(R.string.drawer_sign_up);

    if (!isLogged) {

      navigationDrawer.withDrawerItems(
          signIn,
          signUp
      )

          .withOnDrawerItemClickListener(new IDrawer.OnDrawerItemClickListener() {
            @Override
            public boolean onClick(View view, int position) {
              switch (position) {
                case 1:
                  startActivity(homeIntent);
                  break;
                case 3:
                  startActivity(settingsIntent);
                  break;
                case 4:
                  startActivity(addReminderIntent);
                  break;
                case 6:
                  startActivity(signInIntent);
                  break;
                case 7:
                  startActivity(signUpIntent);
                  break;
              }

              return false;
            }
          });

      navigationDrawer.withSelectedItem(selectedItem).build();
    } else {
      navigationDrawer.withDrawerItems(
           signOut
      )

          .withOnDrawerItemClickListener(new IDrawer.OnDrawerItemClickListener() {
            @Override
            public boolean onClick(View view, int position) {
              switch (position) {
                case 1:
                  startActivity(homeIntent);
                  break;
                case 3:
                  startActivity(settingsIntent);
                  break;
                case 4:
                  startActivity(addReminderIntent);
                  break;
                case 6:
                  session.clearSession();
                  startActivity(homeIntentNoAnimation);
                  getActivity().finish();
                  break;
              }

              return false;
            }
          });

      navigationDrawer.withSelectedItem(selectedItem).build();
    }
  }

  private void createDrawerBuilder() {
    boolean isLogged = session.isUserLoggedIn();
    String username = "Guest";
    String email = "Guest@mail.com";

    if (isLogged) {
      username = session.getUsername();
      email = session.getEmail();
    }

    AccountHeader accountHeader = new AccountHeaderBuilder()
        .withActivity(currentActivity)
        .withTranslucentStatusBar(true)
        .withHeaderBackground(R.drawable.drawer_background)
        .addProfiles(
            new ProfileDrawerItem().withName(username).withEmail(email)
        ).build();

    IDrawerItem home = drawerItemFactory
        .createPrimaryDrawerItem()
        .withIdentifier(R.layout.activity_home)
        .withName(R.string.drawer_home);

    IDrawerItem settings = drawerItemFactory
        .createPrimaryDrawerItem()
        .withIdentifier(R.layout.activity_settings)
        .withName(R.string.drawer_settings);

    IDrawerItem addReminder = drawerItemFactory
        .createPrimaryDrawerItem()
        .withIdentifier(R.layout.activity_add_reminder)
        .withName(R.string.drawer_add_reminder);

    navigationDrawer
        .withToolbar(toolbar)
        .withWidth(270)
        .withAccountHeader(accountHeader)
        .withDrawerItems(home, drawerItemFactory.createDividerDrawerItem(), settings, addReminder, drawerItemFactory.createDividerDrawerItem());

  }

  private void injectMembers() {
    RMWITApplication
        .getInstance()
        .getComponent()
        .getActivityComponent(new ActivityModule(getActivity()))
        .inject(this);
  }
}
