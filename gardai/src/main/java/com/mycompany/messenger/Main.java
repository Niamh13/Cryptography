/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messenger;

import javax.swing.JOptionPane;

/**
 *
 * @author joshokeeffe
 */
public class Main {
    
    public static void main(String args[]) {
        String input = JOptionPane.showInputDialog("Enter Game: ");
        if(input.equalsIgnoreCase("game")) {
            Cipher.Play();
        }
    }
}
