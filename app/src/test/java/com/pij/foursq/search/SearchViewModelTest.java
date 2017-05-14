package com.pij.foursq.search;

import com.pij.foursq.interactor.Searcher;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import rx.Single;
import rx.observers.TestSubscriber;

import static java.util.Collections.emptyList;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * <p>Created on 13/05/2017.</p>
 * @author Pierrejean
 */
public class SearchViewModelTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule();

    @Mock
    Searcher mockSearcher;
    private SearchViewModel sut;

    @Before
    public void setUp() throws Exception {
        sut = new SearchViewModel(mockSearcher, input -> input);
    }

    @Test
    public void test_StartsAFailedExchangeWithInProgress() {
        // given
        when(mockSearcher.findByName(anyString())).thenReturn(Single.error(new NullPointerException("some problem")));
        TestSubscriber<ResultEvent> subscriber = TestSubscriber.create();
        sut.places().subscribe(subscriber);

        // when
        sut.search("");

        // then
        subscriber.assertNoErrors();
        subscriber.assertValues(ResultEvent.started(), ResultEvent.failed("some problem"));
    }

    @Test
    public void test_StartsASuccessfulExchangeWithInProgress() {
        // given
        when(mockSearcher.findByName(anyString())).thenReturn(Single.just(emptyList()));
        TestSubscriber<ResultEvent> subscriber = TestSubscriber.create();
        sut.places().subscribe(subscriber);

        // when
        sut.search("");

        // then
        subscriber.assertNoErrors();
        subscriber.assertValues(ResultEvent.started(), ResultEvent.completed(emptyList()));
    }
}