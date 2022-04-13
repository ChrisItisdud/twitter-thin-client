package com.itisdud;

public class TweetSet {
    private Tweet[] data;

    public TweetSet() {

    }

    public void setData(Tweet[] data) {
        this.data = data;
    }

    public Tweet[] getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (Tweet t : data) {
            sb.append(t.getText()).append("\n\n----------------------------\n");
        }
        return sb.toString();
    }
}
