use crate::commands::command::Command;
use serenity::model::id::UserId;
use serenity::{model::channel::Message, prelude::*};
use std::io;

pub struct Kick {}

impl Command for Kick {
    fn cmd(&self) -> &str {
        "kick"
    }
    fn aliases(&self) -> Vec<&str> {
        Vec::new()
    }
    fn help(&self) -> &str {
        "kicks a user from a guild"
    }
    fn owner_cmd(&self) -> bool {
        false
    }
    fn execute(&self, ctx: &Context, msg: &Message) -> io::Result<()> {

        let kicked = &msg.mentions;
        let guild = msg.guild_id.unwrap().to_partial_guild(&ctx.http).unwrap();
        let mut kicked_users = String::new();

        for user in kicked {
            kicked_users.push_str(
                format!("{}#{} Id: {}\n", user.name, user.discriminator, user.id).as_str(),
            );
            guild
                .kick(&ctx.http, user)
                .expect(format!("Err at kicking player{}", user.name).as_str());
        }
        
        msg.channel_id
            .say(
                &ctx.http,
                format!("{} has been kicked from the guild.", kicked_users),
            )
            .expect("Ban command failed");
        Ok(())
    }
}
