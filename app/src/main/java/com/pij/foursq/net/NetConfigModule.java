package com.pij.foursq.net;

import com.pij.foursq.BuildConfig;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * <p>Created on 15/05/2017.</p>
 * @author Pierrejean
 */
@Module
public class NetConfigModule {

    static final String ENDPOINT = "endpoint";

    @Provides
    @Named(NetConfigModule.ENDPOINT)
    public String provideEndpoint() {
        return BuildConfig.FOURSQUARE_ENDPOINT;
    }

}
