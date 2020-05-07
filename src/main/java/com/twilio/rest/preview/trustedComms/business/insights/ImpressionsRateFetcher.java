/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.trustedComms.business.insights;

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

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
public class ImpressionsRateFetcher extends Fetcher<ImpressionsRate> {
    private final String pathBusinessSid;
    private String brandSid;
    private String brandedChannelSid;
    private String phoneNumberSid;
    private String country;
    private DateTime start;
    private DateTime end;
    private ImpressionsRate.Intervals interval;

    /**
     * Construct a new ImpressionsRateFetcher.
     *
     * @param pathBusinessSid Business Sid.
     */
    public ImpressionsRateFetcher(final String pathBusinessSid) {
        this.pathBusinessSid = pathBusinessSid;
    }

    /**
     * The unique SID identifier of the Brand to filter by..
     *
     * @param brandSid Brand Sid.
     * @return this
     */
    public ImpressionsRateFetcher setBrandSid(final String brandSid) {
        this.brandSid = brandSid;
        return this;
    }

    /**
     * The unique SID identifier of the Branded Channel to filter by..
     *
     * @param brandedChannelSid Branded Channel Sid.
     * @return this
     */
    public ImpressionsRateFetcher setBrandedChannelSid(final String brandedChannelSid) {
        this.brandedChannelSid = brandedChannelSid;
        return this;
    }

    /**
     * The unique SID identifier of the Phone Number to filter by..
     *
     * @param phoneNumberSid Phone Number Sid.
     * @return this
     */
    public ImpressionsRateFetcher setPhoneNumberSid(final String phoneNumberSid) {
        this.phoneNumberSid = phoneNumberSid;
        return this;
    }

    /**
     * The 2-letter ISO 3166 code of the Country to filter by..
     *
     * @param country Country 2-letter ISO 3166 code.
     * @return this
     */
    public ImpressionsRateFetcher setCountry(final String country) {
        this.country = country;
        return this;
    }

    /**
     * The start date that for this Impressions Rate, given in ISO 8601 format.
     * Default value is 30 days ago..
     *
     * @param start The start date that for this Impressions Rate.
     * @return this
     */
    public ImpressionsRateFetcher setStart(final DateTime start) {
        this.start = start;
        return this;
    }

    /**
     * The end date that for this Impressions Rate, given in ISO 8601 format.
     * Default value is current timestamp..
     *
     * @param end The end date that for this Impressions Rate.
     * @return this
     */
    public ImpressionsRateFetcher setEnd(final DateTime end) {
        this.end = end;
        return this;
    }

    /**
     * The Interval of this Impressions Rate. One of `minute`, `hour`, `day`, `week`
     * or `month`..
     *
     * @param interval The Interval of this Impressions Rate.
     * @return this
     */
    public ImpressionsRateFetcher setInterval(final ImpressionsRate.Intervals interval) {
        this.interval = interval;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the fetch.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Fetched ImpressionsRate
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public ImpressionsRate fetch(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.GET,
            Domains.PREVIEW.toString(),
            "/TrustedComms/Businesses/" + this.pathBusinessSid + "/Insights/ImpressionsRate",
            client.getRegion()
        );

        addQueryParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("ImpressionsRate fetch failed: Unable to connect to server");
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

        return ImpressionsRate.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested query string arguments to the Request.
     *
     * @param request Request to add query string arguments to
     */
    private void addQueryParams(final Request request) {
        if (brandSid != null) {
            request.addQueryParam("BrandSid", brandSid);
        }

        if (brandedChannelSid != null) {
            request.addQueryParam("BrandedChannelSid", brandedChannelSid);
        }

        if (phoneNumberSid != null) {
            request.addQueryParam("PhoneNumberSid", phoneNumberSid);
        }

        if (country != null) {
            request.addQueryParam("Country", country);
        }

        if (start != null) {
            request.addQueryParam("Start", start.toString());
        }

        if (end != null) {
            request.addQueryParam("End", end.toString());
        }

        if (interval != null) {
            request.addQueryParam("Interval", interval.toString());
        }
    }
}