package com.vinkel.remindmewheniamthere.config.di.modules;

import dagger.Module;

@Module(includes = { LocalDatabaseModule.class, RemoteDataModule.class })
public class DataModule {
}
