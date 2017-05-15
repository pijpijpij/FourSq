package com.pij.foursq.interactor;

import com.pij.foursq.net.FourSquareApi;
import com.pij.foursq.net.model.ErrorResponse;
import com.pij.foursq.net.model.SearchResults;
import com.pij.foursq.net.model.Venue;
import com.pij.foursq.ui.model.Place;

import org.apache.commons.collections4.IterableUtils;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import rx.Observable;
import rx.Single;

import static org.apache.commons.collections4.IterableUtils.transformedIterable;

/**
 * TODO write Unit tests
 * <p>Created on 14/05/2017.</p>
 * @author Pierrejean
 */

public class NetSearcher implements Searcher {

    private final FourSquareApi api;
    private final DtoConverter<Venue, Place> dtoConverter;
    private final Converter<ResponseBody, ErrorResponse> errorConverter;

    @Inject
    NetSearcher(FourSquareApi api, DtoConverter<Venue, Place> dtoConverter,
                Converter<ResponseBody, ErrorResponse> errorConverter) {
        this.api = api;
        this.dtoConverter = dtoConverter;
        this.errorConverter = errorConverter;
    }

    @Override
    public Single<List<Place>> findByName(String name) {
        Observable<Response<SearchResults>> search = api.search(name).replay(1).autoConnect();
        Observable<List<Place>> success = search.filter(Response::isSuccessful)
                                                .map(Response::body)
                                                .map(SearchResults::response)
                                                .map(SearchResults.Response::venues)
                                                .map(IterableUtils::emptyIfNull)
                                                .map(list -> transformedIterable(list, dtoConverter::toFront))
                                                .map(IterableUtils::toList);
        Observable<List<Place>> failure = search.filter(response -> !response.isSuccessful())
                                                .map(Response::errorBody)
                                                .map(this::convertToError)
                                                .map(response -> response == null ? null : response.meta())
                                                .map(meta -> meta == null ? "Unknown reason" : meta.errorDetail())
                                                .map(RuntimeException::new)
                                                .flatMap(Observable::error);
        return Observable.merge(success, failure).toSingle();
    }

    private ErrorResponse convertToError(ResponseBody body) {
        try {
            return body == null ? null : errorConverter.convert(body);
        } catch (IOException e) {
            throw new RuntimeException("Could not find the reason for failure", e);
        }
    }

}
