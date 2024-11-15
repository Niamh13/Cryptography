/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messenger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 *
 * @author joshokeeffe
 */
public class Encrypt {

    // generates secretKey for AES encryption
    public static SecretKey getSecretKey() throws Exception {
        // hardcoded passphrase
        String passphrase = "super_secret_password";
        // uses SHA-256 to hash the passphrase
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        // generates the bytes for the passphrase and creates secretKeySpec object
        byte[] keyBytes = sha.digest(passphrase.getBytes("UTF-8"));
        byte[] key16Bytes = Arrays.copyOf(keyBytes, 16); // hash is truncated to 16 bytes
        return new SecretKeySpec(key16Bytes, "AES");
    }

    // method to encrypt data
    public static String encrypt(String plainText, SecretKey secretKey) throws Exception {
        // initialises the cipher object
        Cipher cipher = Cipher.getInstance("AES");
        // sets cipher mode to ENCRYPT
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        // encrypts the plain text
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        // returns base64-encoded encrypted text
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Method to decrypt data
    public static String decrypt(String encryptedText, SecretKey secretKey) throws Exception {
        // initialises the cipher object
        Cipher cipher = Cipher.getInstance("AES");
        // sets cipher mode to DECRYPT
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        // decrypts the text
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        // returns a string of decrypted bytes
        return new String(decryptedBytes);
    }
}
