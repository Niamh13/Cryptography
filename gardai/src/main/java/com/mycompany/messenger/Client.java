/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messenger;

/**
 *
 * @author joshokeeffe
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;
import javax.crypto.SecretKey;

public class Client {
    private static InetAddress host;
    private static final int PORT = 1234;
    private static Socket connection;
    // key for encrypting/decrypting messages
    private static SecretKey secretKey;
    // reader for reading messages from server
    private static BufferedReader in;
    // writer for sending messages
    private static PrintWriter out;

    public static void main(String[] args) {
        try {
            // initialise and establish connection to server
            host = InetAddress.getLocalHost();
            // generate encryption key
            secretKey = Encrypt.getSecretKey();
            connection = new Socket(host, PORT);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(connection.getOutputStream(), true);
            System.out.println("\nConnected to server successfully.");

            Scanner scanner = new Scanner(System.in);
            // track login status
            boolean loggedIn = false;

            // loops while not logged in
            while (!loggedIn) {
                System.out.println("\nChoose an action: 'login' or 'register' (type 'exit' to quit):");
                String action = scanner.nextLine().trim().toLowerCase();

                // if user wants to exit the program
                if ("exit".equals(action)) {
                    System.out.println("Exiting program...");
                    break;
                }

                // user input validation
                if (!"login".equals(action) && !"register".equals(action)) {
                    System.out.println("Invalid action. Please choose 'login' or 'register'.");
                    continue;
                }

                // ask user for username and validate
                System.out.print("\nEnter username: ");
                String username = scanner.nextLine().trim();

                if (username.isEmpty()) {
                    System.out.println("Error: Username cannot be empty.");
                    continue;
                }

                // ask user for password and validate
                System.out.print("Enter password: ");
                String password = scanner.nextLine().trim();

                if (password.isEmpty()) {
                    System.out.println("Error: Password cannot be empty.");
                    continue;
                }

                // password re-entry during registration
                if ("register".equals(action)) {
                    System.out.print("Re-enter password: ");
                    String passwordRe = scanner.nextLine().trim();

                    if (!password.equals(passwordRe)) {
                        System.out.println("Error: Passwords do not match.");
                        continue;
                    }
                }

                // send login/registration request to the server and process response
                String result = login(action, username, password);
                System.out.println("Server response >>> " + result);

                // if login is successful, start the messaging client
                if ("Login successful.".equals(result)) {
                    loggedIn = true;
                    chatClient();
                }
            }
        } catch (Exception e) {
            System.err.println("Error connecting to server or during client initialization.");
            e.printStackTrace();
            System.exit(1);
        } finally {
            closeConnection();
        }
    }

    // messaging client
    private static void chatClient() {
        try {
            BufferedReader userEntry = new BufferedReader(new InputStreamReader(System.in));

            // welcome message from server
            System.out.println(in.readLine());
            // prompt to enter recipient's username
            System.out.println(in.readLine());

            // ask user to enter target client's username
            String targetUsername = userEntry.readLine();
            // send username to server
            out.println(targetUsername);

            // process server's response
            String response = in.readLine();
            if (response.startsWith("User")) {
                // exit if the client is not found/online
                System.out.println(response);
                return;
            }

            // Chat start confirmation
            System.out.println(response);

            // a new thread is started to handle incoming messages
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String serverMessage;
                        while ((serverMessage = in.readLine()) != null) {
                            // display the other user's message
                            System.out.println(targetUsername + ": " + decryptMessage(serverMessage));
                            System.out.print("\r\nYou: ");
                        }
                    } catch (IOException e) {
                        System.err.println("Connection lost.");
                    }
                }
            }).start();

            // handle outgoing messages from user
            String message;
            while (true) {
                System.out.print("\rYou: ");
                message = userEntry.readLine();

                if ("STOP".equalsIgnoreCase(message)) {
                    System.out.println("\n* Closing connection... *");
                    break;
                }

                try {
                    // encrypt and send message
                    String encryptedMessage = Encrypt.encrypt(message, secretKey);
                    out.println(encryptedMessage);
                } catch (Exception e) {
                    System.err.println("Error encrypting message.");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error during client runtime.");
            e.printStackTrace();
        }
    }

    // method to decrypt messages
    private static String decryptMessage(String message) {
        try {
            return Encrypt.decrypt(message, secretKey);
        } catch (Exception e) {
            return "Decryption error.";
        }
    }

    // method to handle login/registration
    private static String login(String action, String username, String password) {
        try {
            out.println(action);
            out.println(username);
            out.println(password);
            return in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "Connection error!";
        }
    }

    // method to close connection
    private static void closeConnection() {
        try {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
            if (connection != null)
                connection.close();
            System.out.println("\nConnection closed.");
        } catch (IOException e) {
            System.err.println("\nError closing the connection!");
        }
    }
}