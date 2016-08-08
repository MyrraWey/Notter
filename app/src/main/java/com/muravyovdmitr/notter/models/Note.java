package com.muravyovdmitr.notter.models;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Dima Muravyov on 31.07.2016.
 */

public class Note implements Serializable {
    private UUID id;
    private String title;
    private String message;

    public Note(String title, String message) {
        this(UUID.randomUUID(), title, message);
    }

    public Note(UUID id, String title, String message) {
        this.id = id;
        this.title = title;
        this.message = message;
    }

    public UUID getId() {
        return id;
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
