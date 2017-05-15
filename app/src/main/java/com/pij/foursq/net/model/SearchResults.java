package com.pij.foursq.net.model;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.util.List;

/**
 * We only write the properties we need and ignore the rest of the data.
 * <p>Created on 14/05/2017.</p>
 * @author Pierrejean
 */
@AutoValue
public abstract class SearchResults {

    public static JsonAdapter<SearchResults> jsonAdapter(Moshi moshi) {
        return new AutoValue_SearchResults.MoshiJsonAdapter(moshi);
    }

    public abstract Meta meta();

    @Nullable
    public abstract Response response();

    @AutoValue
    public abstract static class Response {

        public static JsonAdapter<Response> jsonAdapter(Moshi moshi) {
            return new AutoValue_SearchResults_Response.MoshiJsonAdapter(moshi);
        }

        public abstract List<Venue> venues();

    }
}
