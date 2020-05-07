/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.video.v1;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.MoreObjects;
import com.twilio.base.Resource;
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
public class CompositionSettings extends Resource {
    private static final long serialVersionUID = 41622584466212L;

    /**
     * Create a CompositionSettingsFetcher to execute fetch.
     *
     * @return CompositionSettingsFetcher capable of executing the fetch
     */
    public static CompositionSettingsFetcher fetcher() {
        return new CompositionSettingsFetcher();
    }

    /**
     * Create a CompositionSettingsCreator to execute create.
     *
     * @param friendlyName A descriptive string that you create to describe the
     *                     resource
     * @return CompositionSettingsCreator capable of executing the create
     */
    public static CompositionSettingsCreator creator(final String friendlyName) {
        return new CompositionSettingsCreator(friendlyName);
    }

    /**
     * Converts a JSON String into a CompositionSettings object using the provided
     * ObjectMapper.
     *
     * @param json Raw JSON String
     * @param objectMapper Jackson ObjectMapper
     * @return CompositionSettings object represented by the provided JSON
     */
    public static CompositionSettings fromJson(final String json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, CompositionSettings.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    /**
     * Converts a JSON InputStream into a CompositionSettings object using the
     * provided ObjectMapper.
     *
     * @param json Raw JSON InputStream
     * @param objectMapper Jackson ObjectMapper
     * @return CompositionSettings object represented by the provided JSON
     */
    public static CompositionSettings fromJson(final InputStream json, final ObjectMapper objectMapper) {
        // Convert all checked exceptions to Runtime
        try {
            return objectMapper.readValue(json, CompositionSettings.class);
        } catch (final JsonMappingException | JsonParseException e) {
            throw new ApiException(e.getMessage(), e);
        } catch (final IOException e) {
            throw new ApiConnectionException(e.getMessage(), e);
        }
    }

    private final String accountSid;
    private final String friendlyName;
    private final String awsCredentialsSid;
    private final URI awsS3Url;
    private final Boolean awsStorageEnabled;
    private final String encryptionKeySid;
    private final Boolean encryptionEnabled;
    private final URI url;

    @JsonCreator
    private CompositionSettings(@JsonProperty("account_sid")
                                final String accountSid,
                                @JsonProperty("friendly_name")
                                final String friendlyName,
                                @JsonProperty("aws_credentials_sid")
                                final String awsCredentialsSid,
                                @JsonProperty("aws_s3_url")
                                final URI awsS3Url,
                                @JsonProperty("aws_storage_enabled")
                                final Boolean awsStorageEnabled,
                                @JsonProperty("encryption_key_sid")
                                final String encryptionKeySid,
                                @JsonProperty("encryption_enabled")
                                final Boolean encryptionEnabled,
                                @JsonProperty("url")
                                final URI url) {
        this.accountSid = accountSid;
        this.friendlyName = friendlyName;
        this.awsCredentialsSid = awsCredentialsSid;
        this.awsS3Url = awsS3Url;
        this.awsStorageEnabled = awsStorageEnabled;
        this.encryptionKeySid = encryptionKeySid;
        this.encryptionEnabled = encryptionEnabled;
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
     * Returns The string that you assigned to describe the resource.
     *
     * @return The string that you assigned to describe the resource
     */
    public final String getFriendlyName() {
        return this.friendlyName;
    }

    /**
     * Returns The SID of the stored Credential resource.
     *
     * @return The SID of the stored Credential resource
     */
    public final String getAwsCredentialsSid() {
        return this.awsCredentialsSid;
    }

    /**
     * Returns The URL of the AWS S3 bucket where the compositions are stored.
     *
     * @return The URL of the AWS S3 bucket where the compositions are stored
     */
    public final URI getAwsS3Url() {
        return this.awsS3Url;
    }

    /**
     * Returns Whether all compositions are written to the aws_s3_url.
     *
     * @return Whether all compositions are written to the aws_s3_url
     */
    public final Boolean getAwsStorageEnabled() {
        return this.awsStorageEnabled;
    }

    /**
     * Returns The SID of the Public Key resource used for encryption.
     *
     * @return The SID of the Public Key resource used for encryption
     */
    public final String getEncryptionKeySid() {
        return this.encryptionKeySid;
    }

    /**
     * Returns Whether all compositions are stored in an encrypted form.
     *
     * @return Whether all compositions are stored in an encrypted form
     */
    public final Boolean getEncryptionEnabled() {
        return this.encryptionEnabled;
    }

    /**
     * Returns The absolute URL of the resource.
     *
     * @return The absolute URL of the resource
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

        CompositionSettings other = (CompositionSettings) o;

        return Objects.equals(accountSid, other.accountSid) &&
               Objects.equals(friendlyName, other.friendlyName) &&
               Objects.equals(awsCredentialsSid, other.awsCredentialsSid) &&
               Objects.equals(awsS3Url, other.awsS3Url) &&
               Objects.equals(awsStorageEnabled, other.awsStorageEnabled) &&
               Objects.equals(encryptionKeySid, other.encryptionKeySid) &&
               Objects.equals(encryptionEnabled, other.encryptionEnabled) &&
               Objects.equals(url, other.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountSid,
                            friendlyName,
                            awsCredentialsSid,
                            awsS3Url,
                            awsStorageEnabled,
                            encryptionKeySid,
                            encryptionEnabled,
                            url);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                          .add("accountSid", accountSid)
                          .add("friendlyName", friendlyName)
                          .add("awsCredentialsSid", awsCredentialsSid)
                          .add("awsS3Url", awsS3Url)
                          .add("awsStorageEnabled", awsStorageEnabled)
                          .add("encryptionKeySid", encryptionKeySid)
                          .add("encryptionEnabled", encryptionEnabled)
                          .add("url", url)
                          .toString();
    }
}