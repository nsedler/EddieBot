use crate::commands::command::Command;
use serenity::{model::channel::Message, prelude::*};
use std::{ io };

pub struct Ping {}

impl Command for Ping {
    fn cmd(&self) -> &str {
        "ping"
    }
    fn aliases(&self) -> Vec<&str> {
        Vec::new()
    }
    fn help(&self) -> &str {
        "Pong!"
    }
    fn owner_cmd(&self) -> bool {
        true
    }
    fn perms_cmd(
        &self,
    ) -> Option<std::vec::Vec<serenity::model::permissions::Permissions>> {
        None
    }
    fn execute(&self, ctx: &Context, msg: &Message) -> io::Result<()> {
        msg.channel_id
            .say(&ctx.http, "Pong!".to_string())
            .expect("test");
        Ok(())
    }
}
