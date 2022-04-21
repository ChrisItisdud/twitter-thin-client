package com.itisdud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
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
                    case "choose":
                        Account toChoose = dbm.getUser(command.split(" ")[1]);
                        if (toChoose == null) System.out.println("Database does not know this user!");
                        else currentUser = toChoose;
                        break;
                    case "get":
                        if (currentUser == null) {
                            System.out.println("Select a user first");
                        } else {
                            TweetSet response = tac.getTweetFromUser(currentUser.getId());
                            for (Tweet t : response.getData()) {
                                System.out.println("view next tweet? y/n");
                                String cmd = scanner.nextLine();
                                if (cmd.equals("y")) {
                                    System.out.println(t);
                                } else break;
                            }
                            System.out.println("End of tweet set!");
                        }
                        break;
                    case "getall":
                        List<Account> accounts = dbm.getAllUsers();
                        for (Account a : accounts) {
                            System.out.println("view Tweets from user " + a.getName() + "(" + a.getUsername() + ")? y/n");
                            String cmd = scanner.nextLine();
                            if (cmd.equals("n")) continue;
                            TweetSet response = tac.getTweetFromUser(a.getId());
                            System.out.println("Tweets from user " + a.getName() + "(" + a.getUsername() + ")");
                            System.out.println("============================================");
                            for (Tweet t : response.getData()) {
                                System.out.println("view next tweet? y/n");
                                cmd = scanner.nextLine();
                                if (cmd.equals("y")) {
                                    System.out.println(t);
                                } else break;
                            }
                            System.out.println("============================================");
                        }
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
        dbm.close();
        return; //program won't stop without return statement
    }
}
