package com.pij.foursq.ui.search;

import com.pij.foursq.interactor.Searcher;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * <p>Created on 13/05/2017.</p>
 * @author Pierrejean
 */

class SearchViewModel {

    private final PublishSubject<String> names = PublishSubject.create();
    private final Observable<ResultEvent> places;
    private final Searcher searcher;

    /**
     * The debouncer takes threading out of the View Model.
     */
    public SearchViewModel(Searcher searcher, Observable.Transformer<String, String> debouncer) {
        this.searcher = searcher;
        places = names.compose(debouncer).switchMap(this::oneOffSearch);
    }

    /**
     * @return Not ever supposed to error.
     */
    private Observable<ResultEvent> oneOffSearch(String name) {
        return searcher.findByName(name)
                       .map(places -> ResultEvent.completed(name, places))
                       // That's good enough for this
                       .doOnError(Throwable::printStackTrace)
                       .onErrorReturn(error -> ResultEvent.failed(name, error))
                       .toObservable()
                       .startWith(Observable.just(ResultEvent.started(name)))
                ;
    }

    void search(String name) {
        names.onNext(name);
    }

    Observable<ResultEvent> places() {
        return places;
    }

}
