package com.muravyovdmitr.notter.models;

/**
 * Created by Dima Muravyov on 31.07.2016.
 */

public class Note {
    private String title;
    private String message;

    public Note(String title, String message) {
        this.title = title;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
