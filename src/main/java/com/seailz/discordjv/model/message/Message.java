package com.seailz.discordjv.model.message;

import com.seailz.discordjv.DiscordJv;
import com.seailz.discordjv.core.Compilerable;
import com.seailz.discordjv.model.application.Application;
import com.seailz.discordjv.model.channel.thread.Thread;
import com.seailz.discordjv.model.channel.utils.ChannelMention;
import com.seailz.discordjv.model.component.Component;
import com.seailz.discordjv.model.component.DisplayComponent;
import com.seailz.discordjv.model.embed.Embed;
import com.seailz.discordjv.model.emoji.Reaction;
import com.seailz.discordjv.model.interaction.Interaction;
import com.seailz.discordjv.model.message.activity.MessageActivity;
import com.seailz.discordjv.model.resolve.Resolvable;
import com.seailz.discordjv.model.role.Role;
import com.seailz.discordjv.model.user.User;
import com.seailz.discordjv.utils.Snowflake;
import com.seailz.discordjv.utils.URLS;
import com.seailz.discordjv.utils.discordapi.DiscordRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public record Message(
        // The snowflake ID of the message
        String id,
        // The snowflake ID of the channel the message was sent in
        String channelId,
        // The user who sent the message
        User author,
        // The contents of the message
        String content,
        // The timestamp the message was sent at
        String timestamp,
        // The timestamp the message was last edited at (or null if never)
        String editedTimestamp,
        // Whether this was a TTS message
        boolean tts,
        // Whether this message mentions everyone
        boolean mentionEveryone,
        // Users mentioned in the message
        User[] mentions,
        // Roles mentioned in this message
        Role[] mentionRoles,
        // Channels mentioned in this message
        ChannelMention[] mentionChannels,
        // Any attached files
        Attachment[] attachments,
        // Any embedded content
        Embed[] embeds,
        // Reactions to the message
        Reaction[] reactions,
        // Used for validating a message was sent
        String nonce,
        // Whether this message is pinned
        boolean pinned,
        // If this message is generated by a webhook, this is the webhook's ID
        String webhookId,
        // Type of message
        MessageType type,
        // Sent with Rich Presence-related chat embeds
        MessageActivity activity,
        // if the message is an interaction or application-owned webhook, then this is the application object
        Application application,
        // if the message is an interaction or application-owned webhook, then this is the application id
        String applicationId,
        // data showing the source of a crosspost, channel follow add, pin, or reply message
        MessageReference messageReference,
        // the flags applied to the message
        MessageFlag[] flags,
        // the message associated with the message_reference
        Message referencedMessage,
        // sent if the message is a response to an Interaction
        Interaction interaction,
        // the thread that was started from this message, includes thread member object
        Thread thread,
        // sent if the message contains components like buttons, action rows, or other interactive components
        List<DisplayComponent> components,
        DiscordJv discordJv
) implements Compilerable, Resolvable, Snowflake {

    @NonNull
    public static Message decompile(JSONObject obj, DiscordJv discordJv) {
        String id;
        String channelId;
        User author;
        String content;
        String timestamp;
        String editedTimestamp;
        boolean tts;
        boolean mentionEveryone;
        User[] mentions;
        Role[] mentionRoles;
        ChannelMention[] mentionChannels;
        Attachment[] attachments;
        Embed[] embeds;
        Reaction[] reactions;
        String nonce;
        boolean pinned;
        String webhookId;
        MessageType type;
        MessageActivity activity;
        Application application;
        String applicationId;
        MessageReference messageReference;
        MessageFlag[] flags;
        Message referencedMessage;
        Interaction interaction;
        Thread thread;
        List<DisplayComponent> components = new ArrayList<>();

        try {
            id = obj.getString("id");
        } catch (JSONException e) {
            id = null;
        }
        try {
            channelId = obj.getString("channel_id");
        } catch (JSONException e) {
            channelId = null;
        }
        try {
            author = User.decompile(obj.getJSONObject("author"), discordJv);
        } catch (JSONException e) {
            author = null;
        }
        try {
            content = obj.getString("content");
        } catch (JSONException e) {
            content = null;
        }
        try {
            timestamp = obj.getString("timestamp");
        } catch (JSONException e) {
            timestamp = null;
        }
        try {
            editedTimestamp = obj.getString("edited_timestamp");
        } catch (JSONException e) {
            editedTimestamp = null;
        }
        try {
            tts = obj.getBoolean("tts");
        } catch (JSONException e) {
            tts = false;
        }
        try {
            mentionEveryone = obj.getBoolean("mention_everyone");
        } catch (JSONException e) {
            mentionEveryone = false;
        }

        try {
            JSONArray componentsJson = obj.getJSONArray("components");
            List<Component> componentsDecompiled = Component.decompileList(componentsJson, discordJv);
            List<DisplayComponent> displayComponents = new ArrayList<>();
            for (Component component : componentsDecompiled) {
                if (component instanceof DisplayComponent) {
                    displayComponents.add((DisplayComponent) component);
                }
            }
        } catch (JSONException e) {
            components = null;
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            components = null;
            e.printStackTrace();
        }

        try {
            JSONArray mentionsArray = obj.getJSONArray("mentions");
            mentions = new User[mentionsArray.length()];
            for (int i = 0; i < mentionsArray.length(); i++) {
                mentions[i] = User.decompile(mentionsArray.getJSONObject(i), discordJv);
            }
        } catch (JSONException e) {
            mentions = null;
        }
        try {
            JSONArray mentionRolesArray = obj.getJSONArray("mention_roles");
            mentionRoles = new Role[mentionRolesArray.length()];
            for (int i = 0; i < mentionRolesArray.length(); i++) {
                mentionRoles[i] = Role.decompile(mentionRolesArray.getJSONObject(i));
            }
        } catch (JSONException e) {
            mentionRoles = null;
        }
        try {
            JSONArray mentionChannelsArray = obj.getJSONArray("mention_channels");
            ArrayList<ChannelMention> mentionChannelsDecompiled = new ArrayList<>();

            for (int i = 0; i < mentionChannelsArray.length(); i++) {
                mentionChannelsDecompiled.add(ChannelMention.decompile(mentionChannelsArray.getJSONObject(i)));
            }
            mentionChannels = mentionChannelsDecompiled.toArray(new ChannelMention[0]);
        } catch (JSONException e) {
            mentionChannels = null;
        }

        try {
            JSONArray attachmentsArray = obj.getJSONArray("attachments");
            attachments = new Attachment[attachmentsArray.length()];
            for (int i = 0; i < attachmentsArray.length(); i++) {
                attachments[i] = Attachment.decompile(attachmentsArray.getJSONObject(i));
            }
        } catch (JSONException e) {
            attachments = null;
        }

        try {
            JSONArray embedsArray = obj.getJSONArray("embeds");
            embeds = new Embed[embedsArray.length()];
            for (int i = 0; i < embedsArray.length(); i++) {
                embeds[i] = Embed.decompile(embedsArray.getJSONObject(i));
            }
        } catch (JSONException e) {
            embeds = null;
        }

        try {
            JSONArray reactionsArray = obj.getJSONArray("reactions");
            reactions = new Reaction[reactionsArray.length()];
            for (int i = 0; i < reactionsArray.length(); i++) {
                reactions[i] = Reaction.decompile(reactionsArray.getJSONObject(i), discordJv);
            }
        } catch (JSONException e) {
            reactions = null;
        }

        try {
            nonce = obj.getString("nonce");
        } catch (JSONException e) {
            nonce = null;
        }

        try {
            pinned = obj.getBoolean("pinned");
        } catch (JSONException e) {
            pinned = false;
        }

        try {
            webhookId = obj.getString("webhook_id");
        } catch (JSONException e) {
            webhookId = null;
        }

        try {
            type = MessageType.fromCode(obj.getInt("type"));
        } catch (JSONException e) {
            type = null;
        }

        try {
            activity = MessageActivity.decompile(obj.getJSONObject("activity"));
        } catch (JSONException e) {
            activity = null;
        }

        try {
            application = Application.decompile(obj.getJSONObject("application"), discordJv);
        } catch (JSONException e) {
            application = null;
        }

        try {
            applicationId = obj.getString("application_id");
        } catch (JSONException e) {
            applicationId = null;
        }

        try {
            messageReference = MessageReference.decompile(obj.getJSONObject("message_reference"));
        } catch (JSONException e) {
            messageReference = null;
        }

        try {
            ArrayList<MessageFlag> flagsDecompiled = new ArrayList<>(MessageFlag.getFlagsByInt(obj.getInt("flags")));
            flags = flagsDecompiled.toArray(new MessageFlag[0]);
        } catch (JSONException e) {
            flags = null;
        }

        try {
            referencedMessage = Message.decompile(obj.getJSONObject("referenced_message"), discordJv);
        } catch (JSONException e) {
            referencedMessage = null;
        }

        try {
            interaction = Interaction.decompile(obj.getJSONObject("interaction"), discordJv);
        } catch (JSONException e) {
            interaction = null;
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        try {
            thread = Thread.decompile(obj.getJSONObject("thread"), discordJv);
        } catch (JSONException e) {
            thread = null;
        }

        return new Message(id, channelId, author, content, timestamp, editedTimestamp, tts, mentionEveryone, mentions, mentionRoles, mentionChannels, attachments, embeds, reactions, nonce, pinned, webhookId, type, activity, application, applicationId, messageReference, flags, referencedMessage, interaction, thread, components, discordJv);
    }

    @Override
    public JSONObject compile() {
        int flags = 0;
        for (MessageFlag flag : this.flags) {
            flags += flag.getLeftShiftId();
        }

        JSONArray mentionsArray = new JSONArray();
        if (mentions != null) {
            for (User mention : mentions) {
                mentionsArray.put(mention.compile());
            }
        }

        JSONArray mentionRolesArray = new JSONArray();
        if (mentionRoles != null) {
            for (Role mentionRole : mentionRoles) {
                mentionRolesArray.put(mentionRole.compile());
            }
        }

        JSONArray mentionChannelsArray = new JSONArray();
        if (mentionChannels != null) {
            for (ChannelMention mentionChannel : mentionChannels) {
                mentionChannelsArray.put(mentionChannel.compile());
            }
        }

        JSONArray attachmentsArray = new JSONArray();
        if (attachments != null) {
            for (Attachment attachment : attachments) {
                attachmentsArray.put(attachment.compile());
            }
        }

        JSONArray embedsArray = new JSONArray();
        if (embeds != null) {
            for (Embed embed : embeds) {
                embedsArray.put(embed.compile());
            }
        }

        JSONArray reactionsArray = new JSONArray();
        if (reactions != null) {
            for (Reaction reaction : reactions) {
                reactionsArray.put(reaction.compile());
            }
        }

        JSONArray componentsArray = new JSONArray();
        if (components != null) {
            for (Component component : components) {
                componentsArray.put(component.compile());
            }
        }

        return new JSONObject()
                .put("id", id)
                .put("channel_id", channelId)
                .put("author", author.compile())
                .put("content", content)
                .put("timestamp", timestamp)
                .put("edited_timestamp", editedTimestamp)
                .put("tts", tts)
                .put("mention_everyone", mentionEveryone)
                .put("mentions", mentionsArray)
                .put("mention_roles", mentionRolesArray)
                .put("mention_channels", mentionChannelsArray)
                .put("attachments", attachmentsArray)
                .put("embeds", embedsArray)
                .put("reactions", reactionsArray)
                .put("nonce", nonce)
                .put("pinned", pinned)
                .put("webhook_id", webhookId)
                .put("type", type.getCode())
                .put("activity", activity == null ? JSONObject.NULL : activity.compile())
                .put("application", application == null ? JSONObject.NULL : application.compile())
                .put("application_id", applicationId)
                .put("message_reference", messageReference == null ? JSONObject.NULL : messageReference.compile())
                .put("flags", flags)
                .put("referenced_message", referencedMessage == null ? JSONObject.NULL : referencedMessage.compile())
                .put("interaction", interaction == null ? JSONObject.NULL : interaction.compile())
                .put("thread", thread == null ? JSONObject.NULL : thread.compile())
                .put("components", componentsArray);
    }

    public void delete() {
        new DiscordRequest(new JSONObject(), new HashMap<>(), URLS.DELETE.CHANNEL.MESSAGE.DELETE_MESSAGE
                .replace("{channel.id}", channelId).replace("{message.id}", id),
                discordJv, URLS.DELETE.CHANNEL.MESSAGE.DELETE_MESSAGE, RequestMethod.DELETE).invoke();
    }

    /**
     * Returns the text of the message as how you would see it in the client.
     */
    public String getFormattedText() {
        String formatted = content;

        for (User user : mentions)
            formatted = formatted.replaceAll("<@" + user.id() + ">", "@" + user.username());

        for (Role role : mentionRoles)
            formatted = formatted.replaceAll("<@&" + role.id() + ">", "@" + role.name());
        return formatted;
    }
}

