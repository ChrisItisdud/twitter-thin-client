package com.itisdud;

import com.owlike.genson.Genson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class TwitterApiClient {
    private final OkHttpClient client;
    private final Genson genson;
    private final String twittApi = "https://api.twitter.com/2/";
    private final String apiKey;
    private final String secretKey;
    private final String bearerToken;

    public TwitterApiClient(String apiKey, String secretKey, String bearerToken) {
        this.client = new OkHttpClient();
        this.genson = new Genson();
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        this.bearerToken = bearerToken;
    }

    private Response sendRequest(final String url) throws IOException {
        final Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + bearerToken)
                .get()
                .build();

        return client.newCall(request).execute();
    }

    public AccountData getDataFromUsername(String username) throws IOException {
        final String url = twittApi + "users/by/username/" + username;
        final Response response = sendRequest(url);
        if (response.body() == null) return null;
        final String resString = response.body().string();
        return genson.deserialize(resString, AccountData.class);
    }

    public Account getAccountFromUsername(String username) throws IOException {
        AccountData data = getDataFromUsername(username);
        return new Account(data.data.get("name"), data.data.get("id"), data.data.get("username"));
    }

    public TweetSet getTweetFromUser(String userId) throws IOException {
        final String url = twittApi + "users/" + userId + "/tweets";
        final Response response = sendRequest(url);
        if (response.body() == null) return null;
        final String resString = response.body().string();
        System.out.println(resString);
        return genson.deserialize(resString, TweetSet.class);
    }
}
