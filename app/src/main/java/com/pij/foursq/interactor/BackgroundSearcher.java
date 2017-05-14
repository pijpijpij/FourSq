package com.pij.foursq.interactor;

import com.pij.foursq.model.Place;

import java.util.List;

import javax.inject.Inject;

import rx.Scheduler;
import rx.Single;

/**
 * <p>Created on 13/05/2017.</p>
 * @author Pierrejean
 */

public class BackgroundSearcher implements Searcher {

    private final Searcher delegate;
    private final Scheduler background;
    private final Scheduler foreground;

    @Inject
    public BackgroundSearcher(Searcher delegate, Scheduler background, Scheduler foreground) {
        this.delegate = delegate;
        this.background = background;
        this.foreground = foreground;
    }

    @Override

    public Single<List<Place>> findByName(String name) {
        return delegate.findByName(name)
                       .doOnSuccess(n -> System.out.println("PJC findByName1 " + Thread.currentThread().getName()))
                       .subscribeOn(background)
                       .observeOn(foreground)
                       .doOnSuccess(n -> System.out.println("PJC findByName2 " + Thread.currentThread().getName()));
    }
}
