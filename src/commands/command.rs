use serenity::{
    model::{ channel::Message},
    prelude::*,
};

pub struct Command {
    cmd: String,
    aliases: Vec<String>,
    help: String
}

impl Command {
    pub fn new(cmd: String, aliases: Vec<String>, help: String) -> Command {
        return Command { cmd, aliases, help }
    }
}

pub trait Execute {
    fn execute(&self, ctx: Context, msg: Message);
}