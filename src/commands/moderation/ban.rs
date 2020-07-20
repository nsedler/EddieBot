use serenity::model::id::UserId;
use crate::commands::command::Command;
use serenity::{model::channel::Message, prelude::*};
use std::io;

pub struct Ban {}

impl Command for Ban {
    fn cmd(&self) -> &str {
        "ban"
    }
    fn aliases(&self) -> Vec<&str> {
        Vec::new()
    }
    fn help(&self) -> &str {
        "bans a user from a guild"
    }
    fn owner_cmd(&self) -> bool {
        false
    }
    fn execute(&self, ctx: &Context, msg: &Message) -> io::Result<()> {
        let banned = &msg.mentions;
        msg.channel_id
            .say(
                &ctx.http,
                format!(
                    "User `{}#{}` is now banned",
                    banned[0].name, banned[0].discriminator
                ),
            )
            .expect("Ban command failed");

        msg.guild_id
            .unwrap()
            .to_partial_guild(&ctx.http)
            .unwrap()
            .ban(&ctx.http, &banned[0], 7)
            .expect("Cannot ban user");
        Ok(())
    }
}

pub struct Unban {}

impl Command for Unban {
    fn cmd(&self) -> &str {
        "unban"
    }
    fn aliases(&self) -> Vec<&str> {
        Vec::new()
    }
    fn help(&self) -> &str {
        "unbans a user from a guild"
    }
    fn owner_cmd(&self) -> bool {
        false
    }
    fn execute(&self, ctx: &Context, msg: &Message) -> io::Result<()> {
        let user_id = msg.content.split(" ").collect::<Vec<&str>>()[1].parse::<u64>().unwrap();
        msg.guild_id.unwrap().to_partial_guild(&ctx.http).unwrap().unban(&ctx.http, user_id).expect("cannot find id");
        msg.channel_id.say(&ctx.http, format!("`{}` has been unbanned", &user_id)).expect("Error at sending unban message");
        Ok(())
    }
}

pub struct Bans {}

impl Command for Bans {
    fn cmd(&self) -> &str {
        "bans"
    }
    fn aliases(&self) -> Vec<&str> {
        Vec::new()
    }
    fn help(&self) -> &str {
        "Shows all banned users"
    }
    fn owner_cmd(&self) -> bool {
        false
    }
    fn execute(&self, ctx: &Context, msg: &Message) -> io::Result<()> {
        let all_bans = msg
            .guild_id
            .unwrap()
            .to_partial_guild(&ctx.http)
            .unwrap()
            .bans(&ctx.http)
            .unwrap();
        let mut banned_user: String = String::from("\n");
        for (i, user) in all_bans.iter().enumerate() {
            let temp_str = format!(
                "{}: Name:{}#{} ID: {} \n",
                i + 1,
                user.user.name,
                user.user.discriminator,
                user.user.id
            );
            banned_user.push_str(&temp_str);
        }

        println!("{}", banned_user);
        msg.channel_id
            .say(&ctx.http, banned_user)
            .expect("Bans command failed");
        Ok(())
    }
}
