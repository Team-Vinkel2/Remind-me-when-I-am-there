package com.vinkel.remindmewheniamthere.activities;

import android.graphics.Color;
import android.os.Bundle;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.vinkel.remindmewheniamthere.R;

public class IntroActivity extends AppIntro {
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    this.setupWelcomeScreen();
  }

  private void setupWelcomeScreen() {
    this.addSlide(AppIntroFragment.newInstance("Title", "TestDesc", R.drawable.ic_arrow_back_white, Color.parseColor("#000000")));
    this.addSlide(AppIntroFragment.newInstance("Second", "TestDesc", R.drawable.ic_navigate_before_white, Color.parseColor("#5645F1")));
    this.showSkipButton(false);
  }
}
