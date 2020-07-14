use serenity::{model::{channel::Message, Permissions}, prelude::*};
use std::io;

pub trait Command {
    fn cmd(&self) -> &str;
    fn aliases(&self) -> Vec<&str>;
    fn help(&self) -> &str;
    fn owner_cmd(&self) -> bool;
    fn perms_cmd(&self) -> Option<Vec<Permissions>>;
    fn execute(&self, ctx: &Context, msg: &Message) -> io::Result<()>;
}