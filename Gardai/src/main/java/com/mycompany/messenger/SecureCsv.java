/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messenger;

import java.nio.file.Files;
import java.nio.file.Paths;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author josho
 */
public class SecureCsv {

    //16 bytes key for AES algorithm
    private static final String KEY = "1234567887654321";
    private static final String INIT_VECTOR = "abcdefghijklmnop"; //16 bytes IV for AES algorithm

    //CSV file encryption
    public static void encryptCsv(String filePath) throws Exception {
        if (!Files.exists(Paths.get(filePath))) {
            // Nothing happens if the file doesn't exsist
            return;
        }
        // Read the content in the file as bytes
        byte[] content = Files.readAllBytes(Paths.get(filePath));
        Files.write(Paths.get(filePath), encrypt(content, KEY));
    }   //Encrypt and overwrite the content in the file

    public static String decryptCsv(String filePath) throws Exception {
        if (!Files.exists(Paths.get(filePath))) {
            System.out.println("File not found: " + filePath);
            return null;
        }
        byte[] content = Files.readAllBytes(Paths.get(filePath));
        String decryptedContent = new String(decrypt(content, KEY));

        // Write the decrypted content back to the CSV file
        Files.write(Paths.get(filePath), decryptedContent.getBytes());

        System.out.println("Decrypted content:\n" + decryptedContent);
        return decryptedContent;
    }

    // Encrypt the data with AES algorithm
    private static byte[] encrypt(byte[] data, String key) throws Exception {
        //Use CBC mode to add randomness to make the data more secure, PKCS5Padding to ensure the data length to *16 bytes
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES"); // Create AES key

        //Set iv by converting string to byte and store it
        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
        //Initialize the cipher with encrypt mode and set the secret key and IV
        return cipher.doFinal(data); //Encrypt data and return
    }

    //Decrypt the data with AES algorithm
    private static byte[] decrypt(byte[] data, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES"); // Create AES key

        IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        //Initialize the cipher with encrypt mode and set the secret key and IV
        return cipher.doFinal(data); //Decrypt data and return
    }
}
