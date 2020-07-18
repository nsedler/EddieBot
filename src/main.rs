use crate::commands::command::Command;
use chrono::Local;
use chrono::Timelike;
use serenity::cache::CacheRwLock;
use serenity::model::guild::Member;
use serenity::{
    model::{
        channel::Message,
        gateway::{Activity, Ready},
        user::OnlineStatus,
        Permissions,
    },
    prelude::*,
};

mod commands;

struct Handler;

impl EventHandler for Handler {
    fn message(&self, ctx: Context, msg: Message) {
        if !msg.is_own(&ctx) {
            // println!("{:?}", msg.guild_id.unwrap().channels(&ctx.http));
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

            let y = user_perms(msg.member(&ctx.cache).unwrap(), &ctx.cache);
            println!("{:?}", y);
        }

        let command_list: Vec<Box<dyn Command>> = vec![
            Box::new(commands::test_cmd::Ping {}),
            Box::new(commands::quit_cmd::Quit {}),
        ];

        let message: &String = &msg.content;

        // TODO: Add permsion check.  Loop through users roles, and that roles perms and check -
        // > if it matches the perms_cmd then good
        // This should probably be done in a seperate function
        for cmd in command_list.iter() {
            if message == &format!("!{}", cmd.cmd()) {
                if *msg.author.id.as_u64() == 185063150557593600 as u64 && cmd.owner_cmd() {
                    cmd.execute(&ctx, &msg).expect("Owner check Passed | Error");
                } else {
                    msg.channel_id
                        .say(&ctx.http, "You must be an owner to use this command!")
                        .expect("Owner check failed | ERROR");
                }
            }
        }

        // println!("{}", msg.author);
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

fn user_perms(memb: Member, cache: impl AsRef<CacheRwLock>) -> Vec<Permissions> {
    let mut roles: Vec<Permissions> = vec![];
    for role in memb.roles {
        let r = role.to_role_cached(&cache).unwrap().permissions;
        if !roles.contains(&r) {
            roles.push(r);
        }
    }
    return roles;
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
