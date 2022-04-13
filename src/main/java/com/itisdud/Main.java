package com.itisdud;

import java.io.BufferedReader;
import java.io.FileReader;

public class Main {
    public static void main(String[] args) {
        //init
        final String dir = "D:/programme/apikeys/twitter.txt";
        String apiKey = "";
        String secKey = "";
        String bearerToken = "";
        try (BufferedReader br = new BufferedReader(new FileReader(dir))) {
            br.readLine();
            apiKey = br.readLine();
            br.readLine();
            secKey = br.readLine();
            br.readLine();
            bearerToken = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //init database
        DatabaseManager dbm = new DatabaseManager();
        dbm.init();
        //init api client
        TwitterApiClient tac = new TwitterApiClient(apiKey, secKey, bearerToken);
        //actual program
        try {
            System.out.println(tac.getAccountFromUsername("Itisdud"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
