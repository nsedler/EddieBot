use crate::commands::command::Command;
use serenity::{
    model::{channel::Message, gateway::Ready},
    prelude::*,
};

mod commands;

struct Handler;

impl EventHandler for Handler {
    fn message(&self, ctx: Context, msg: Message) {
        let command_list: Vec<Box<dyn Command>> = vec![
            Box::new(commands::test_cmd::Ping {}),
            Box::new(commands::quit_cmd::Quit {}),
        ];

        let message: &String = &msg.content;

        for cmd in command_list.iter() {
            if message == &format!("!{}", cmd.cmd()) {
                cmd.execute(&ctx, &msg).expect("testing");
            }
        }
    }
    fn ready(&self, _: Context, ready: Ready) {
        println!(
            "{} is connected on version: {}",
            ready.user.name, ready.version
        );
    }
}

fn main() {
    // Configure the client with your Discord bot token in the environment.
    let token = String::from("NTAzMzU1ODIxMTY2NjkwMzA2.XwDHxQ.r8i9475BzMbrNxJUUxw5oz54Ihc");

    // Create a new instance of the Client, logging in as a bot. This will
    // automatically prepend your bot token with "Bot ", which is a requirement
    // by Discord for bot users.
    let mut client = Client::new(&token, Handler).expect("Err creating client");

    // Finally, start a single shard, and start listening to events.
    //
    // Shards will automatically attempt to reconnect, and will perform
    // exponential backoff until it reconnects.
    if let Err(why) = client.start() {
        println!("Client error: {:?}", why);
    }
}
