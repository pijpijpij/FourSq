package com.pij.foursq.interactor;

import com.pij.foursq.net.model.Venue;
import com.pij.foursq.ui.model.Place;

import javax.inject.Inject;

/**
 * TODO Unit test worth it?
 * <p>Created on 14/05/2017.</p>
 * @author Pierrejean
 */

public class VenueConverter implements DtoConverter<Venue, Place> {

    @Inject
    VenueConverter() {
    }

    @Override
    public Place toFront(Venue venue) {
        return Place.create(venue.id(), venue.name());
    }
}
