package com.itisdud;

public class Tweet {
    public Tweet() {

    }

    private String id;
    private String text;

    public void setId(String id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
