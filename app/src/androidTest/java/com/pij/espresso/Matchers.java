package com.pij.espresso;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import io.appflate.restmock.RESTMockServer;
import io.appflate.restmock.utils.QueryParam;
import io.appflate.restmock.utils.RequestMatcher;
import io.appflate.restmock.utils.RestMockUtils;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * <p>Created on 15/05/2017.</p>
 * @author Pierrejean
 */

public class Matchers {

    /**
     * Supplecments {@link io.appflate.restmock.utils.RequestMatchers}.
     */
    public static RequestMatcher hasQueryParameters(final QueryParam... expectedParams) {
        return new RequestMatcher("matched query parameters") {
            protected boolean matchesSafely(RecordedRequest item) {
                try {
                    URL e = new URL("http", "localhost", item.getPath());
                    List actualParams = RestMockUtils.splitQuery(e);
                    for (int i = 0; i < expectedParams.length; ++i) {
                        QueryParam param = expectedParams[i];
                        if (!actualParams.contains(param)) {
                            return false;
                        }
                    }

                    return true;
                } catch (MalformedURLException e) {
                    RESTMockServer.getLogger().error("URL appears to be malformed with path: " + item.getPath());
                    return false;
                } catch (UnsupportedEncodingException e) {
                    return false;
                }
            }
        };
    }
}
