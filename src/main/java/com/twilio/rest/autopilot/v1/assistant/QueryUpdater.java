/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.autopilot.v1.assistant;

import com.twilio.base.Updater;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class QueryUpdater extends Updater<Query> {
    private final String pathAssistantSid;
    private final String pathSid;
    private String sampleSid;
    private String status;

    /**
     * Construct a new QueryUpdater.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resource to update
     * @param pathSid The unique string that identifies the resource to update
     */
    public QueryUpdater(final String pathAssistantSid,
                        final String pathSid) {
        this.pathAssistantSid = pathAssistantSid;
        this.pathSid = pathSid;
    }

    /**
     * The SID of an optional reference to the
     * [Sample](https://www.twilio.com/docs/autopilot/api/task-sample) created from
     * the query..
     *
     * @param sampleSid The SID of an optional reference to the Sample created from
     *                  the query
     * @return this
     */
    public QueryUpdater setSampleSid(final String sampleSid) {
        this.sampleSid = sampleSid;
        return this;
    }

    /**
     * The new status of the resource. Can be: `pending-review`, `reviewed`, or
     * `discarded`.
     *
     * @param status The new status of the resource
     * @return this
     */
    public QueryUpdater setStatus(final String status) {
        this.status = status;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Query
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Query update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.AUTOPILOT.toString(),
            "/v1/Assistants/" + this.pathAssistantSid + "/Queries/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Query update failed: Unable to connect to server");
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

        return Query.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (sampleSid != null) {
            request.addPostParam("SampleSid", sampleSid);
        }

        if (status != null) {
            request.addPostParam("Status", status);
        }
    }
}