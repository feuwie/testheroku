/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.taskrouter.v1.workspace;

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

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event extends Resource {
    private static final long serialVersionUID = 88063843569402L;

    /**
     * Create a EventFetcher to execute fetch.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the Event to fetch
     * @param pathSid The SID of the resource to fetch
     * @return EventFetcher capable of executing the fetch
     */
    public static EventFetcher fetcher(final String pathWorkspaceSid,
                                       final String pathSid) {
        return new EventFetcher(pathWorkspaceSid, pathSid);
    }

    /**
     * Create a EventReader to execute read.
     *
     * @param pathWorkspaceSid The SID of the Workspace with the Events to read
     * @return EventReader capable of executing the read
     */
    public static EventReader reader(final String pathWorkspaceSid) {
        return new EventReader(pathWorkspaceSid);
    }

    /**
     * Converts a JSON String into a Event object using the provided ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return Event object represented by the provided JSON
     */
    public static Event fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a Event object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return Event object represented by the provided JSON
     */
    public static Event fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String actorSid;
    private final String actorType;
    private final URI actorUrl;
    private final String description;
    private final Map<String, Object> eventData;
    private final DateTime eventDate;
    private final Long eventDateMs;
    private final String eventType;
    private final String resourceSid;
    private final String resourceType;
    private final URI resourceUrl;
    private final String sid;
    private final String source;
    private final String sourceIpAddress;
    private final URI url;
    private final String workspaceSid;

    @JsonCreator
    private Event(@JsonProperty("account_sid")
                  final String accountSid,
                  @JsonProperty("actor_sid")
                  final String actorSid,
                  @JsonProperty("actor_type")
                  final String actorType,
                  @JsonProperty("actor_url")
                  final URI actorUrl,
                  @JsonProperty("description")
                  final String description,
                  @JsonProperty("event_data")
                  final Map<String, Object> eventData,
                  @JsonProperty("event_date")
                  final String eventDate,
                  @JsonProperty("event_date_ms")
                  final Long eventDateMs,
                  @JsonProperty("event_type")
                  final String eventType,
                  @JsonProperty("resource_sid")
                  final String resourceSid,
                  @JsonProperty("resource_type")
                  final String resourceType,
                  @JsonProperty("resource_url")
                  final URI resourceUrl,
                  @JsonProperty("sid")
                  final String sid,
                  @JsonProperty("source")
                  final String source,
                  @JsonProperty("source_ip_address")
                  final String sourceIpAddress,
                  @JsonProperty("url")
                  final URI url,
                  @JsonProperty("workspace_sid")
                  final String workspaceSid) {
        this.accountSid = accountSid;
        this.actorSid = actorSid;
        this.actorType = actorType;
        this.actorUrl = actorUrl;
        this.description = description;
        this.eventData = eventData;
        this.eventDate = DateConverter.iso8601DateTimeFromString(eventDate);
        this.eventDateMs = eventDateMs;
        this.eventType = eventType;
        this.resourceSid = resourceSid;
        this.resourceType = resourceType;
        this.resourceUrl = resourceUrl;
        this.sid = sid;
        this.source = source;
        this.sourceIpAddress = sourceIpAddress;
        this.url = url;
        this.workspaceSid = workspaceSid;
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
     * Returns The SID of the resource that triggered the event.
     *
     * @return The SID of the resource that triggered the event
     */
    public final String getActorSid() {
        return this.actorSid;
    }

    /**
     * Returns The type of resource that triggered the event.
     *
     * @return The type of resource that triggered the event
     */
    public final String getActorType() {
        return this.actorType;
    }

    /**
     * Returns The absolute URL of the resource that triggered the event.
     *
     * @return The absolute URL of the resource that triggered the event
     */
    public final URI getActorUrl() {
        return this.actorUrl;
    }

    /**
     * Returns A description of the event.
     *
     * @return A description of the event
     */
    public final String getDescription() {
        return this.description;
    }

    /**
     * Returns Data about the event.
     *
     * @return Data about the event
     */
    public final Map<String, Object> getEventData() {
        return this.eventData;
    }

    /**
     * Returns The time the event was sent.
     *
     * @return The time the event was sent
     */
    public final DateTime getEventDate() {
        return this.eventDate;
    }

    /**
     * Returns The time the event was sent in milliseconds.
     *
     * @return The time the event was sent in milliseconds
     */
    public final Long getEventDateMs() {
        return this.eventDateMs;
    }

    /**
     * Returns The identifier for the event.
     *
     * @return The identifier for the event
     */
    public final String getEventType() {
        return this.eventType;
    }

    /**
     * Returns The SID of the object the event is most relevant to.
     *
     * @return The SID of the object the event is most relevant to
     */
    public final String getResourceSid() {
        return this.resourceSid;
    }

    /**
     * Returns The type of object the event is most relevant to.
     *
     * @return The type of object the event is most relevant to
     */
    public final String getResourceType() {
        return this.resourceType;
    }

    /**
     * Returns The URL of the resource the event is most relevant to.
     *
     * @return The URL of the resource the event is most relevant to
     */
    public final URI getResourceUrl() {
        return this.resourceUrl;
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
     * Returns Where the Event originated.
     *
     * @return Where the Event originated
     */
    public final String getSource() {
        return this.source;
    }

    /**
     * Returns The IP from which the Event originated.
     *
     * @return The IP from which the Event originated
     */
    public final String getSourceIpAddress() {
        return this.sourceIpAddress;
    }

    /**
     * Returns The absolute URL of the Event resource.
     *
     * @return The absolute URL of the Event resource
     */
    public final URI getUrl() {
        return this.url;
    }

    /**
     * Returns The SID of the Workspace that contains the Event.
     *
     * @return The SID of the Workspace that contains the Event
     */
    public final String getWorkspaceSid() {
        return this.workspaceSid;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Event other = (Event) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(actorSid, other.actorSid) &&
               Objects.equals(actorType, other.actorType) &&
               Objects.equals(actorUrl, other.actorUrl) &&
               Objects.equals(description, other.description) &&
               Objects.equals(eventData, other.eventData) &&
               Objects.equals(eventDate, other.eventDate) &&
               Objects.equals(eventDateMs, other.eventDateMs) &&
               Objects.equals(eventType, other.eventType) &&
               Objects.equals(resourceSid, other.resourceSid) &&
               Objects.equals(resourceType, other.resourceType) &&
               Objects.equals(resourceUrl, other.resourceUrl) &&
               Objects.equals(sid, other.sid) &&
               Objects.equals(source, other.source) &&
               Objects.equals(sourceIpAddress, other.sourceIpAddress) &&
               Objects.equals(url, other.url) &&
               Objects.equals(workspaceSid, other.workspaceSid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            actorSid,
                            actorType,
                            actorUrl,
                            description,
                            eventData,
                            eventDate,
                            eventDateMs,
                            eventType,
                            resourceSid,
                            resourceType,
                            resourceUrl,
                            sid,
                            source,
                            sourceIpAddress,
                            url,
                            workspaceSid);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("actorSid", actorSid)
                          .add("actorType", actorType)
                          .add("actorUrl", actorUrl)
                          .add("description", description)
                          .add("eventData", eventData)
                          .add("eventDate", eventDate)
                          .add("eventDateMs", eventDateMs)
                          .add("eventType", eventType)
                          .add("resourceSid", resourceSid)
                          .add("resourceType", resourceType)
                          .add("resourceUrl", resourceUrl)
                          .add("sid", sid)
                          .add("source", source)
                          .add("sourceIpAddress", sourceIpAddress)
                          .add("url", url)
                          .add("workspaceSid", workspaceSid)
                          .toString();
    }
}