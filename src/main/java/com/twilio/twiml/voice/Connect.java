/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.twiml.voice;

import com.twilio.converter.Promoter;
import com.twilio.http.HttpMethod;
import com.twilio.twiml.TwiML;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * TwiML wrapper for {@code <Connect>}
 */
public class Connect extends TwiML {
    private final URI action;
    private final HttpMethod method;

    /**
     * For XML Serialization/Deserialization
     */
    private Connect() {
        this(new Builder());
    }

    /**
     * Create a new {@code <Connect>} element
     */
    private Connect(Builder b) {
        super("Connect", b);
        this.action = b.action;
        this.method = b.method;
    }

    /**
     * Attributes to set on the generated XML element
     *
     * @return A Map of attribute keys to values
     */
    protected Map<String, String> getElementAttributes() {
        // Preserve order of attributes
        Map<String, String> attrs = new HashMap<>();

        if (this.getAction() != null) {
            attrs.put("action", this.getAction().toString());
        }
        if (this.getMethod() != null) {
            attrs.put("method", this.getMethod().toString());
        }

        return attrs;
    }

    /**
     * Action URL
     *
     * @return Action URL
     */
    public URI getAction() {
        return action;
    }

    /**
     * Action URL method
     *
     * @return Action URL method
     */
    public HttpMethod getMethod() {
        return method;
    }

    /**
     * Create a new {@code <Connect>} element
     */
    public static class Builder extends TwiML.Builder<Builder> {
        private URI action;
        private HttpMethod method;

        /**
         * Action URL
         */
        public Builder action(URI action) {
            this.action = action;
            return this;
        }

        /**
         * Action URL
         */
        public Builder action(String action) {
            this.action = Promoter.uriFromString(action);
            return this;
        }

        /**
         * Action URL method
         */
        public Builder method(HttpMethod method) {
            this.method = method;
            return this;
        }

        /**
         * Add a child {@code <Room>} element
         */
        public Builder room(Room room) {
            this.children.add(room);
            return this;
        }

        /**
         * Add a child {@code <Autopilot>} element
         */
        public Builder autopilot(Autopilot autopilot) {
            this.children.add(autopilot);
            return this;
        }

        /**
         * Add a child {@code <Stream>} element
         */
        public Builder stream(Stream stream) {
            this.children.add(stream);
            return this;
        }

        /**
         * Create and return resulting {@code <Connect>} element
         */
        public Connect build() {
            return new Connect(this);
        }
    }
}