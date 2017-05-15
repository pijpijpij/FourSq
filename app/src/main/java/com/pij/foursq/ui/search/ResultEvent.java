package com.pij.foursq.ui.search;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.pij.foursq.ui.model.Place;

import java.util.List;

import static java.util.Collections.emptyList;

/**
 * <p>Created on 13/05/2017.</p>
 * @author Pierrejean
 */

@AutoValue
abstract class ResultEvent {

    static ResultEvent started(String name) {
        return new AutoValue_ResultEvent(name, true, emptyList(), null);
    }

    static ResultEvent completed(String name, @NonNull List<Place> places) {
        return new AutoValue_ResultEvent(name, false, places, null);
    }

    static ResultEvent failed(String name, @NonNull String errorMessage) {
        return new AutoValue_ResultEvent(name, false, emptyList(), errorMessage);
    }

    static ResultEvent failed(String name, @NonNull Throwable error) {
        return failed(name, error.getMessage());
    }

    public abstract String name();

    public abstract boolean inProgress();

    @NonNull
    public abstract List<Place> places();

    @Nullable
    public abstract String errorMessage();

}
