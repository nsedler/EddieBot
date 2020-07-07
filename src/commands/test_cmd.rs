// use crate::commands_list;
use crate::commands::command::Command;
use serenity::{model::channel::Message, prelude::*};

pub fn ping() -> Command {
    Command {
        cmd: String::from("ping"),
        aliases: vec![String::from("")],
        help: String::from("Pong!"),
        execute: Box::new(| ctx: Context, msg: Message | 
            if let Err(err) = msg.channel_id.say(&ctx.http, String::from("Pong!")) {
                println!("{}", err)
            }
        )
    }
}
