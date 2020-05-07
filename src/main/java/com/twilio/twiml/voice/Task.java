/**
 * This code was generated by
 * \ / _    _  _|   _  _
 *  | (_)\/(_)(_|\/| |(/_  v1.0.0
 *       /       /
 */

package com.twilio.twiml.voice;

import com.twilio.twiml.TwiML;

import java.util.HashMap;
import java.util.Map;

/**
 * TwiML wrapper for {@code <Task>}
 */
public class Task extends TwiML {
    private final Integer priority;
    private final Integer timeout;
    private final String body;

    /**
     * For XML Serialization/Deserialization
     */
    private Task() {
        this(new Builder((String) null));
    }

    /**
     * Create a new {@code <Task>} element
     */
    private Task(Builder b) {
        super("Task", b);
        this.priority = b.priority;
        this.timeout = b.timeout;
        this.body = b.body;
    }

    /**
     * The body of the TwiML element
     *
     * @return Element body as a string if present else null
     */
    protected String getElementBody() {
        return this.getBody() == null ? null : this.getBody();
    }

    /**
     * Attributes to set on the generated XML element
     *
     * @return A Map of attribute keys to values
     */
    protected Map<String, String> getElementAttributes() {
        // Preserve order of attributes
        Map<String, String> attrs = new HashMap<>();

        if (this.getPriority() != null) {
            attrs.put("priority", this.getPriority().toString());
        }
        if (this.getTimeout() != null) {
            attrs.put("timeout", this.getTimeout().toString());
        }

        return attrs;
    }

    /**
     * Task priority
     *
     * @return Task priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * Timeout associated with task
     *
     * @return Timeout associated with task
     */
    public Integer getTimeout() {
        return timeout;
    }

    /**
     * TaskRouter task attributes
     *
     * @return TaskRouter task attributes
     */
    public String getBody() {
        return body;
    }

    /**
     * Create a new {@code <Task>} element
     */
    public static class Builder extends TwiML.Builder<Builder> {
        private Integer priority;
        private Integer timeout;
        private String body;

        /**
         * Create a {@code <Task>} with body
         */
        public Builder(String body) {
            this.body = body;
        }

        /**
         * Task priority
         */
        public Builder priority(Integer priority) {
            this.priority = priority;
            return this;
        }

        /**
         * Timeout associated with task
         */
        public Builder timeout(Integer timeout) {
            this.timeout = timeout;
            return this;
        }

        /**
         * Create and return resulting {@code <Task>} element
         */
        public Task build() {
            return new Task(this);
        }
    }
}