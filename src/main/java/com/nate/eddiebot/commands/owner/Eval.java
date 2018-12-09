package com.nate.eddiebot.commands.owner;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import com.nate.eddiebot.util.bot.Categories;
import me.duncte123.botcommons.messaging.EmbedUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Arrays;

/**
 * Evaluates the code you send it
 * // TODO switch to groovy thingy
 *
 * @author Nate Sedler
 */
public class Eval extends Command {

    public Eval() {
        this.name = "eval";
        this.help = "evaluates stuffs";
        this.category = Categories.Owner;
        this.ownerOnly = true;
        this.hidden = true;
    }

    @Override
    protected void execute(BetterMessageEvent event) {
        ScriptEngine se = new ScriptEngineManager().getEngineByName("Nashorn");
        se.put("event", event);
        se.put("jda", event.getJDA());
        se.put("guild", event.getGuild());
        se.put("channel", event.getTextChannel());
        se.put("bot", event.getJDA().getSelfUser());

        String args = String.join(",", Arrays.copyOfRange(event.getArgs(), 1, event.getArgs().length));
        System.out.println(args);
        try {
            event.reply(EmbedUtils.embedMessage(" Evaluated Successfully:\n```\n" + se.eval(args) + " ```"));
        } catch (Exception e) {
            event.reply(EmbedUtils.embedMessage(" An exception was thrown:\n```\n" + e + " ```"));
        }
    }
}
