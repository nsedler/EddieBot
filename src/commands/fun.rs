use serenity::{
    model::{ channel::Message},
    prelude::*,
};

pub fn ping(ctx: Context, msg: Message) {
    if let Err(error) = msg.channel_id.say(&ctx.http, "test") {
        println!("Got an error: {}", error);
    }
}

pub fn say(ctx: Context, msg: Message) {
    println!("{}", msg.content.to_string());
    if let Err(error) = msg.channel_id.say(&ctx.http, msg.content.to_string()) {
        println!("Got an error: {}", error);
    }
}