package com.pij.foursq;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <p>Created on 08/04/2017.</p>
 * @author Pierrejean
 */
@Module
public class ThreadingModule {

    public static final String MAIN_THREAD = "main";
    public static final String BACKGROUND_THREAD = "background";

    @Provides
    @Named(MAIN_THREAD)
    Scheduler provideMainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Named(BACKGROUND_THREAD)
    Scheduler provideBackground() {
        return Schedulers.io();
    }

}