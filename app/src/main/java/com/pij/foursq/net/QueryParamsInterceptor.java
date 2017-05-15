package com.pij.foursq.net;

import android.support.annotation.NonNull;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>Created on 14/05/2017.</p>
 * @author Pierrejean
 */

public class QueryParamsInterceptor implements Interceptor {

    private final List<Pair<String, String>> parameters;

    public QueryParamsInterceptor(List<Pair<String, String>> parameters) {
        this.parameters = new ArrayList<>(parameters);
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl.Builder newUrl = request.url().newBuilder();
        IterableUtils.forEach(parameters, param -> newUrl.setQueryParameter(param.getLeft(), param.getRight()));
        Request newRequest = request.newBuilder().url(newUrl.build()).build();
        return chain.proceed(newRequest);
    }
}
