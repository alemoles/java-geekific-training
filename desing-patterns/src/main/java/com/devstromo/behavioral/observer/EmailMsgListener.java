package com.devstromo.behavioral.observer;

public class EmailMsgListener implements EventListener {
    private final String email;

    public EmailMsgListener(String email) {
        this.email = email;
    }

    @Override
    public void update(Event eventType) {
        // Actually send the mail
        System.out.println("Emailed for: " + eventType.name() + " with email: " + email);
    }
}
