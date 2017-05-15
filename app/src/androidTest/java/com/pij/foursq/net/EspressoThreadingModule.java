package com.pij.foursq.net;

import android.os.AsyncTask;

import com.pij.foursq.ThreadingModule;

import javax.inject.Named;

import dagger.Provides;
import rx.Scheduler;
import rx.schedulers.Schedulers;

/**
 * <p>Created on 16/05/2017.</p>
 * @author Pierrejean
 */

public class EspressoThreadingModule extends ThreadingModule {

    @Provides
    @Named(BACKGROUND_THREAD)
    Scheduler provideBackground() {
        return Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
