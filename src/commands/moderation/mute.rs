extern crate regex;

use regex::Regex;
use crate::commands::command::Command;
use serenity::model::guild::Guild;
use serenity::{model::channel::Message, prelude::*};
use std::io;

pub struct Mute {}

impl Command for Mute {
    fn cmd(&self) -> &str {
        "mute"
    }
    fn aliases(&self) -> Vec<&str> {
        Vec::new()
    }
    fn help(&self) -> &str {
        "Stops someone from typing in your Guild"
    }
    fn owner_cmd(&self) -> bool {
        false
    }
    fn execute(&self, ctx: &Context, msg: &Message) -> io::Result<()> {
        let time_pattern = Regex::new(r"\s+[0-9]+(m|h|d)").unwrap();
        let guild: &Guild = &msg.guild(&ctx).unwrap().read().clone();
        let guild_members = &guild.members;
        let guild_roles = &guild.roles;
        let member = guild_members.get(&msg.mentions[0].id).unwrap();
        let guild_has_mute_role = guild_roles.values().any(|val| val.name == "mute");
        let mute_length = time_pattern.captures(msg.content.as_str()).unwrap()[0].to_string();
        println!("{:?}", mute_length);
        if guild_has_mute_role {
            let role = guild_roles
                .iter()
                .filter(|(_, v)| v.name.to_ascii_lowercase().as_str() == "mute")
                .map(|(k, _)| k)
                .collect::<Vec<_>>()[0];
            let has_role = &member
                .user
                .read()
                .clone()
                .has_role(&ctx.http, guild.id, role);
            match has_role {
                Err(err) => {
                    msg.channel_id
                        .say(&ctx.http, format!("An error has occured: `{}`", err))
                        .expect("Error occured at mute has_role match");
                }
                Ok(has) => {
                    if has == &true {
                        msg.channel_id.say(&ctx.http, format!("{}, is already muted...", &msg.mentions[0].name)).expect("User is muted already");
                    } else {
                        member.clone().add_role(&ctx.http, role).expect("test");
                    }
                }
            }
        } else {
            let create_mute_role = guild.create_role(&ctx.http, |r| r.hoist(true).name("mute").colour(999999));

            match create_mute_role {
                Err(err) => {
                    msg.channel_id.say(&ctx.http, format!("An error has occured: `{}`", err)).expect("Error creating new role");
                }
                Ok(role) => {
                    member.clone().add_role(&ctx.http, role.id).expect("Created and assigned mute role");
                }
            }
        }
        Ok(())
    }
}