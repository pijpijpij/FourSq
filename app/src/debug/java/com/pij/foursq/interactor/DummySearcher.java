package com.pij.foursq.interactor;

import com.pij.foursq.ui.model.Place;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Single;

import static java.util.Arrays.asList;
import static org.apache.commons.collections4.IterableUtils.filteredIterable;
import static org.apache.commons.collections4.IterableUtils.toList;
import static org.apache.commons.collections4.IterableUtils.transformedIterable;

/**
 * <p>Created on 13/05/2017.</p>
 * @author Pierrejean
 */

public class DummySearcher implements Searcher {

    @Inject
    public DummySearcher() {
    }

    @Override

    public Single<List<Place>> findByName(String name) {
        List<String> tokens = asList(name.split("\\s+"));
        Iterable<String> usefulTokens = filteredIterable(tokens, StringUtils::isNotBlank);
        Iterable<Place> places = transformedIterable(usefulTokens, token -> Place.create(token, token));
        return Single.just(toList(places));
    }
}
