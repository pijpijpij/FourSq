package com.pij.foursq.net.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * <p>Created on 14/05/2017.</p>
 * @author Pierrejean
 */
@AutoValue
public abstract class Venue {

    public static JsonAdapter<Venue> jsonAdapter(Moshi moshi) {
        return new AutoValue_Venue.MoshiJsonAdapter(moshi);
    }

    public abstract String id();

    public abstract String name();

}
