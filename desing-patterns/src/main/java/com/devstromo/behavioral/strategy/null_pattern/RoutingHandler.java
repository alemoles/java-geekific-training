package com.devstromo.behavioral.strategy.null_pattern;

public class RoutingHandler {
    public void handle(Iterable<Message> messages) {
        for (Message msg : messages) {
            Router router = RouterFactory.getRouterForMessage(msg);
            router.route(msg);
        }
    }
}
