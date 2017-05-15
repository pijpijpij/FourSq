package com.pij.foursq;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;

import com.pij.foursq.interactor.BackgroundSearcher;
import com.pij.foursq.interactor.DtoConverter;
import com.pij.foursq.interactor.NetSearcher;
import com.pij.foursq.interactor.Searcher;
import com.pij.foursq.interactor.VenueConverter;
import com.pij.foursq.net.model.ErrorResponse;
import com.pij.foursq.net.model.Venue;
import com.pij.foursq.ui.model.Place;
import com.pij.foursq.ui.search.SearchActivitySubComponent;

import java.lang.annotation.Annotation;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
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

    // Interactor and Co.
    @Provides
    static Searcher provideSearcher(NetSearcher searcher) {
        return new BackgroundSearcher(searcher, Schedulers.io(), AndroidSchedulers.mainThread());
    }

    @Provides
    static Converter<ResponseBody, ErrorResponse> provideErrorConverter(Retrofit retrofit) {
        return retrofit.responseBodyConverter(ErrorResponse.class, new Annotation[0]);
    }

    @Binds
    abstract DtoConverter<Venue, Place> provideVenueConverter(VenueConverter venueConverter);

}