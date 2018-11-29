package com.nate.eddiebot.commands;

import net.dv8tion.jda.core.events.Event;

/**
 * An interface defining an entity which accepts
 * events without direct invocation. <br>
 * This is best used for any entity that needs to read lots of commands,
 * or pay attention to messages while the bot is not directly called.
 *
 *
 * @author Jonathan Augustine
 * @since 1.0
1 */
public interface IPassive {

    /**
     * Take in an {@link net.dv8tion.jda.core.events.Event} to interact with.
     *
     * @param event The event to receive.
     */
    void accept(PassiveEvent event);

    /** @return {@code false} if the passive is no longer active */
    boolean dead();
}
