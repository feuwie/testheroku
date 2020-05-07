/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.usage;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.net.URI;

public class TriggerCreator extends Creator<Trigger> {
    private String pathAccountSid;
    private final URI callbackUrl;
    private final String triggerValue;
    private final Trigger.UsageCategory usageCategory;
    private HttpMethod callbackMethod;
    private String friendlyName;
    private Trigger.Recurring recurring;
    private Trigger.TriggerField triggerBy;

    /**
     * Construct a new TriggerCreator.
     *
     * @param callbackUrl The URL we call when the trigger fires
     * @param triggerValue The usage value at which the trigger should fire
     * @param usageCategory The usage category the trigger watches
     */
    public TriggerCreator(final URI callbackUrl,
                          final String triggerValue,
                          final Trigger.UsageCategory usageCategory) {
        this.callbackUrl = callbackUrl;
        this.triggerValue = triggerValue;
        this.usageCategory = usageCategory;
    }

    /**
     * Construct a new TriggerCreator.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param callbackUrl The URL we call when the trigger fires
     * @param triggerValue The usage value at which the trigger should fire
     * @param usageCategory The usage category the trigger watches
     */
    public TriggerCreator(final String pathAccountSid,
                          final URI callbackUrl,
                          final String triggerValue,
                          final Trigger.UsageCategory usageCategory) {
        this.pathAccountSid = pathAccountSid;
        this.callbackUrl = callbackUrl;
        this.triggerValue = triggerValue;
        this.usageCategory = usageCategory;
    }

    /**
     * The HTTP method we should use to call `callback_url`. Can be: `GET` or `POST`
     * and the default is `POST`..
     *
     * @param callbackMethod The HTTP method to use to call callback_url
     * @return this
     */
    public TriggerCreator setCallbackMethod(final HttpMethod callbackMethod) {
        this.callbackMethod = callbackMethod;
        return this;
    }

    /**
     * A descriptive string that you create to describe the resource. It can be up
     * to 64 characters long..
     *
     * @param friendlyName A string to describe the resource
     * @return this
     */
    public TriggerCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * The frequency of a recurring UsageTrigger.  Can be: `daily`, `monthly`, or
     * `yearly` for recurring triggers or empty for non-recurring triggers. A
     * trigger will only fire once during each period. Recurring times are in GMT..
     *
     * @param recurring The frequency of a recurring UsageTrigger
     * @return this
     */
    public TriggerCreator setRecurring(final Trigger.Recurring recurring) {
        this.recurring = recurring;
        return this;
    }

    /**
     * The field in the
     * [UsageRecord](https://www.twilio.com/docs/usage/api/usage-record) resource
     * that should fire the trigger.  Can be: `count`, `usage`, or `price` as
     * described in the [UsageRecords
     * documentation](https://www.twilio.com/docs/usage/api/usage-record#usage-count-price).  The default is `usage`..
     *
     * @param triggerBy The field in the UsageRecord resource that fires the trigger
     * @return this
     */
    public TriggerCreator setTriggerBy(final Trigger.TriggerField triggerBy) {
        this.triggerBy = triggerBy;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Trigger
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Trigger create(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Usage/Triggers.json",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Trigger creation failed: Unable to connect to server");
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

        return Trigger.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (callbackUrl != null) {
            request.addPostParam("CallbackUrl", callbackUrl.toString());
        }

        if (triggerValue != null) {
            request.addPostParam("TriggerValue", triggerValue);
        }

        if (usageCategory != null) {
            request.addPostParam("UsageCategory", usageCategory.toString());
        }

        if (callbackMethod != null) {
            request.addPostParam("CallbackMethod", callbackMethod.toString());
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (recurring != null) {
            request.addPostParam("Recurring", recurring.toString());
        }

        if (triggerBy != null) {
            request.addPostParam("TriggerBy", triggerBy.toString());
        }
    }
}