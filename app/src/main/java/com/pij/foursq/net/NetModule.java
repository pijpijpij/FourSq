package com.pij.foursq.net;

import com.pij.foursq.BuildConfig;
import com.pij.foursq.net.model.FourSqAdapterFactory;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import static org.apache.commons.lang3.tuple.Pair.of;

/**
 * @author Pierrejean
 */
@Module
public abstract class NetModule {

    @Provides
    static FourSquareApi provideFourSquareApi(Retrofit retrofit) {
        return retrofit.create(FourSquareApi.class);
    }

    @Provides
    static Retrofit provideRetrofit(Converter.Factory converterFactory, CallAdapter.Factory callAdapterFactory,
                                    OkHttpClient client) {
        return new Retrofit.Builder().addConverterFactory(converterFactory)
                                     .addCallAdapterFactory(callAdapterFactory)
                                     .baseUrl(BuildConfig.FOURSQUARE_ENDPOINT).client(client)
                                     .build();
    }

    @Provides
    static CallAdapter.Factory provideCallAdapterFactory() {
        return RxJavaCallAdapterFactory.create();
    }

    @Provides
    static Converter.Factory provideMoshiConverterFactory() {
        JsonAdapter.Factory adapterFactory = FourSqAdapterFactory.create();
        Moshi moshi = new Moshi.Builder().add(adapterFactory).build();
        return MoshiConverterFactory.create(moshi);
    }

    @Provides
    static QueryParamsInterceptor provideAuthenticatingInterceptor() {
        List<Pair<String, String>> pairs = new ArrayList<>();
        pairs.add(of("client_id", BuildConfig.CLIENT_ID));
        pairs.add(of("client_secret", BuildConfig.CLIENT_SECRET));
        pairs.add(of("v", "20170513"));
        pairs.add(of("m", "foursquare"));

        return new QueryParamsInterceptor(pairs);
    }
}
