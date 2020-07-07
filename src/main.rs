use serenity::{
    model::{ channel::Message, gateway::Ready },
    prelude::*,
};

mod commands;

struct Handler;

impl EventHandler for Handler {
    // Set a handler for the `message` event - so that whenever a new message
    // is received - the closure (or function) passed will be called.
    //
    // Event handlers are dispatched through a threadpool, and so multiple
    // events can be dispatched simultaneously.
    fn message(&self, ctx: Context, msg: Message) {
        let x = commands::test_cmd::test();
        println!("{:#?}", x);
        match msg.content.as_ref()  {
            "!ping" => commands::fun::ping(ctx, msg),
            "!say" => {commands::fun::say(ctx, msg); println!("test");},
            _ => ()
        };
    }

    // Set a handler to be called on the `ready` event. This is called when a
    // shard is booted, and a READY payload is sent by Discord. This payload
    // contains data like the current user's guild Ids, current user data,
    // private channels, and more.
    //
    // In this case, just print what the current user's username is.
    fn ready(&self, _: Context, ready: Ready) {
        println!("{} is connected on session id: {}", ready.user.name, ready.session_id);
    }
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