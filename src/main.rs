extern crate dotenv;

use crate::commands::command::Command;
use chrono::Local;
use chrono::Timelike;
use serenity::{
    model::{
        channel::Message,
        gateway::{Activity, Ready},
        user::OnlineStatus,
    },
    prelude::*,
};

use dotenv::dotenv;

mod commands;

struct Handler;

impl EventHandler for Handler {
    fn message(&self, ctx: Context, msg: Message) {

        if !msg.is_own(&ctx) {
            println!(
                "[EddieBot] {} | {} | {} | {}",
                msg.guild_id
                    .unwrap()
                    .to_partial_guild(&ctx.http)
                    .unwrap()
                    .name,
                format!(
                    "{}:{}:{}",
                    Local::now().time().hour12().1,
                    msg.timestamp.time().minute(),
                    msg.timestamp.time().second()
                ),
                msg.author.name,
                msg.content
            );
        }

        let command_list: Vec<Box<dyn Command>> = vec![
            Box::new(commands::moderation::ban::Ban {}),
            Box::new(commands::moderation::ban::Bans {}),
            Box::new(commands::moderation::ban::Unban {}),
            Box::new(commands::moderation::kick::Kick {}),
            Box::new(commands::moderation::mute::Mute {}),
        ];

        let message: Vec<&str> = msg.content.split(" ").collect();

        // TODO: Add permsion check.  Loop through users roles, and that roles perms and check -
        // > if it matches the perms_cmd then good
        // This should probably be done in a seperate function
        for cmd in command_list.iter() {
            if message[0] == &format!("!{}", cmd.cmd()) {
                println!(
                    "[EddieBot Command] {}#{} ran {} command in guild: {} at: {}",
                    msg.author.name,
                    msg.author.discriminator,
                    cmd.cmd(),
                    msg.guild_id
                        .unwrap()
                        .to_partial_guild(&ctx.http)
                        .unwrap()
                        .name,
                    format!(
                        "{}:{}:{}",
                        Local::now().time().hour12().1,
                        msg.timestamp.time().minute(),
                        msg.timestamp.time().second()
                    ),
                );
                if cmd.owner_cmd() {
                    if *msg.author.id.as_u64() == 185063150557593600 as u64 {
                        cmd.execute(&ctx, &msg).expect("Owner check Passed | Error");
                    } else {
                        msg.channel_id
                            .say(&ctx.http, "You must be an owner to use this command!")
                            .expect("Owner check failed | ERROR");
                    }
                } else {
                    cmd.execute(&ctx, &msg).expect("Regular command | Error");
                }
            }
        }
    }
    fn ready(&self, ctx: Context, ready: Ready) {
        ctx.set_presence(
            Some(Activity::streaming(
                "purple 😲",
                "https://www.twitch.tv/nsedler",
            )),
            OnlineStatus::Idle,
        );
        println!(
            "{} is connected on version: {}",
            ready.user.name, ready.version
        );
    }
}

fn main() {
    dotenv().ok();
    // Configure the client with your Discord bot token in the environment.
    let token = String::from(dotenv::var("token").expect("Error at dotenv token"));

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
