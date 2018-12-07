 package com.nate.eddiebot.commands.misc;

 import com.nate.eddiebot.commands.Command;
 import com.nate.eddiebot.listener.events.BetterMessageEvent;
 import com.nate.eddiebot.util.bot.Categories;
 import com.nate.eddiebot.util.bot.DefaultEmbed;
 import net.dv8tion.jda.core.EmbedBuilder;
 import net.dv8tion.jda.core.entities.User;

 import java.util.Arrays;

 /**
  * Sends a pm to the owner
  *
  * @author Nate Sedler
  */
 public class Feedback extends Command {

     public Feedback() {

         this.name = "feedback";
         this.aliases = new String[]{"fb"};
         this.arguments = "<type of feedback> <feedback>";
         this.help = "Report a bug or give feedback on the bot";
         this.category = Categories.Misc;
     }

     @Override
     protected void execute(BetterMessageEvent event) {

         User owner = event.getJDA().retrieveUserById("185063150557593600").complete();
         EmbedBuilder x = DefaultEmbed.embedDefault();

         if(event.getArgs().length == 0 || event.getArgs() == null){
            x.appendDescription("You must include something in your feedback!");
         } else {
             owner.openPrivateChannel().queue((channel) -> channel.sendMessage(Arrays.toString(event.getArgs()) + " from " + event.getAuthor().getName()).queue());
             x.appendDescription("Message sent to " + owner.getName() + "!");
         }
         event.reply(x.build());
     }
 }
