use crate::commands::command::Command;
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
        // let guild = msg.guild_id.unwrap().to_partial_guild(&ctx.http).unwrap();
        let guild = msg.guild(&ctx).unwrap().read().clone();
        let mut kicked_users = String::new();

        if !kicked.is_empty() {
            for user in kicked {
                let kick_result = guild.kick(&ctx, user);
                match kick_result {
                    Err(err) => {
                        msg.channel_id
                            .say(
                                &ctx.http,
                                format!("An unknown error has caused this command to fail: {}", err),
                            )
                            .expect("Failed at unknown error");
                            break;
                    }
                    Ok(_) => {
                        kicked_users.push_str(
                            format!("{}#{} Id: {}\n", user.name, user.discriminator, user.id).as_str(),
                        );
                        msg.channel_id
                        .say(
                            &ctx.http,
                            format!("{} has been kicked from the guild.", kicked_users),
                        )
                        .expect("Ban command failed");
                    }
                }
            }
            
        } else {
            msg.channel_id
                .say(&ctx.http, "You must mention the user you wish to ban.")
                .expect("No user to kick mentioned");
        }

        Ok(())
    }
}
