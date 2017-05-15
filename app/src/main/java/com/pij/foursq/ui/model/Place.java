package com.pij.foursq.ui.model;

import com.google.auto.value.AutoValue;

/**
 * <p>Created on 13/05/2017.</p>
 * @author Pierrejean
 */
@AutoValue
public abstract class Place {

    public static Place create(String id, String name) {
        return new AutoValue_Place(id, name);
    }

    public abstract String id();

    public abstract String name();

}
