package com.pij.foursq.ui.search;

import com.pij.dagger.ActivityScope;
import com.pij.foursq.interactor.Searcher;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Pierrejean
 */
@Module
abstract class SearchActivityModule {

    @Provides
    @ActivityScope
    static SearchViewModel provideSearchViewModel(Searcher searcher) {
        return new SearchViewModel(searcher,
                                   input -> input.debounce(300, TimeUnit.MILLISECONDS, Schedulers.io())
                                                 .observeOn(AndroidSchedulers.mainThread()));
    }

}
