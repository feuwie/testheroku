/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.autopilot.v1.assistant;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dialogue extends Resource {
    private static final long serialVersionUID = 58246367939830L;

    /**
     * Create a DialogueFetcher to execute fetch.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resource to fetch
     * @param pathSid The unique string that identifies the resource
     * @return DialogueFetcher capable of executing the fetch
     */
    public static DialogueFetcher fetcher(final String pathAssistantSid,
                                          final String pathSid) {
        return new DialogueFetcher(pathAssistantSid, pathSid);
    }

    /**
     * Converts a JSON String into a Dialogue object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Dialogue object represented by the provided JSON
     */
    public static Dialogue fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Dialogue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Dialogue object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Dialogue object represented by the provided JSON
     */
    public static Dialogue fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Dialogue.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String assistantSid;
    private final String sid;
    private final Map<String, Object> data;
    private final URI url;

    @JsonCreator
    private Dialogue(@JsonProperty("account_sid")
                     final String accountSid,
                     @JsonProperty("assistant_sid")
                     final String assistantSid,
                     @JsonProperty("sid")
                     final String sid,
                     @JsonProperty("data")
                     final Map<String, Object> data,
                     @JsonProperty("url")
                     final URI url) {
        this.accountSid = accountSid;
        this.assistantSid = assistantSid;
        this.sid = sid;
        this.data = data;
        this.url = url;
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
     * Returns The SID of the Assistant that is the parent of the resource.
     *
     * @return The SID of the Assistant that is the parent of the resource
     */
    public final String getAssistantSid() {
        return this.assistantSid;
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
     * Returns The JSON string that describes the dialogue session object.
     *
     * @return The JSON string that describes the dialogue session object
     */
    public final Map<String, Object> getData() {
        return this.data;
    }

    /**
     * Returns The absolute URL of the Dialogue resource.
     *
     * @return The absolute URL of the Dialogue resource
     */
    public final URI getUrl() {
        return this.url;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Dialogue other = (Dialogue) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(assistantSid, other.assistantSid) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(data, other.data) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            assistantSid,
                            sid,
                            data,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("assistantSid", assistantSid)
                          .add("sid", sid)
                          .add("data", data)
                          .add("url", url)
                          .toString();
    }
}