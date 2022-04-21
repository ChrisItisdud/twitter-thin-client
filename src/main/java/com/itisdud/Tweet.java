package com.itisdud;

import java.util.Map;

public class Tweet {
    public Tweet() {

    }

    private String id;
    private String text;
    private Map<String, String> public_metrics;

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

    public Map<String, String> getPublic_metrics() {
        return public_metrics;
    }

    public void setPublic_metrics(Map<String, String> public_metrics) {
        this.public_metrics = public_metrics;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("");
        result.append(text).append("\n");
        if (public_metrics != null) {
            result.append("----------------------------\n");
            result.append("Likes: ").append(public_metrics.get("like_count")).append("\n");
            result.append("Replies: ").append(public_metrics.get("reply_count")).append("\n");
            result.append("Retweets: ").append(public_metrics.get("retweet_count")).append("\n");
        }
        return result.toString();
    }
}
