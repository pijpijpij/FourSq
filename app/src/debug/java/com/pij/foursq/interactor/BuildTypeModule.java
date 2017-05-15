package com.pij.foursq.interactor;

import com.pij.foursq.net.QueryParamsInterceptor;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Pierrejean
 */

@Module
public abstract class BuildTypeModule {

    @Provides
    static OkHttpClient provideOkHttpClient(QueryParamsInterceptor queryParamsInterceptor) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(queryParamsInterceptor).addInterceptor(logging).build();
    }

}
