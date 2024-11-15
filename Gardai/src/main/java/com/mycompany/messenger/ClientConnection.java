/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author josho
 */
// implements runnable to handle each client seperatly
public class ClientConnection implements Runnable {

    // socket connects client to server
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    // map of active clients
    private Map<String, ClientConnection> activeClients;

    public ClientConnection(Socket clientSocket, Map<String, ClientConnection> activeClients) {
        this.clientSocket = clientSocket;
        this.activeClients = activeClients;
    }

    // method to handle input and output streams for communication
    @Override
    public void run() {
        try {
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // store client action in string
            String action;
            while ((action = in.readLine()) != null) {
                if ("register".equalsIgnoreCase(action)) {
                    // calls registration method
                    registration();
                } else if ("login".equalsIgnoreCase(action)) {
                    // calls login method
                    login();
                }
            }
        } catch (IOException e) {
            System.err.println("Error during client communication: " + e.getMessage());
            e.printStackTrace();
        } finally {
            close();
        }
    }

    // handle user registration
    private void registration() throws IOException {
        // read username and password from client
        String username = in.readLine();
        String password = in.readLine();

        // attempt to save username and password
        if (Server.saveUser(username, password)) {
            out.println("Registration successful.");
            System.out.println("\nUser saved: " + username);
        } else {
            out.println("Registration failed: Username already exists.");
        }
    }

    // user login
    private void login() throws IOException {
        // read username and password from client
        String username = in.readLine();
        String password = in.readLine();

        if (Server.loginUser(username, password)) {
            out.println("Login successful.");
            System.out.println("\n" + username + ": is online");

            // add client to map of active clients
            synchronized (activeClients) {
                activeClients.put(username, this);
            }
            startChat(username);
        } else {
            out.println("Login failed: Incorrect username or password.");
        }
    }

    // manage chats between clients
    private void startChat(String username) {
        try {
            out.println("Welcome " + username + "!");
            out.println("Enter the username of the user you want to chat with:");
            // get target username
            String targetUsername = in.readLine();

            ClientConnection targetClient;
            synchronized (activeClients) {
                targetClient = activeClients.get(targetUsername);
            }

            // if target client is not online, exit
            if (targetClient == null) {
                out.println("User " + targetUsername + " is not online. Connection closing...");
                return;
            }

            out.println("You are now chatting with " + targetUsername + ". Type 'STOP' to end the chat.");

            String message;
            while ((message = in.readLine()) != null) {
                // if message equals "STOP", end the chat
                if ("STOP".equalsIgnoreCase(message)) {
                    out.println("Chat ended. Goodbye!");
                    break;
                }

                // log message and send to target client
                System.out.println("\nSender: " + username + "\nReceiver: " + targetUsername + "\nMessage: " + message);
                targetClient.sendMessage(message);
            }
        } catch (IOException e) {
            System.err.println("Error during chat: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // close when chat ends and remove client from active clients map
            close();
            synchronized (activeClients) {
                activeClients.remove(username);
            }
        }
    }

    // method to send messages
    public void sendMessage(String message) {
        out.println(message);
    }

    // close
    private void close() {
        try {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
            if (clientSocket != null && !clientSocket.isClosed()) {
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}
