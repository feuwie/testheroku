/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.twiml.voice;

import com.twilio.twiml.TwiML;

/**
 * TwiML wrapper for {@code <Identity>}
 */
public class Identity extends TwiML {
    private final String clientIdentity;

    /**
     * For XML Serialization/Deserialization
     */
    private Identity() {
        this(new Builder((String) null));
    }

    /**
     * Create a new {@code <Identity>} element
     */
    private Identity(Builder b) {
        super("Identity", b);
        this.clientIdentity = b.clientIdentity;
    }

    /**
     * The body of the TwiML element
     *
     * @return Element body as a string if present else null
     */
    protected String getElementBody() {
        return this.getClientIdentity() == null ? null : this.getClientIdentity();
    }

    /**
     * Identity of the client to dial
     *
     * @return Identity of the client to dial
     */
    public String getClientIdentity() {
        return clientIdentity;
    }

    /**
     * Create a new {@code <Identity>} element
     */
    public static class Builder extends TwiML.Builder<Builder> {
        private String clientIdentity;

        /**
         * Create a {@code <Identity>} with clientIdentity
         */
        public Builder(String clientIdentity) {
            this.clientIdentity = clientIdentity;
        }

        /**
         * Create and return resulting {@code <Identity>} element
         */
        public Identity build() {
            return new Identity(this);
        }
    }
}