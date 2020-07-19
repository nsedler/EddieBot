use crate::commands::command::Command;
use serenity::{model::channel::Message, prelude::*};
use std::io;
use std::process;

pub struct Quit {}

impl Command for Quit {
    fn cmd(&self) -> &str {
        "quit"
    }
    fn aliases(&self) -> Vec<&str> {
        Vec::new()
    }
    fn help(&self) -> &str {
        "shutsdown the bot"
    }
    fn owner_cmd(&self) -> bool {
        true
    }
    #[allow(unreachable_code)]
    fn execute(&self, ctx: &Context, msg: &Message) -> io::Result<()> {
        msg.channel_id
            .say(&ctx.http, "Bye bye...".to_string())
            .expect("Error executing command");
        process::exit(69);
        Ok(())
    }
}
