/**
 *  Copyright 2018 Jonathan Augustine
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.nate.eddiebot.listener.events;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.Event;

/**
 * An wrapper class for {@code net.dv8tion.core.entities.Event}
 * that allows for easier
 * responding to messages in a number of ways; <br> namely,
 * methods for replying to messages regardless of the channel of origin or
 * responding to events directly to a {@code net.dv8tion.entites.PrivateChannel}
 *
 * @author Jonathan Augustine
 */

public abstract class BetterEvent {


    // get the original event
    protected abstract Event getEvent();

    // get the author
    protected abstract User getAuthor();

    // replys the the channel the original message was sent in
    protected abstract void reply(String message);

    // someone slid in your dms
    protected abstract void privateReply(String message);

    // is the message a dms
    protected abstract boolean isPrivate();

    BetterEvent(Event event){}

    public JDA getJDA(){
        return this.getEvent().getJDA();
    }
}
