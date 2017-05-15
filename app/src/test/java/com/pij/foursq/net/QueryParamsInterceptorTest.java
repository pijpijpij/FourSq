package com.pij.foursq.net;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;

import static java.util.Collections.singletonList;
import static org.apache.commons.lang3.tuple.Pair.of;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * <p>Created on 14/05/2017.</p>
 * @author Pierrejean
 */
public class QueryParamsInterceptorTest {

    @Test
    public void test_addsOneParameter() throws IOException {
        // given
        Interceptor.Chain mockChain = mock(Interceptor.Chain.class);
        HttpUrl url = HttpUrl.parse("https://nowhere.com");
        //noinspection ConstantConditions
        when(mockChain.request()).thenReturn(new Request.Builder().url(url).build());

        //noinspection unchecked
        QueryParamsInterceptor sut = new QueryParamsInterceptor(singletonList(of("client_id", "id")));
        sut.intercept(mockChain);

        // when
        ArgumentCaptor<Request> request = ArgumentCaptor.forClass(Request.class);
        verify(mockChain).proceed(request.capture());

        // then
        assertThat(request.getValue().url().queryParameter("client_id")).isEqualTo("id");
    }

    @Test
    public void test_addsTwoParameters() throws IOException {
        // given
        Interceptor.Chain mockChain = mock(Interceptor.Chain.class);
        HttpUrl url = HttpUrl.parse("https://nowhere.com");
        //noinspection ConstantConditions
        when(mockChain.request()).thenReturn(new Request.Builder().url(url).build());

        //noinspection unchecked
        List<Pair<String, String>> params = new ArrayList<>();
        params.add(of("client_id", "id"));
        params.add(of("another", "a value"));
        QueryParamsInterceptor sut = new QueryParamsInterceptor(params);
        sut.intercept(mockChain);

        // when
        ArgumentCaptor<Request> request = ArgumentCaptor.forClass(Request.class);
        verify(mockChain).proceed(request.capture());

        // then
        assertThat(request.getValue().url().queryParameter("client_id")).isEqualTo("id");
        assertThat(request.getValue().url().queryParameter("another")).isEqualTo("a value");
    }

}