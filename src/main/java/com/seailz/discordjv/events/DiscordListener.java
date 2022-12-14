package com.seailz.discordjv.events;

import com.seailz.discordjv.events.model.command.CommandPermissionUpdateEvent;
import com.seailz.discordjv.events.model.general.ReadyEvent;
import com.seailz.discordjv.events.model.guild.GuildCreateEvent;
import com.seailz.discordjv.events.model.interaction.button.ButtonInteractionEvent;
import com.seailz.discordjv.events.model.interaction.modal.ModalInteractionEvent;
import com.seailz.discordjv.events.model.interaction.select.StringSelectMenuInteractionEvent;
import com.seailz.discordjv.events.model.interaction.select.entity.ChannelSelectMenuInteractionEvent;
import com.seailz.discordjv.events.model.interaction.select.entity.RoleSelectMenuInteractionEvent;
import com.seailz.discordjv.events.model.interaction.select.entity.UserSelectMenuInteractionEvent;
import com.seailz.discordjv.events.model.message.MessageCreateEvent;
import org.jetbrains.annotations.NotNull;

/**
 * This class is used to listen for events that are fired by the Discord API.
 * This class can be extended to listen for events.
 * Once extended, you can override the methods you want to listen for.
 * Then, just register them in your main class.
 *
 * @author Seailz
 * @see com.seailz.discordjv.events.EventDispatcher
 * @see com.seailz.discordjv.events.annotation.EventMethod
 * @since 1.0
 */
public abstract class DiscordListener {
    // General Events
    public void onReady(@NotNull ReadyEvent event) {
    }

    // Message Events
    public void onMessageReceived(@NotNull MessageCreateEvent event) {
    }

    // Guild Events
    public void onGuildCreated(@NotNull GuildCreateEvent event) {
    }

    // Command Events
    public void onCommandPermissionUpdate(@NotNull CommandPermissionUpdateEvent event) {
    }

    // Message Component Events
    // Select Menu Events
    public void onStringSelectMenuInteraction(@NotNull StringSelectMenuInteractionEvent event) {
    }

    public void onRoleSelectMenuInteraction(@NotNull RoleSelectMenuInteractionEvent event) {
    }

    public void onUserSelectMenuInteraction(@NotNull UserSelectMenuInteractionEvent event) {
    }

    public void onChannelSelectMenuInteraction(@NotNull ChannelSelectMenuInteractionEvent event) {
    }

    // Button Events
    public void onButtonClickInteractionEvent(@NotNull ButtonInteractionEvent event) {
    }

    // Modal Events
    public void onModalInteractionEvent(@NotNull ModalInteractionEvent event) {
    }

}
