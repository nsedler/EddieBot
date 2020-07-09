use crate::commands::command::Command;
use serenity::{model::channel::Message, prelude::*};
use std::io;

pub struct Ping {}

impl Command for Ping {
    fn cmd(&self) -> &str { "ping" }
    fn aliases(&self) -> Vec<&str> { Vec::new() }
    fn help(&self) -> &str { "Pong!" }
    fn execute(&self, ctx: &Context, msg: &Message) -> io::Result<()> {
        msg.channel_id.say(&ctx.http, "Pong!".to_string()).expect("test");
        Ok(())
    }
}

// pub fn ping() -> Command {
//     Command {
//         cmd: String::from("ping"),
//         aliases: vec![String::from("")],
//         help: String::from("Pong!"),
//         execute: Box::new(| ctx: Context, msg: Message | 
//             if let Err(err) = msg.channel_id.say(&ctx.http, String::from("Pong!")) {
//                 println!("{}", err)
//             }
//         )
//     }
// }
