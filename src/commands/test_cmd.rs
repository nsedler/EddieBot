use crate::commands::command::Execute;
use crate::commands::command::Command;
use serenity::{
    model::{ channel::Message},
    prelude::*,
};

pub fn test() -> Command {

    let y: Command = Command::new(
        String::from("test"),
        vec![String::from("")],
        String::from("just testing"),
    );
    
    impl Execute for Command {
        fn execute(&self, ctx: Context, msg: Message) {
            if let Err(why) = msg.channel_id.say(&ctx.http, "testing".to_string()) {
                println!("{}", why);
            }
        }
    }

    return y;
}
