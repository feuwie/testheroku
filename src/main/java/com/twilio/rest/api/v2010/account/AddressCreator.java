/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class AddressCreator extends Creator<Address> {
    private String pathAccountSid;
    private final String customerName;
    private final String street;
    private final String city;
    private final String region;
    private final String postalCode;
    private final String isoCountry;
    private String friendlyName;
    private Boolean emergencyEnabled;
    private Boolean autoCorrectAddress;

    /**
     * Construct a new AddressCreator.
     *
     * @param customerName The name to associate with the new address
     * @param street The number and street address of the new address
     * @param city The city of the new address
     * @param region The state or region of the new address
     * @param postalCode The postal code of the new address
     * @param isoCountry The ISO country code of the new address
     */
    public AddressCreator(final String customerName,
                          final String street,
                          final String city,
                          final String region,
                          final String postalCode,
                          final String isoCountry) {
        this.customerName = customerName;
        this.street = street;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.isoCountry = isoCountry;
    }

    /**
     * Construct a new AddressCreator.
     *
     * @param pathAccountSid The SID of the Account that will be responsible for
     *                       the new Address resource
     * @param customerName The name to associate with the new address
     * @param street The number and street address of the new address
     * @param city The city of the new address
     * @param region The state or region of the new address
     * @param postalCode The postal code of the new address
     * @param isoCountry The ISO country code of the new address
     */
    public AddressCreator(final String pathAccountSid,
                          final String customerName,
                          final String street,
                          final String city,
                          final String region,
                          final String postalCode,
                          final String isoCountry) {
        this.pathAccountSid = pathAccountSid;
        this.customerName = customerName;
        this.street = street;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.isoCountry = isoCountry;
    }

    /**
     * A descriptive string that you create to describe the new address. It can be
     * up to 64 characters long..
     *
     * @param friendlyName A string to describe the new resource
     * @return this
     */
    public AddressCreator setFriendlyName(final String friendlyName) {
        this.friendlyName = friendlyName;
        return this;
    }

    /**
     * Whether to enable emergency calling on the new address. Can be: `true` or
     * `false`..
     *
     * @param emergencyEnabled Whether to enable emergency calling on the new
     *                         address
     * @return this
     */
    public AddressCreator setEmergencyEnabled(final Boolean emergencyEnabled) {
        this.emergencyEnabled = emergencyEnabled;
        return this;
    }

    /**
     * Whether we should automatically correct the address. Can be: `true` or
     * `false` and the default is `true`. If empty or `true`, we will correct the
     * address you provide if necessary. If `false`, we won't alter the address you
     * provide..
     *
     * @param autoCorrectAddress Whether we should automatically correct the address
     * @return this
     */
    public AddressCreator setAutoCorrectAddress(final Boolean autoCorrectAddress) {
        this.autoCorrectAddress = autoCorrectAddress;
        return this;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created Address
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public Address create(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/Addresses.json",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("Address creation failed: Unable to connect to server");
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

        return Address.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (customerName != null) {
            request.addPostParam("CustomerName", customerName);
        }

        if (street != null) {
            request.addPostParam("Street", street);
        }

        if (city != null) {
            request.addPostParam("City", city);
        }

        if (region != null) {
            request.addPostParam("Region", region);
        }

        if (postalCode != null) {
            request.addPostParam("PostalCode", postalCode);
        }

        if (isoCountry != null) {
            request.addPostParam("IsoCountry", isoCountry);
        }

        if (friendlyName != null) {
            request.addPostParam("FriendlyName", friendlyName);
        }

        if (emergencyEnabled != null) {
            request.addPostParam("EmergencyEnabled", emergencyEnabled.toString());
        }

        if (autoCorrectAddress != null) {
            request.addPostParam("AutoCorrectAddress", autoCorrectAddress.toString());
        }
    }
}