package com.pij.foursq.net;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * <p>Created on 15/05/2017.</p>
 * @author Pierrejean
 */
@Module
public class MockNetConfigModule extends NetConfigModule {

    private final String endpoint;

    public MockNetConfigModule(String endpoint) {
        this.endpoint = endpoint;
    }

    @Provides
    @Named(NetConfigModule.ENDPOINT)
    public String provideEndpoint() {
        return endpoint;
    }

}
