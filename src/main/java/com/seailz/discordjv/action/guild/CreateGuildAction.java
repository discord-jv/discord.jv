package com.seailz.discordjv.action.guild;

import com.seailz.discordjv.DiscordJv;
import com.seailz.discordjv.model.guild.Guild;
import com.seailz.discordjv.model.guild.filter.ExplicitContentFilterLevel;
import com.seailz.discordjv.model.guild.notification.DefaultMessageNotificationLevel;
import com.seailz.discordjv.model.guild.verification.VerificationLevel;
import com.seailz.discordjv.utils.URLS;
import com.seailz.discordjv.utils.discordapi.DiscordRequest;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.concurrent.CompletableFuture;

public class CreateGuildAction {
    private String name;
    private VerificationLevel verificationLevel;
    private DefaultMessageNotificationLevel defaultMessageNotificationLevel;
    private ExplicitContentFilterLevel explicitContentFilterLevel;
    private final DiscordJv discordJv;

    public CreateGuildAction(String name, DefaultMessageNotificationLevel defaultMessageNotificationLevel, DiscordJv discordJv) {
        this.name = name;
        this.verificationLevel = VerificationLevel.NONE;
        this.defaultMessageNotificationLevel = defaultMessageNotificationLevel;
        this.explicitContentFilterLevel = ExplicitContentFilterLevel.DISABLED;
        this.discordJv = discordJv;
    }

    public void setName(String name) { this.name = name; }

    public void setVerificationLevel(VerificationLevel verificationLevel) { this.verificationLevel = verificationLevel; }

    public void setDefaultMessageNotificationLevel(DefaultMessageNotificationLevel defaultMessageNotificationLevel) { this.defaultMessageNotificationLevel = defaultMessageNotificationLevel; }

    public void setExplicitContentFilterLevel(ExplicitContentFilterLevel explicitContentFilterLevel) { this.explicitContentFilterLevel = explicitContentFilterLevel; }

    public String getName() { return name; }

    public VerificationLevel getVerificationLevel() { return verificationLevel; }

    public DefaultMessageNotificationLevel getDefaultMessageNotificationLevel() { return defaultMessageNotificationLevel; }

    public ExplicitContentFilterLevel getExplicitContentFilterLevel() { return explicitContentFilterLevel; }

    public CompletableFuture<Guild> run() {
        CompletableFuture<Guild> future = new CompletableFuture<>();
        future.completeAsync(() -> Guild.decompile(
                new DiscordRequest(
                        new JSONObject()
                                .put("name", name)
                                .put("verification_level", verificationLevel.getCode())
                                .put("default_message_notifications", defaultMessageNotificationLevel.getCode())
                                .put("explicit_content_filter", explicitContentFilterLevel.getCode()),
                        new HashMap<>(),
                        URLS.POST.GUILDS.BASE_URL,
                        discordJv,
                        URLS.BASE_URL,
                        RequestMethod.POST
                ).invoke().body(),
                discordJv
        ));
        return future;
    }
}
