package eddie.commands.owner;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import eddie.helpful.Categories;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Eval extends Command {


    public Eval() {
        this.name = "eval";
        this.help = "evaluates stuffs";
        this.category = Categories.Owner;
        this.ownerCommand = true;
        this.hidden = true;
    }

    @Override
    protected void execute(CommandEvent event) {
        ScriptEngine se = new ScriptEngineManager().getEngineByName("Nashorn");
        se.put("event", event);
        se.put("jda", event.getJDA());
        se.put("guild", event.getGuild());
        se.put("channel", event.getChannel());
        se.put("bot", event.getJDA().getSelfUser());

        try {
            event.reply(event.getClient().getSuccess() + " Evaluated Successfully:\n```\n" + se.eval(event.getArgs()) + " ```");
        } catch (Exception e) {
            event.reply(event.getClient().getError() + " An exception was thrown:\n```\n" + e + " ```");
        }
    }
}