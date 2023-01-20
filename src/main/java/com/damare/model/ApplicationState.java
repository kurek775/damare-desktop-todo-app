package com.damare.model;

public class ApplicationState {

    private static ApplicationState INSTANCE;

    private User currentlyLoggedUser = null;

    private Task currentlyEditedTask= null;

    private ApplicationState() {

    }

    public static ApplicationState getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApplicationState();
        }

        return INSTANCE;
    }

    public User getCurrentlyLoggedUser() {
        return this.currentlyLoggedUser;
    }

    public void setCurrentlyLoggedUser(User currentlyLoggedUser) {

        this.currentlyLoggedUser = currentlyLoggedUser;
    }

    public Task getCurrentlyEditedTask() {
        return this.currentlyEditedTask;
    }

    public void setCurrentlyEditedTask(Task currentlyEditedTask) {

        this.currentlyEditedTask = currentlyEditedTask;
    }
}