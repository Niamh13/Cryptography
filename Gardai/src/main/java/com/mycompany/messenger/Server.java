/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messenger;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author josho
 */
public class Server {

    private static final String CSV_FILE = "users.csv"; // File to store user data
    private static ServerSocket servSock;
    private static final int PORT = 1234;
    private static final Map<String, ClientConnection> activeClients = new HashMap<>();
    private static final Map<Integer, String[]> users = new HashMap<>(); // Stores user data in memory
    private static int nextUserId = 1; // Used to assign unique user IDs

    public static void main(String[] args) {
        System.out.println("Server is running...");

        // Decrypt CSV file before loading users
        if (Files.exists(Paths.get(CSV_FILE))) {
            try {
                SecureCsv.decryptCsv(CSV_FILE);
                System.out.println("CSV file decrypted successfully.");
                loadUsersFromCSV(); // Load users after decryption
                SecureCsv.encryptCsv(CSV_FILE);
                System.out.println("CSV file loaded and encrypted successfully.");
            } catch (Exception e) {
                System.out.println("Error decrypting CSV file: " + e.getMessage());
                e.printStackTrace();
            }
        }

        
        try {
            servSock = new ServerSocket(PORT);
            while (true) {
                Socket clientSocket = servSock.accept();
                ClientConnection client = new ClientConnection(clientSocket, activeClients);
                Thread clientThread = new Thread(client);
                clientThread.start();
            }
        } catch (IOException e) {
            System.out.println("Error starting server on port " + PORT);
            e.printStackTrace();
        }
    }

    public static void loadUsersFromCSV() {
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    int userId = Integer.parseInt(parts[0]);
                    String username = parts[1];
                    String salt = parts[2];
                    String hashedPassword = parts[3];
                    users.put(userId, new String[]{username, salt, hashedPassword});

                    System.out.println("\nLoaded user: " + username); // Debug line
                    if (userId >= nextUserId) {
                        nextUserId = userId + 1;
                    }
                }
            }
            System.out.println("Users loaded from CSV.");
        } catch (IOException e) {
            System.out.println("No CSV file found. Starting fresh.");
        }
    }

    // Save a new user with a hashed password and salt
    public static synchronized boolean saveUser(String username, String password) {
        for (String[] userData : users.values()) {
            if (userData[0].equals(username)) {
                return false;
            }
        }

        int userId = nextUserId++;
        String salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);

        users.put(userId, new String[]{username, salt, hashedPassword});
        saveUserToCSV(userId, username, salt, hashedPassword);
        return true;
    }

    public static synchronized boolean loginUser(String username, String password) {
        for (String[] userData : users.values()) {
            String storedUsername = userData[0];
            String storedSalt = userData[1];
            String storedHashedPassword = userData[2];

            if (storedUsername.equals(username)) {
                String hashedPassword = hashPassword(password, storedSalt);
                return hashedPassword.equals(storedHashedPassword);
            }
        }
        return false;
    }

    private static void saveUserToCSV(int userId, String username, String salt, String hashedPassword) {
    // Ensure the CSV file is decrypted before writing
    try {
        SecureCsv.decryptCsv(CSV_FILE);
    } catch (Exception e) {
        System.out.println("Error decrypting CSV file: " + e.getMessage());
        return; // Exit if decryption fails
    }

    // Write user data to the CSV file
    try (FileWriter fw = new FileWriter(CSV_FILE, true);
         BufferedWriter bw = new BufferedWriter(fw);
         PrintWriter out = new PrintWriter(bw)) {

        out.println(userId + "," + username + "," + salt + "," + hashedPassword);

    } catch (IOException e) {
        System.out.println("Error writing to CSV file: " + e.getMessage());
        return; // Exit if writing fails
    }

    // Re-encrypt the CSV file after writing
    try {
        SecureCsv.encryptCsv(CSV_FILE);
        System.out.println("CSV file encrypted after saving.");
    } catch (Exception e) {
        System.out.println("Error encrypting CSV file: " + e.getMessage());
    }
}


    private static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    private static String hashPassword(String password, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Hashing error", e);
        }
    }
}
