package com.pij.foursq.interactor;

import com.pij.foursq.model.Place;

import java.util.List;

import rx.Single;

/**
 * <p>Created on 13/05/2017.</p>
 * @author Pierrejean
 */

public interface Searcher {

    Single<List<Place>> findByName(String name);
}
