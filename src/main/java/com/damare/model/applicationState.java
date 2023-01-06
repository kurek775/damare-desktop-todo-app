package com.damare.model;

public class applicationState {

    private static applicationState INSTANCE;

    private User currentlyLoggedUser = null;

    private applicationState() {

    }

    public static applicationState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new applicationState();
        }

        return INSTANCE;
    }

    public User getCurrentlyLoggedUser() {
        return this.currentlyLoggedUser;
    }

    public void setCurrentlyLoggedUser(User currentlyLoggedUser) {

        this.currentlyLoggedUser = currentlyLoggedUser;
    }
}