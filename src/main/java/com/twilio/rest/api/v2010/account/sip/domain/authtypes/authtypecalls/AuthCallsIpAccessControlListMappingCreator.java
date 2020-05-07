/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.rest.api.v2010.account.sip.domain.authtypes.authtypecalls;

import com.twilio.base.Creator;
import com.twilio.exception.ApiConnectionException;
import com.twilio.exception.ApiException;
import com.twilio.exception.RestException;
import com.twilio.http.HttpMethod;
import com.twilio.http.Request;
import com.twilio.http.Response;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.Domains;

public class AuthCallsIpAccessControlListMappingCreator extends Creator<AuthCallsIpAccessControlListMapping> {
    private String pathAccountSid;
    private final String pathDomainSid;
    private final String ipAccessControlListSid;

    /**
     * Construct a new AuthCallsIpAccessControlListMappingCreator.
     *
     * @param pathDomainSid The SID of the SIP domain that will contain the new
     *                      resource
     * @param ipAccessControlListSid The SID of the IpAccessControlList resource to
     *                               map to the SIP domain
     */
    public AuthCallsIpAccessControlListMappingCreator(final String pathDomainSid,
                                                      final String ipAccessControlListSid) {
        this.pathDomainSid = pathDomainSid;
        this.ipAccessControlListSid = ipAccessControlListSid;
    }

    /**
     * Construct a new AuthCallsIpAccessControlListMappingCreator.
     *
     * @param pathAccountSid The SID of the Account that will create the resource
     * @param pathDomainSid The SID of the SIP domain that will contain the new
     *                      resource
     * @param ipAccessControlListSid The SID of the IpAccessControlList resource to
     *                               map to the SIP domain
     */
    public AuthCallsIpAccessControlListMappingCreator(final String pathAccountSid,
                                                      final String pathDomainSid,
                                                      final String ipAccessControlListSid) {
        this.pathAccountSid = pathAccountSid;
        this.pathDomainSid = pathDomainSid;
        this.ipAccessControlListSid = ipAccessControlListSid;
    }

    /**
     * Make the request to the Twilio API to perform the create.
     *
     * @param client TwilioRestClient with which to make the request
     * @return Created AuthCallsIpAccessControlListMapping
     */
    @Override
    @SuppressWarnings("checkstyle:linelength")
    public AuthCallsIpAccessControlListMapping create(final TwilioRestClient client) {
        this.pathAccountSid = this.pathAccountSid == null ? client.getAccountSid() : this.pathAccountSid;
        Request request = new Request(
            HttpMethod.POST,
            Domains.API.toString(),
            "/2010-04-01/Accounts/" + this.pathAccountSid + "/SIP/Domains/" + this.pathDomainSid + "/Auth/Calls/IpAccessControlListMappings.json",
            client.getRegion()
        );

        addPostParams(request);
        Response response = client.request(request);

        if (response == null) {
            throw new ApiConnectionException("AuthCallsIpAccessControlListMapping creation failed: Unable to connect to server");
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

        return AuthCallsIpAccessControlListMapping.fromJson(response.getStream(), client.getObjectMapper());
    }

    /**
     * Add the requested post parameters to the Request.
     *
     * @param request Request to add post params to
     */
    private void addPostParams(final Request request) {
        if (ipAccessControlListSid != null) {
            request.addPostParam("IpAccessControlListSid", ipAccessControlListSid);
        }
    }
}