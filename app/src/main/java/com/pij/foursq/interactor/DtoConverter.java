package com.pij.foursq.interactor;

/**
 * <p>Created on 14/05/2017.</p>
 * @author Pierrejean
 */

public interface DtoConverter<Backend, Frontend> {

    Frontend toFront(Backend backend);
}
