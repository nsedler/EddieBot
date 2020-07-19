use serenity::{
    model::{channel::Message},
    prelude::*,
};
use std::io;

pub trait Command {
    fn cmd(&self) -> &str;
    fn aliases(&self) -> Vec<&str>;
    fn help(&self) -> &str;
    fn owner_cmd(&self) -> bool;
    fn execute(&self, ctx: &Context, msg: &Message) -> io::Result<()>;
}
