use crate::commands::command::Command;
use serenity::Error;
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
        let guild = msg.guild(&ctx).unwrap().read().clone();

        if !banned.is_empty() {
            let ban_result = guild.ban(ctx, &banned[0], &String::from("Banned"));

            match ban_result {
                Err(Error::Model(ModelError::InvalidPermissions(permissions))) => {
                    msg.channel_id  
                        .say(
                            &ctx.http,
                            format!(
                                "I don't have the correct permissions, I am missing the following permissions `{:?}`",
                                permissions
                            ),
                        )
                        .expect("Incorrect Permssions message failed");
                }
                Err(err) => {
                    msg.channel_id
                        .say(
                            &ctx.http,
                            format!("An unknown error has caused this command to fail: {}", err),
                        )
                        .expect("Failed at unknown error");
                }
                Ok(_) => {}
            }
        } else {
            msg.channel_id
                .say(
                    &ctx.http,
                    "You must mention a user that you want to be banned",
                )
                .expect("No User Mentioned Failed");
        }

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
        let user_id = msg.content.split(" ").collect::<Vec<&str>>()[1]
            .parse::<u64>()
            .unwrap();
        let guild = msg.guild(&ctx).unwrap().read().clone();
        
        let unban_result = guild
            .unban(&ctx.http, user_id);

        match unban_result {
            Err(Error::Model(ModelError::InvalidPermissions(permissions))) => {
                msg.channel_id  
                    .say(
                        &ctx.http,
                        format!(
                            "I don't have the correct permissions, I am missing the following permissions `{:?}`",
                            permissions
                        ),
                    )
                    .expect("Incorrect Permssions message failed");
            }
            Err(err) => {
                msg.channel_id.say(&ctx.http, format!("An unknown error occured: `{}`", err)).expect("err expected?");
            }
            Ok(_) => {}
        }

        msg.channel_id
            .say(&ctx.http, format!("`{}` has been unbanned", &user_id))
            .expect("Error at sending unban message");
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
        let guild = msg.guild(&ctx).unwrap().read().clone();

        let all_bans = guild.bans(&ctx.http);

        let mut banned_user: String = String::from("\n");
        match all_bans {
            Err(Error::Model(ModelError::InvalidPermissions(permissions))) => {
                msg.channel_id  
                    .say(
                        &ctx.http,
                        format!(
                            "I don't have the correct permissions, I am missing the following permissions `{:?}`",
                            permissions
                        ),
                    )
                    .expect("Incorrect Permssions message failed");
            }
            Err(err) => {
                msg.channel_id  
                    .say(
                        &ctx.http,
                        format!(
                            "I don't have the correct permissions, I am missing the following permissions `{:?}`",
                            err
                        ),
                    )
                    .expect("Incorrect Permssions message failed");
            }
            Ok(_) => {
                for (i, user) in all_bans.unwrap().iter().enumerate() {
                    let temp_str = format!(
                        "{}: Name:{}#{} ID: {} \n",
                        i + 1,
                        user.user.name,
                        user.user.discriminator,
                        user.user.id
                    );
                    banned_user.push_str(&temp_str);
                }
                msg.channel_id
                    .say(&ctx.http, banned_user)
                    .expect("Bans command failed");
            }
        }
        
        Ok(())
    }
}
