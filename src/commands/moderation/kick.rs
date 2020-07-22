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
        msg.channel_id
            .say(
                &ctx.http,
                format!(
                    "User `{}#{}` has been cicked from {}",
                    kicked[0].name, kicked[0].discriminator, guild.name
                ),
            )
            .expect("Ban command failed");
        for user in kicked {
            guild
                .kick(&ctx.http, user)
                .expect(format!("Err at kicking player{}", user.name));
        }
        Ok(())
    }
}
