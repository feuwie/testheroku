/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.wireless.v1.sim;

import com.twilio.base.Page;
import com.twilio.base.Reader;
import com.twilio.base.ResourceSet;
import com.twilio.converter.DateConverter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;
import org.joda.time.DateTime;

public class DataSessionReader extends Reader<DataSession> {
    private final String pathSimSid;
    private DateTime end;
    private DateTime start;

    /**
     * Construct a new DataSessionReader.
     *
     * @param pathSimSid The SID of the Sim resource with the Data Sessions to read
     */
    public DataSessionReader(final String pathSimSid) {
        this.pathSimSid = pathSimSid;
    }

    /**
     * The date that the record ended, given as GMT in [ISO
     * 8601](https://www.iso.org/iso-8601-date-and-time-format.html) format..
     *
     * @param end The date that the record ended, given as GMT in ISO 8601 format
     * @return this
     */
    public DataSessionReader setEnd(final DateTime end) {
        this.end = end;
        return this;
    }

    /**
     * The date that the Data Session started, given as GMT in [ISO
     * 8601](https://www.iso.org/iso-8601-date-and-time-format.html) format..
     *
     * @param start The date that the Data Session started, given as GMT in ISO
     *              8601 format
     * @return this
     */
    public DataSessionReader setStart(final DateTime start) {
        this.start = start;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return DataSession ResourceSet
     */
    @Override
    public ResourceSet<DataSession> read(final TwilioRestClient client) {
        return new ResourceSet<>(this, client, firstPage(client));
    }

    /**
     * Make the request to the Twilio API to perform the read.
     *
     * @param client TwilioRestClient with which to make the request
     * @return DataSession ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<DataSession> firstPage(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.WIRELESS.toString(),
            "/v1/Sims/" + this.pathSimSid + "/DataSessions",
            client.getRegion()
        );

        addQueryParams(request);
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the target page from the Twilio API.
     *
     * @param targetUrl API-generated URL for the requested results page
     * @param client TwilioRestClient with which to make the request
     * @return DataSession ResourceSet
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Page<DataSession> getPage(final String targetUrl, final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            targetUrl
        );

        return pageForRequest(client, request);
    }

    /**
     * Retrieve the next page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Next Page
     */
    @Override
    public Page<DataSession> nextPage(final Page<DataSession> page,
                                      final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getNextPageUrl(
                Domains.WIRELESS.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Retrieve the previous page from the Twilio API.
     *
     * @param page current page
     * @param client TwilioRestClient with which to make the request
     * @return Previous Page
     */
    @Override
    public Page<DataSession> previousPage(final Page<DataSession> page,
                                          final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            page.getPreviousPageUrl(
                Domains.WIRELESS.toString(),
                client.getRegion()
            )
        );
        return pageForRequest(client, request);
    }

    /**
     * Generate a Page of DataSession Resources for a given request.
     *
     * @param client TwilioRestClient with which to make the request
     * @param request Request to generate a page for
     * @return Page for the Request
     */
    private Page<DataSession> pageForRequest(final TwilioRestClient client, final Request request) {
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("DataSession read failed: Unable to connect to server");
        } else if (!TwilioRestClient.SUCCESS.apply(response.getStatusCode())) {
            RestException restException = RestException.fromJson(response.getStream(), client.getObjectMapper());
            if (restException == null) {
                throw new ApiException("Server Error, no content");
            }

            throw new ApiException(
                restException.getMessage(),
                restException.getCode(),
                restException.getMoreInfo(),
                restException.getStatus(),
                null
            );
        }

        return Page.fromJson(
            "data_sessions",
            response.getContent(),
            DataSession.class,
            client.getObjectMapper()
        );
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (end != null) {
            request.addQueryParam("End", end.toString());
        }

        if (start != null) {
            request.addQueryParam("Start", start.toString());
        }

        if (getPageSize() != null) {
            request.addQueryParam("PageSize", Integer.toString(getPageSize()));
        }
    }
}