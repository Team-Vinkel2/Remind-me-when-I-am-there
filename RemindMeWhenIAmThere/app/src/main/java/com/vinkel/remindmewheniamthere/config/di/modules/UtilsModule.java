package com.vinkel.remindmewheniamthere.config.di.modules;

import com.vinkel.remindmewheniamthere.utils.UriUtils;
import com.vinkel.remindmewheniamthere.utils.base.IUriParser;
import dagger.Module;
import dagger.Provides;

@Module
public class UtilsModule {

  @Provides
  IUriParser provideUriParser() {
    return new UriUtils();
  }
}
