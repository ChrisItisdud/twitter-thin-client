package com.itisdud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
        System.out.println("Reinitialise Database? y/n");
        boolean success = false;
        boolean decision = false;
        do {
            String userDec = scanner.nextLine();
            switch (userDec) {
                case "y":
                    success = true;
                    decision = true;
                    break;
                case "n":
                    success = true;
                    decision = false;
                    break;
                default:
                    System.out.println("Please enter y or n");
            }
        } while (!success);
        DatabaseManager dbm = new DatabaseManager();
        if (decision) {
            dbm.init();
        }
        //init api client
        TwitterApiClient tac = new TwitterApiClient(apiKey, secKey, bearerToken);
        //actual program
        boolean run = true;
        Account currentUser = null;
        while (run) {
            try {
                System.out.println("enter command - user: " + (currentUser == null ? "null" : (currentUser.getName() + "(" + currentUser.getUsername() + ")")));
                String command = scanner.nextLine();
                switch (command.split(" ")[0]) {
                    case "add":
                        Account addAcc = tac.getAccountFromUsername(command.split(" ")[1]);
                        dbm.addUser(addAcc);
                        currentUser = addAcc;
                        break;
                    case "get":
                        TweetSet response = tac.getTweetFromUser(currentUser.getId());
                        System.out.println(response.getData());
                        break;
                    case "exit":
                        run = false;
                        break;
                    default:
                        System.out.println("Invalid command");
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
