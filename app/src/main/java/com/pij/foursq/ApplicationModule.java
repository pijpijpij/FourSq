package com.pij.foursq;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.pij.foursq.interactor.BackgroundSearcher;
import com.pij.foursq.interactor.DummySearcher;
import com.pij.foursq.interactor.Searcher;
import com.pij.foursq.search.SearchActivitySubComponent;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <p>Created on 08/04/2017.</p>
 * @author Pierrejean
 */
@Module(subcomponents = { SearchActivitySubComponent.class })
abstract class ApplicationModule {

    // Standard Android system elements.
    @Provides
    static Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    static AssetManager provideAssetManager(Context context) {
        return context.getAssets();
    }

    @Provides
    static Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    static ContentResolver provideContentResolver(Context context) {
        return context.getContentResolver();
    }

//    @Provides
//    static <T> Observable.Transformer<T, T> provideDebouncer() {
//        return input -> input.debounce(300, TimeUnit.MILLISECONDS, Schedulers.io())
//                             .observeOn(AndroidSchedulers.mainThread());
//    }


    // Interactor and Co.
    @Provides
    static Searcher provideSearcher(DummySearcher searcher) {
        return new BackgroundSearcher(searcher, Schedulers.io(), AndroidSchedulers.mainThread());
    }

}