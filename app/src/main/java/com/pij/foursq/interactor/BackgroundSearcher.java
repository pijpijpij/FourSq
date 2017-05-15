package com.pij.foursq.interactor;

import com.pij.foursq.ui.model.Place;

import java.util.List;

import rx.Scheduler;
import rx.Single;

/**
 * TODO Unit test worth it?
 * <p>Created on 13/05/2017.</p>
 * @author Pierrejean
 */

public class BackgroundSearcher implements Searcher {

    private final Searcher delegate;
    private final Scheduler background;
    private final Scheduler foreground;

    public BackgroundSearcher(Searcher delegate, Scheduler background, Scheduler foreground) {
        this.delegate = delegate;
        this.background = background;
        this.foreground = foreground;
    }

    @Override

    public Single<List<Place>> findByName(String name) {
        return delegate.findByName(name).subscribeOn(background).observeOn(foreground);
    }
}
