/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.conversations.v1.conversation;

import com.twilio.base.Updater;
import com.twilio.converter.Promoter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.util.List;

/**
 * PLEASE NOTE that this class contains beta products that are subject to
 * change. Use them with caution.
 */
public class WebhookUpdater extends Updater<Webhook> {
    private final String pathConversationSid;
    private final String pathSid;
    private String configurationUrl;
    private Webhook.Method configurationMethod;
    private List<String> configurationFilters;
    private List<String> configurationTriggers;
    private String configurationFlowSid;

    /**
     * Construct a new WebhookUpdater.
     *
     * @param pathConversationSid The unique id of the Conversation for this
     *                            webhook.
     * @param pathSid A 34 character string that uniquely identifies this resource.
     */
    public WebhookUpdater(final String pathConversationSid,
                          final String pathSid) {
        this.pathConversationSid = pathConversationSid;
        this.pathSid = pathSid;
    }

    /**
     * The absolute url the webhook request should be sent to..
     *
     * @param configurationUrl The absolute url the webhook request should be sent
     *                         to.
     * @return this
     */
    public WebhookUpdater setConfigurationUrl(final String configurationUrl) {
        this.configurationUrl = configurationUrl;
        return this;
    }

    /**
     * The HTTP method to be used when sending a webhook request..
     *
     * @param configurationMethod The HTTP method to be used when sending a webhook
     *                            request.
     * @return this
     */
    public WebhookUpdater setConfigurationMethod(final Webhook.Method configurationMethod) {
        this.configurationMethod = configurationMethod;
        return this;
    }

    /**
     * The list of events, firing webhook event for this Conversation..
     *
     * @param configurationFilters The list of events, firing webhook event for
     *                             this Conversation.
     * @return this
     */
    public WebhookUpdater setConfigurationFilters(final List<String> configurationFilters) {
        this.configurationFilters = configurationFilters;
        return this;
    }

    /**
     * The list of events, firing webhook event for this Conversation..
     *
     * @param configurationFilters The list of events, firing webhook event for
     *                             this Conversation.
     * @return this
     */
    public WebhookUpdater setConfigurationFilters(final String configurationFilters) {
        return setConfigurationFilters(Promoter.listOfOne(configurationFilters));
    }

    /**
     * The list of keywords, firing webhook event for this Conversation..
     *
     * @param configurationTriggers The list of keywords, firing webhook event for
     *                              this Conversation.
     * @return this
     */
    public WebhookUpdater setConfigurationTriggers(final List<String> configurationTriggers) {
        this.configurationTriggers = configurationTriggers;
        return this;
    }

    /**
     * The list of keywords, firing webhook event for this Conversation..
     *
     * @param configurationTriggers The list of keywords, firing webhook event for
     *                              this Conversation.
     * @return this
     */
    public WebhookUpdater setConfigurationTriggers(final String configurationTriggers) {
        return setConfigurationTriggers(Promoter.listOfOne(configurationTriggers));
    }

    /**
     * The studio flow sid, where the webhook should be sent to..
     *
     * @param configurationFlowSid The studio flow sid, where the webhook should be
     *                             sent to.
     * @return this
     */
    public WebhookUpdater setConfigurationFlowSid(final String configurationFlowSid) {
        this.configurationFlowSid = configurationFlowSid;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the update.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Updated Webhook
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Webhook update(final TwilioRestClient client) {
        Request request = new Request(
            HttpMethod.POST,
            Domains.CONVERSATIONS.toString(),
            "/v1/Conversations/" + this.pathConversationSid + "/Webhooks/" + this.pathSid + "",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Webhook update failed: Unable to connect to server");
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

        return Webhook.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (configurationUrl != null) {
            request.addPostParam("Configuration.Url", configurationUrl);
        }

        if (configurationMethod != null) {
            request.addPostParam("Configuration.Method", configurationMethod.toString());
        }

        if (configurationFilters != null) {
            for (String prop : configurationFilters) {
                request.addPostParam("Configuration.Filters", prop);
            }
        }

        if (configurationTriggers != null) {
            for (String prop : configurationTriggers) {
                request.addPostParam("Configuration.Triggers", prop);
            }
        }

        if (configurationFlowSid != null) {
            request.addPostParam("Configuration.FlowSid", configurationFlowSid);
        }
    }
}