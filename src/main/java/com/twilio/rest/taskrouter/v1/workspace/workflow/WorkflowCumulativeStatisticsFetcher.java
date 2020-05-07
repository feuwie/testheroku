/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.taskrouter.v1.workspace.workflow;

import com.twilio.base.Fetcher;
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

public class WorkflowCumulativeStatisticsFetcher extends Fetcher<WorkflowCumulativeStatistics> {
    private final String pathWorkspaceSid;
    private final String pathWorkflowSid;
    private DateTime endDate;
    private Integer minutes;
    private DateTime startDate;
    private String taskChannel;
    private String splitByWaitTime;

    /**
     * Construct a new WorkflowCumulativeStatisticsFetcher.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the resource to fetch
     * @param pathWorkflowSid Returns the list of Tasks that are being controlled
     *                        by the Workflow with the specified Sid value
     */
    public WorkflowCumulativeStatisticsFetcher(final String pathWorkspaceSid,
                                               final String pathWorkflowSid) {
        this.pathWorkspaceSid = pathWorkspaceSid;
        this.pathWorkflowSid = pathWorkflowSid;
    }

    /**
     * Only include usage that occurred on or before this date, specified in GMT as
     * an [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) date-time..
     *
     * @param endDate Only include usage that occurred on or before this date
     * @return this
     */
    public WorkflowCumulativeStatisticsFetcher setEndDate(final DateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    /**
     * Only calculate statistics since this many minutes in the past. The default 15
     * minutes. This is helpful for displaying statistics for the last 15 minutes,
     * 240 minutes (4 hours), and 480 minutes (8 hours) to see trends..
     *
     * @param minutes Only calculate statistics since this many minutes in the past
     * @return this
     */
    public WorkflowCumulativeStatisticsFetcher setMinutes(final Integer minutes) {
        this.minutes = minutes;
        return this;
    }

    /**
     * Only calculate statistics from this date and time and later, specified in
     * [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) format..
     *
     * @param startDate Only calculate statistics from on or after this date
     * @return this
     */
    public WorkflowCumulativeStatisticsFetcher setStartDate(final DateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    /**
     * Only calculate cumulative statistics on this TaskChannel. Can be the
     * TaskChannel's SID or its `unique_name`, such as `voice`, `sms`, or
     * `default`..
     *
     * @param taskChannel Only calculate cumulative statistics on this TaskChannel
     * @return this
     */
    public WorkflowCumulativeStatisticsFetcher setTaskChannel(final String taskChannel) {
        this.taskChannel = taskChannel;
        return this;
    }

    /**
     * A comma separated list of values that describes the thresholds, in seconds,
     * to calculate statistics on. For each threshold specified, the number of Tasks
     * canceled and reservations accepted above and below the specified thresholds
     * in seconds are computed. For example, `5,30` would show splits of Tasks that
     * were canceled or accepted before and after 5 seconds and before and after 30
     * seconds. This can be used to show short abandoned Tasks or Tasks that failed
     * to meet an SLA..
     *
     * @param splitByWaitTime A comma separated list of values that describes the
     *                        thresholds to calculate statistics on
     * @return this
     */
    public WorkflowCumulativeStatisticsFetcher setSplitByWaitTime(final String splitByWaitTime) {
        this.splitByWaitTime = splitByWaitTime;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched WorkflowCumulativeStatistics
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public WorkflowCumulativeStatistics fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.TASKROUTER.toString(),
            "/v1/Workspaces/" + this.pathWorkspaceSid + "/Workflows/" + this.pathWorkflowSid + "/CumulativeStatistics",
            client.getRegion()
        );

        addQueryParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("WorkflowCumulativeStatistics fetch failed: Unable to connect to server");
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

        return WorkflowCumulativeStatistics.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (endDate != null) {
            request.addQueryParam("EndDate", endDate.toString());
        }

        if (minutes != null) {
            request.addQueryParam("Minutes", minutes.toString());
        }

        if (startDate != null) {
            request.addQueryParam("StartDate", startDate.toString());
        }

        if (taskChannel != null) {
            request.addQueryParam("TaskChannel", taskChannel);
        }

        if (splitByWaitTime != null) {
            request.addQueryParam("SplitByWaitTime", splitByWaitTime);
        }
    }
}