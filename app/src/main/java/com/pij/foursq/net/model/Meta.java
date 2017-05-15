package com.pij.foursq.net.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * <p>Created on 15/05/2017.</p>
 * @author Pierrejean
 */
@AutoValue
public abstract class Meta {

    public static JsonAdapter<Meta> jsonAdapter(Moshi moshi) {
        return new AutoValue_Meta.MoshiJsonAdapter(moshi);
    }

    public abstract int code();

    @Nullable
    public abstract String errorType();

    @Nullable
    public abstract String errorDetail();

}
