/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.preview.marketplace;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
import com.twilio.converter.Converter;
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

/**
 * PLEASE NOTE that this class contains preview products that are subject to
 * change. Use them with caution. If you currently do not have developer preview
 * access, please contact help@twilio.com.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class InstalledAddOn extends Resource {
    private static final long serialVersionUID = 59619174394820L;

    /**
     * Create a InstalledAddOnCreator to execute create.
     *
     * @param availableAddOnSid The SID of the AvaliableAddOn to install
     * @param acceptTermsOfService Whether the Terms of Service were accepted
     * @return InstalledAddOnCreator capable of executing the create
     */
    public static InstalledAddOnCreator creator(final String availableAddOnSid,
                                                final Boolean acceptTermsOfService) {
        return new InstalledAddOnCreator(availableAddOnSid, acceptTermsOfService);
    }

    /**
     * Create a InstalledAddOnDeleter to execute delete.
     *
     * @param pathSid The SID of the InstalledAddOn resource to delete
     * @return InstalledAddOnDeleter capable of executing the delete
     */
    public static InstalledAddOnDeleter deleter(final String pathSid) {
        return new InstalledAddOnDeleter(pathSid);
    }

    /**
     * Create a InstalledAddOnFetcher to execute fetch.
     *
     * @param pathSid The SID of the InstalledAddOn resource to fetch
     * @return InstalledAddOnFetcher capable of executing the fetch
     */
    public static InstalledAddOnFetcher fetcher(final String pathSid) {
        return new InstalledAddOnFetcher(pathSid);
    }

    /**
     * Create a InstalledAddOnUpdater to execute update.
     *
     * @param pathSid The SID of the InstalledAddOn resource to update
     * @return InstalledAddOnUpdater capable of executing the update
     */
    public static InstalledAddOnUpdater updater(final String pathSid) {
        return new InstalledAddOnUpdater(pathSid);
    }

    /**
     * Create a InstalledAddOnReader to execute read.
     *
     * @return InstalledAddOnReader capable of executing the read
     */
    public static InstalledAddOnReader reader() {
        return new InstalledAddOnReader();
    }

    /**
     * Converts a JSON String into a InstalledAddOn object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return InstalledAddOn object represented by the provided JSON
     */
    public static InstalledAddOn fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, InstalledAddOn.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a InstalledAddOn object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return InstalledAddOn object represented by the provided JSON
     */
    public static InstalledAddOn fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, InstalledAddOn.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String sid;
    private final String accountSid;
    private final String friendlyName;
    private final String description;
    private final Map<String, Object> configuration;
    private final String uniqueName;
    private final DateTime dateCreated;
    private final DateTime dateUpdated;
    private final URI url;
    private final Map<String, String> links;

    @JsonCreator
    private InstalledAddOn(@JsonProperty("sid")
                           final String sid,
                           @JsonProperty("account_sid")
                           final String accountSid,
                           @JsonProperty("friendly_name")
                           final String friendlyName,
                           @JsonProperty("description")
                           final String description,
                           @JsonProperty("configuration")
                           final Map<String, Object> configuration,
                           @JsonProperty("unique_name")
                           final String uniqueName,
                           @JsonProperty("date_created")
                           final String dateCreated,
                           @JsonProperty("date_updated")
                           final String dateUpdated,
                           @JsonProperty("url")
                           final URI url,
                           @JsonProperty("links")
                           final Map<String, String> links) {
        this.sid = sid;
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.description = description;
        this.configuration = configuration;
        this.uniqueName = uniqueName;
        this.dateCreated = DateConverter.iso8601DateTimeFromString(dateCreated);
        this.dateUpdated = DateConverter.iso8601DateTimeFromString(dateUpdated);
        this.url = url;
        this.links = links;
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
     * Returns The SID of the Account that created the resource.
     *
     * @return The SID of the Account that created the resource
     */
    public final String getAccountSid() {
        return this.accountSid;
    }

    /**
     * Returns The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns A short description of the Add-on's functionality.
     *
     * @return A short description of the Add-on's functionality
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * Returns The JSON object that represents the current configuration of
     * installed Add-on.
     *
     * @return The JSON object that represents the current configuration of
     *         installed Add-on
     */
    public final Map<String, Object> getConfiguration() {
        return this.configuration;
    }

    /**
     * Returns An application-defined string that uniquely identifies the resource.
     *
     * @return An application-defined string that uniquely identifies the resource
     */
    public final String getUniqueName() {
        return this.uniqueName;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was created.
     *
     * @return The ISO 8601 date and time in GMT when the resource was created
     */
    public final DateTime getDateCreated() {
        return this.dateCreated;
    }

    /**
     * Returns The ISO 8601 date and time in GMT when the resource was last updated.
     *
     * @return The ISO 8601 date and time in GMT when the resource was last updated
     */
    public final DateTime getDateUpdated() {
        return this.dateUpdated;
    }

    /**
     * Returns The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The URLs of related resources.
     *
     * @return The URLs of related resources
     */
    public final Map<String, String> getLinks() {
        return this.links;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        InstalledAddOn other = (InstalledAddOn) o;

        return Objects.equals(sid, other.sid) &&
               Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(description, other.description) &&
               Objects.equals(configuration, other.configuration) &&
               Objects.equals(uniqueName, other.uniqueName) &&
               Objects.equals(dateCreated, other.dateCreated) &&
               Objects.equals(dateUpdated, other.dateUpdated) &&
               Objects.equals(url, other.url) &&
               Objects.equals(links, other.links);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid,
                            accountSid,
                            friendlyName,
                            description,
                            configuration,
                            uniqueName,
                            dateCreated,
                            dateUpdated,
                            url,
                            links);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("sid", sid)
                          .add("accountSid", accountSid)
                          .add("friendlyName", friendlyName)
                          .add("description", description)
                          .add("configuration", configuration)
                          .add("uniqueName", uniqueName)
                          .add("dateCreated", dateCreated)
                          .add("dateUpdated", dateUpdated)
                          .add("url", url)
                          .add("links", links)
                          .toString();
    }
}