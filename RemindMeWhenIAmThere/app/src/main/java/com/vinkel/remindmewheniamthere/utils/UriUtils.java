package com.vinkel.remindmewheniamthere.utils;

import android.net.Uri;
import com.vinkel.remindmewheniamthere.utils.base.IUriParser;

public class UriUtils implements IUriParser {
  @Override
  public Uri parseUri(String uri) {
    return Uri.parse(uri);
  }
}
