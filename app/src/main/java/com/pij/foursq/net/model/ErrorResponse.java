package com.pij.foursq.net.model;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * An example: {"meta":{"code":400,"errorType":"invalid_auth","errorDetail":"Missing access credentials. See
 * https:\/\/developer.foursquare.com\/docs\/oauth.html for details.","requestId":"59187bd54c1f672298f53451"},
 * "response":{}}
 * <p>Created on 14/05/2017.</p>
 * @author Pierrejean
 */
@AutoValue
public abstract class ErrorResponse {

    public static JsonAdapter<ErrorResponse> jsonAdapter(Moshi moshi) {
        return new AutoValue_ErrorResponse.MoshiJsonAdapter(moshi);
    }

    public abstract Meta meta();

}
