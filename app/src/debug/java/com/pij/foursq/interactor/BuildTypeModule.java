package com.pij.foursq.interactor;

import com.pij.foursq.net.QueryParamsInterceptor;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.logging.HttpLoggingInterceptor;

import static java.util.Arrays.asList;

/**
 * @author Pierrejean
 */

@Module
public abstract class BuildTypeModule {

    @Provides
    static List<Interceptor> provideInterceptors(QueryParamsInterceptor queryParamsInterceptor) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return asList(queryParamsInterceptor, logging);
    }

}
