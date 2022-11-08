package com.seailz.javadiscordwrapper.model.status;

/**
 * Represents the type of status
 *
 * @author Seailz
 * @since  1.0
 * @see    Status
 */
public enum StatusType {

    ONLINE("online"),
    IDLE("idle"),
    DO_NOT_DISTURB("dnd"),
    INVISIBLE("invisible"),
    OFFLINE("offline");

    private String code;

    StatusType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static StatusType fromCode(String code) {
        switch (code) {
            case "online" -> {
                return ONLINE;
            }
            case "idle" -> {
                return IDLE;
            }
            case "dnd" -> {
                return DO_NOT_DISTURB;
            }
            case "invisible" -> {
                return INVISIBLE;
            }
            case "offline" -> {
                return OFFLINE;
            }
            default -> {
                return null;
            }
        }
    }

}
