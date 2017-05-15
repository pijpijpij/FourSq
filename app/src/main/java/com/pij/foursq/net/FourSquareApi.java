package com.pij.foursq.net;

import com.pij.foursq.net.model.SearchResults;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * <p>Created on 14/05/2017.</p>
 * @author Pierrejean
 */

public interface FourSquareApi {

    @GET("venues/search")
    Observable<Response<SearchResults>> search(@Query("near") String near);
}
