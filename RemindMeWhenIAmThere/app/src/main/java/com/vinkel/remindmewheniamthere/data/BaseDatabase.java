package com.vinkel.remindmewheniamthere.data;

import android.support.annotation.Nullable;
import com.orm.SugarRecord;
import com.vinkel.remindmewheniamthere.data.base.IBaseDatabase;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public abstract class BaseDatabase<T> implements IBaseDatabase<T> {

  protected static final String AND_OPERATOR = " and ";
  protected static final String OR_OPERATOR = " or ";
  protected static final String BOOLEAN_TRUE = "1";
  protected static final String BOOLEAN_FALSE = "0";

  protected Class<? extends T> classSingle;

  public BaseDatabase(Class<? extends T> classSingle){
    this.classSingle = classSingle;
  }

  @Override
  public Observable<T> findById(final long id) {
    return Observable.create(new ObservableOnSubscribe<T>() {
      @Override
      public void subscribe(ObservableEmitter<T> e) throws Exception {
        T foundObject = SugarRecord.findById(classSingle, id);
        e.onNext(foundObject);
      }
    })
        .subscribeOn(Schedulers.io());
  }

  @Override
  public Observable<List<T>> getAll() {
    return Observable.create(new ObservableOnSubscribe<List<T>>() {
      @Override
      public void subscribe(ObservableEmitter<List<T>> e) throws Exception {
        List allObjects =  SugarRecord.listAll(classSingle);
        e.onNext(allObjects);
      }
    })
        .subscribeOn(Schedulers.io());
  }

  @Override
  public Observable<Long> add(final T object) {
    return Observable.create(new ObservableOnSubscribe<Long>() {
      @Override
      public void subscribe(ObservableEmitter<Long> e) throws Exception {
        Long id = SugarRecord.save(object);

        e.onNext(id);
      }
    })
        .subscribeOn(Schedulers.io());
  }

  @Override
  public Observable<Long> update(final T object) {
    return Observable.create(new ObservableOnSubscribe<Long>() {
      @Override
      public void subscribe(ObservableEmitter<Long> e) throws Exception {
        Long updatedObjectsCount = SugarRecord.update(object);
        e.onNext(updatedObjectsCount);
      }
    })
        .subscribeOn(Schedulers.io());
  }

  @Override
  public Observable<Boolean> delete(final T object) {
    return Observable.create(new ObservableOnSubscribe<Boolean>() {
      @Override
      public void subscribe(ObservableEmitter<Boolean> e) throws Exception {
        boolean deleted = SugarRecord.delete(object);

        e.onNext(deleted);
      }
    })
        .subscribeOn(Schedulers.io());
  }

  @Override
  public Observable<List<T>> find(final String whereClause, @Nullable final String... whereClauseArgs) {
    return Observable.create(new ObservableOnSubscribe<List<T>>() {
      @Override
      public void subscribe(ObservableEmitter<List<T>> e) throws Exception {
        List foundObjects = SugarRecord.find(classSingle, whereClause, whereClauseArgs);

        e.onNext(foundObjects);
      }
    })
        .subscribeOn(Schedulers.io());
  }
}
