package com.pij.foursq.net.model;

import com.ryanharter.auto.value.moshi.MoshiAdapterFactory;
import com.squareup.moshi.JsonAdapter;

/**
 * <p>Created on 14/05/2017.</p>
 * @author Pierrejean
 */
@MoshiAdapterFactory
public abstract class FourSqAdapterFactory implements JsonAdapter.Factory {

    public static JsonAdapter.Factory create() {
        return new AutoValueMoshi_FourSqAdapterFactory();
    }

}