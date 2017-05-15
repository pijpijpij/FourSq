package com.pij.foursq.ui.search;

import com.pij.dagger.ActivityScope;
import com.pij.foursq.ThreadingModule;
import com.pij.foursq.interactor.Searcher;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.Scheduler;

/**
 * @author Pierrejean
 */
@Module
abstract class SearchActivityModule {

    @Provides
    @ActivityScope
    static SearchViewModel provideSearchViewModel(Searcher searcher, Observable.Transformer<String, String> debouncer) {
        return new SearchViewModel(searcher, debouncer);
    }

    @Provides
    @ActivityScope
    static Observable.Transformer<String, String> provideDebouncer(
            @Named(ThreadingModule.BACKGROUND_THREAD) Scheduler background,
            @Named(ThreadingModule.MAIN_THREAD) Scheduler foreground) {
        return input -> input.debounce(300, TimeUnit.MILLISECONDS, background).observeOn(foreground);
    }
}
