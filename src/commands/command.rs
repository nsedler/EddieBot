use serenity::{model::channel::Message, prelude::*};

pub struct Command {
    pub cmd: String,
    pub aliases: Vec<String>,
    pub help: String,
    pub execute: Box<dyn Fn(Context, Message)>
}

pub trait Execute {
    fn execute(&self, ctx: Context, msg: Message);
}
