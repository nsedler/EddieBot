package com.nate.eddiebot.commands.misc;

import com.nate.eddiebot.commands.Command;
import com.nate.eddiebot.helpful.Categories;
import com.nate.eddiebot.listener.events.BetterMessageEvent;
import me.duncte123.botcommons.messaging.EmbedUtils;

public class testing extends Command {

    public testing(){
        this.name = "test";
        this.aliases = new String[]{"69", "420", "1337"};
        this.category = Categories.Testing;
    }

    @Override
    protected void execute(BetterMessageEvent event) {

        System.out.println("testing event");
        event.reply(EmbedUtils.embedMessage("test"));
    }


}
