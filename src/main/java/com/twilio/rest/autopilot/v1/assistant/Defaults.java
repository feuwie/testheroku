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
public class Defaults extends Resource {
    private static final long serialVersionUID = 3179724176697L;

    /**
     * Create a DefaultsFetcher to execute fetch.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resource to fetch
     * @return DefaultsFetcher capable of executing the fetch
     */
    public static DefaultsFetcher fetcher(final String pathAssistantSid) {
        return new DefaultsFetcher(pathAssistantSid);
    }

    /**
     * Create a DefaultsUpdater to execute update.
     *
     * @param pathAssistantSid The SID of the Assistant that is the parent of the
     *                         resource to update
     * @return DefaultsUpdater capable of executing the update
     */
    public static DefaultsUpdater updater(final String pathAssistantSid) {
        return new DefaultsUpdater(pathAssistantSid);
    }

    /**
     * Converts a JSON String into a Defaults object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Defaults object represented by the provided JSON
     */
    public static Defaults fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Defaults.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Defaults object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Defaults object represented by the provided JSON
     */
    public static Defaults fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Defaults.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String assistantSid;
    private final URI url;
    private final Map<String, Object> data;

    @JsonCreator
    private Defaults(@JsonProperty("account_sid")
                     final String accountSid,
                     @JsonProperty("assistant_sid")
                     final String assistantSid,
                     @JsonProperty("url")
                     final URI url,
                     @JsonProperty("data")
                     final Map<String, Object> data) {
        this.accountSid = accountSid;
        this.assistantSid = assistantSid;
        this.url = url;
        this.data = data;
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
     * Returns The absolute URL of the Defaults resource.
     *
     * @return The absolute URL of the Defaults resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The JSON string that describes the default task links.
     *
     * @return The JSON string that describes the default task links
     */
    public final Map<String, Object> getData() {
        return this.data;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Defaults other = (Defaults) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(assistantSid, other.assistantSid) &&
               Objects.equals(url, other.url) &&
               Objects.equals(data, other.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            assistantSid,
                            url,
                            data);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("assistantSid", assistantSid)
                          .add("url", url)
                          .add("data", data)
                          .toString();
    }
}