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
 * TwiML wrapper for {@code <say-as>}
 */
public class SsmlSayAs extends TwiML {
    public enum InterpretAs {
        CHARACTER("character"),
        SPELL_OUT("spell-out"),
        CARDINAL("cardinal"),
        NUMBER("number"),
        ORDINAL("ordinal"),
        DIGITS("digits"),
        FRACTION("fraction"),
        UNIT("unit"),
        DATE("date"),
        TIME("time"),
        ADDRESS("address"),
        EXPLETIVE("expletive"),
        TELEPHONE("telephone");

        private final String value;

        private InterpretAs(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    public enum Role {
        MDY("mdy"),
        DMY("dmy"),
        YMD("ymd"),
        MD("md"),
        DM("dm"),
        YM("ym"),
        MY("my"),
        D("d"),
        M("m"),
        Y("y"),
        YYYYMMDD("yyyymmdd");

        private final String value;

        private Role(final String value) {
            this.value = value;
        }

        public String toString() {
            return value;
        }
    }

    private final SsmlSayAs.InterpretAs interpretAs;
    private final SsmlSayAs.Role role;
    private final String words;

    /**
     * For XML Serialization/Deserialization
     */
    private SsmlSayAs() {
        this(new Builder((String) null));
    }

    /**
     * Create a new {@code <say-as>} element
     */
    private SsmlSayAs(Builder b) {
        super("say-as", b);
        this.interpretAs = b.interpretAs;
        this.role = b.role;
        this.words = b.words;
    }

    /**
     * The body of the TwiML element
     *
     * @return Element body as a string if present else null
     */
    protected String getElementBody() {
        return this.getWords() == null ? null : this.getWords();
    }

    /**
     * Attributes to set on the generated XML element
     *
     * @return A Map of attribute keys to values
     */
    protected Map<String, String> getElementAttributes() {
        // Preserve order of attributes
        Map<String, String> attrs = new HashMap<>();

        if (this.getInterpretAs() != null) {
            attrs.put("interpret-as", this.getInterpretAs().toString());
        }
        if (this.getRole() != null) {
            attrs.put("role", this.getRole().toString());
        }

        return attrs;
    }

    /**
     * Specify the type of words are spoken
     *
     * @return Specify the type of words are spoken
     */
    public SsmlSayAs.InterpretAs getInterpretAs() {
        return interpretAs;
    }

    /**
     * Specify the format of the date when interpret-as is set to date
     *
     * @return Specify the format of the date when interpret-as is set to date
     */
    public SsmlSayAs.Role getRole() {
        return role;
    }

    /**
     * Words to be interpreted
     *
     * @return Words to be interpreted
     */
    public String getWords() {
        return words;
    }

    /**
     * Create a new {@code <say-as>} element
     */
    public static class Builder extends TwiML.Builder<Builder> {
        private SsmlSayAs.InterpretAs interpretAs;
        private SsmlSayAs.Role role;
        private String words;

        /**
         * Create a {@code <say-as>} with words
         */
        public Builder(String words) {
            this.words = words;
        }

        /**
         * Specify the type of words are spoken
         */
        public Builder interpretAs(SsmlSayAs.InterpretAs interpretAs) {
            this.interpretAs = interpretAs;
            return this;
        }

        /**
         * Specify the format of the date when interpret-as is set to date
         */
        public Builder role(SsmlSayAs.Role role) {
            this.role = role;
            return this;
        }

        /**
         * Create and return resulting {@code <say-as>} element
         */
        public SsmlSayAs build() {
            return new SsmlSayAs(this);
        }
    }
}