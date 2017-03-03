package com.vinkel.remindmewheniamthere.data.base;

import android.support.annotation.Nullable;
import io.reactivex.Observable;
import java.util.List;

public interface IBaseDatabase<T> {
  Observable<T> findById(long id);

  Observable<List<T>> getAll();

  Observable<Long> add(final T object);

  Observable<Long> update(final T object);

  Observable<Boolean> delete(final T object);

  Observable<List<T>> find(final String whereClause, @Nullable final String... whereClauseArgs);
}
