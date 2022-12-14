package com.seailz.discordjv.utils.version;

/**
 * Represents the state of an API version
 * Discord.jv will use the latest version of the API unless specified otherwise
 *
 * @author Seailz
 * @see com.seailz.discordjv.utils.version.APIVersion
 * @since 1.0
 */
public enum APIVersionState {

    AVAILABLE(true),
    DEPRECATED(true),
    DISCONTINUED(false),

    ;
    boolean canBeUsed;

    APIVersionState(boolean canBeUsed) {
        this.canBeUsed = canBeUsed;
    }


}
