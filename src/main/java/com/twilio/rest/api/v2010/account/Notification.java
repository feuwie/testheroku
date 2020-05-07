/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Notification extends Resource {
    private static final long serialVersionUID = 203437902819777L;

    /**
     * Create a NotificationFetcher to execute fetch.
     *
     * @param pathAccountSid The SID of the Account that created the resource to
     *                       fetch
     * @param pathSid The unique string that identifies the resource
     * @return NotificationFetcher capable of executing the fetch
     */
    public static NotificationFetcher fetcher(final String pathAccountSid,
                                              final String pathSid) {
        return new NotificationFetcher(pathAccountSid, pathSid);
    }

    /**
     * Create a NotificationFetcher to execute fetch.
     *
     * @param pathSid The unique string that identifies the resource
     * @return NotificationFetcher capable of executing the fetch
     */
    public static NotificationFetcher fetcher(final String pathSid) {
        return new NotificationFetcher(pathSid);
    }

    /**
     * Create a NotificationReader to execute read.
     *
     * @param pathAccountSid The SID of the Account that created the resources to
     *                       read
     * @return NotificationReader capable of executing the read
     */
    public static NotificationReader reader(final String pathAccountSid) {
        return new NotificationReader(pathAccountSid);
    }

    /**
     * Create a NotificationReader to execute read.
     *
     * @return NotificationReader capable of executing the read
     */
    public static NotificationReader reader() {
        return new NotificationReader();
    }

    /**
     * Converts a JSON String into a Notification object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Notification object represented by the provided JSON
     */
    public static Notification fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Notification.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Notification object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Notification object represented by the provided JSON
     */
    public static Notification fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Notification.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String apiVersion;
    private final String callSid;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final String errorCode;
    private final String log;
    private final DateTime messageDate;
    private final String messageText;
    private final URI moreInfo;
    private final HttpMethod requestMethod;
    private final URI requestUrl;
    private final String requestVariables;
    private final String responseBody;
    private final String responseHeaders;
    private final String sid;
    private final String uri;

    @JsonCreator
    private Notification(@JsonProperty("account_sid")
                         final String accountSid,
                         @JsonProperty("api_version")
                         final String apiVersion,
                         @JsonProperty("call_sid")
                         final String callSid,
                         @JsonProperty("date_created")
                         final String dateCreated,
                         @JsonProperty("date_updated")
                         final String dateUpdated,
                         @JsonProperty("error_code")
                         final String errorCode,
                         @JsonProperty("log")
                         final String log,
                         @JsonProperty("message_date")
                         final String messageDate,
                         @JsonProperty("message_text")
                         final String messageText,
                         @JsonProperty("more_info")
                         final URI moreInfo,
                         @JsonProperty("request_method")
                         final HttpMethod requestMethod,
                         @JsonProperty("request_url")
                         final URI requestUrl,
                         @JsonProperty("request_variables")
                         final String requestVariables,
                         @JsonProperty("response_body")
                         final String responseBody,
                         @JsonProperty("response_headers")
                         final String responseHeaders,
                         @JsonProperty("sid")
                         final String sid,
                         @JsonProperty("uri")
                         final String uri) {
        this.accountSid = accountSid;
        this.apiVersion = apiVersion;
        this.callSid = callSid;
        this.dateCreated = DateConverter.rfc2822DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.rfc2822DateTimeFromString(dateUpdated);
        this.errorCode = errorCode;
        this.log = log;
        this.messageDate = DateConverter.rfc2822DateTimeFromString(messageDate);
        this.messageText = messageText;
        this.moreInfo = moreInfo;
        this.requestMethod = requestMethod;
        this.requestUrl = requestUrl;
        this.requestVariables = requestVariables;
        this.responseBody = responseBody;
        this.responseHeaders = responseHeaders;
        this.sid = sid;
        this.uri = uri;
    }

    /**
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The API version used to generate the notification.
     *
     * @return The API version used to generate the notification
     */
    public final String getApiVersion() {
        return this.apiVersion;
    }

    /**
     * Returns The SID of the Call the resource is associated with.
     *
     * @return The SID of the Call the resource is associated with
     */
    public final String getCallSid() {
        return this.callSid;
    }

    /**
     * Returns The RFC 2822 date and time in GMT that the resource was created.
     *
     * @return The RFC 2822 date and time in GMT that the resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The RFC 2822 date and time in GMT that the resource was last updated.
     *
     * @return The RFC 2822 date and time in GMT that the resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns A unique error code corresponding to the notification.
     *
     * @return A unique error code corresponding to the notification
     */
    public final String getErrorCode() {
        return this.errorCode;
    }

    /**
     * Returns An integer log level.
     *
     * @return An integer log level
     */
    public final String getLog() {
        return this.log;
    }

    /**
     * Returns The date the notification was generated.
     *
     * @return The date the notification was generated
     */
    public final DateTime getMessageDate() {
        return this.messageDate;
    }

    /**
     * Returns The text of the notification.
     *
     * @return The text of the notification
     */
    public final String getMessageText() {
        return this.messageText;
    }

    /**
     * Returns A URL for more information about the error code.
     *
     * @return A URL for more information about the error code
     */
    public final URI getMoreInfo() {
        return this.moreInfo;
    }

    /**
     * Returns HTTP method used with the request url.
     *
     * @return HTTP method used with the request url
     */
    public final HttpMethod getRequestMethod() {
        return this.requestMethod;
    }

    /**
     * Returns URL of the resource that generated the notification.
     *
     * @return URL of the resource that generated the notification
     */
    public final URI getRequestUrl() {
        return this.requestUrl;
    }

    /**
     * Returns Twilio-generated HTTP variables sent to the server.
     *
     * @return Twilio-generated HTTP variables sent to the server
     */
    public final String getRequestVariables() {
        return this.requestVariables;
    }

    /**
     * Returns The HTTP body returned by your server.
     *
     * @return The HTTP body returned by your server
     */
    public final String getResponseBody() {
        return this.responseBody;
    }

    /**
     * Returns The HTTP headers returned by your server.
     *
     * @return The HTTP headers returned by your server
     */
    public final String getResponseHeaders() {
        return this.responseHeaders;
    }

    /**
     * Returns The unique string that identifies the resource.
     *
     * @return The unique string that identifies the resource
     */
    public final String getSid() {
        return this.sid;
    }

    /**
     * Returns The URI of the resource, relative to `https://api.twilio.com`.
     *
     * @return The URI of the resource, relative to `https://api.twilio.com`
     */
    public final String getUri() {
        return this.uri;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Notification other = (Notification) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(apiVersion, other.apiVersion) &&
               Objects.equals(callSid, other.callSid) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(errorCode, other.errorCode) &&
               Objects.equals(log, other.log) &&
               Objects.equals(messageDate, other.messageDate) &&
               Objects.equals(messageText, other.messageText) &&
               Objects.equals(moreInfo, other.moreInfo) &&
               Objects.equals(requestMethod, other.requestMethod) &&
               Objects.equals(requestUrl, other.requestUrl) &&
               Objects.equals(requestVariables, other.requestVariables) &&
               Objects.equals(responseBody, other.responseBody) &&
               Objects.equals(responseHeaders, other.responseHeaders) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(uri, other.uri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            apiVersion,
                            callSid,
                            dateCreated,
                            dateUpdated,
                            errorCode,
                            log,
                            messageDate,
                            messageText,
                            moreInfo,
                            requestMethod,
                            requestUrl,
                            requestVariables,
                            responseBody,
                            responseHeaders,
                            sid,
                            uri);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("apiVersion", apiVersion)
                          .add("callSid", callSid)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("errorCode", errorCode)
                          .add("log", log)
                          .add("messageDate", messageDate)
                          .add("messageText", messageText)
                          .add("moreInfo", moreInfo)
                          .add("requestMethod", requestMethod)
                          .add("requestUrl", requestUrl)
                          .add("requestVariables", requestVariables)
                          .add("responseBody", responseBody)
                          .add("responseHeaders", responseHeaders)
                          .add("sid", sid)
                          .add("uri", uri)
                          .toString();
    }
}