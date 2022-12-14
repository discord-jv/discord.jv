package com.seailz.discordjv.command.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Defines info about a specific context command.
 *
 * @author Seailz
 * @see    com.seailz.discordjv.command.listeners.MessageContextCommandListener
 * @see    com.seailz.discordjv.command.listeners.UserContextCommandListener
 * @since  1.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextCommandInfo {
    /**
     * The name of the context command
     */
    String value();
}
