/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messenger;

import static java.lang.Character.toUpperCase;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * Cipher Mini Game
 * @author niamh
 */
public class CipherGame {
     public static void Play {
        //setting the main arrays
        String inputS;
        char[][] alpha = {
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '}, 
            {'F', 'P', 'U', 'G', 'Z', 'D', 'C', 'S', 'K', 'Q', 'N', 'A', 'V', 'X', 'M', 'E', 'H', 'Y', 'O', 'T', 'W', 'R', 'I', 'J', 'L', 'B', '@'}, 
            {'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', ' '},
            {'Q', 'X', 'S', 'Y', 'E', 'T', 'R', 'D', 'K', '~', 'M', 'F', 'L', 'B', 'G', 'C', 'H', 'U', 'N', 'I', 'J', 'V', 'O', '*', 'P', 'Z', 'W'}, 
            {'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', ' '}, 
            {'O', '}', 'U', 'W', 'F', '@', 'S', 'V', 'E', 'I', 'A', 'J', 'X', 'B', 'K', 'D', 'C', 'L', 'Y', '~', 'M', 'N', 'T', 'Q', 'R', 'P', 'R'}, 
            {'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', ' '}, 
            {'S', 'X', 'T', '+', 'A', 'I', 'Z', 'V', 'B', 'K', 'W', 'H', 'J', 'C', 'Y', 'L', 'D', '=', 'N', 'E', 'G', 'O', 'F', 'P', 'Q', 'R', '&'}, 
            {'O', 'N', 'E', 'F', 'M', 'D', '-', 'G', 'C', 'H', 'Z', 'V', 'B', 'I', 'U', 'Y', 'T', 'K', 'J', 'A', 'S', 'R', 'X', 'Q', 'W', 'P', '!'},
            {'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', ' '}, 
            {'U', '0', 'G', '^', 'Y', 'Z', 'W', 'X', 'L', 'K', 'J', 'I', 'M', 'D', 'C', 'B', 'E', '{', 'F', 'P', 'S', 'H', 'T', 'O', 'V', 'N', '¬'}, 
            {'H', 'I', 'O', 'R', '7', 'T', 'Q', 'V', 'X', '[', 'J', 'K', 'B', 'L', 'Z', 'C', 'M', 'G', 'N', 'D', 'F', 'U', 'E', 'Y', 'W', 'S', '+'},
            {'T', 'O', 'B', 'Y', 'V', 'X', 'Z', 'W', 'D', 'R', 'E', 'N', 'A', 'F', 'J', 'G', 'M', 'U', 'Q', 'C', 'P', 'K', 'H', 'S', 'I', 'L', '-'}, 
            {'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', ' '}, 
            {'C', 'L', 'A', 'I', 'R', '%', 'P', 'Z', 'W', 'S', 'X', 'B', 'M', 'Y', '}', 'Q', 'D', 'T', 'F', 'U', '3', 'G', 'K', 'J', 'H', 'N', ']'}, 
            {'B', 'L', 'I', 'N', 'K', 'Y', 'U', 'Q', '8', 'O', 'W', 'R', 'S', 'E', 'P', 'J', 'D', 'H', 'Z', 'A', 'F', 'M', 'C', 'G', 'V', 'T', '{'}, 
            {'M', 'E', 'R', 'L', 'I', 'N', 'Z', 'T', 'G', 'O', 'U', 'A', 'Y', 'S', 'B', 'H', 'V', 'C', 'K', 'J', 'D', 'X', 'F', 'P', 'W', 'Q', '}'},
            {'Q', 'X', 'Y', 'J', '+', 'A', 'S', 'K', 'L', 'B', 'T', 'I', 'M', 'C', 'N', 'H', 'U', 'D', 'G', 'Z', 'E', 'V', 'O', 'F', 'P', 'W', ' _'}, 
            {'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', ' '},
            {'D', '@', 'H', 'K', 'L', 'N', 'P', '-', 'B', 'T', 'V', 'C', 'X', 'Y', 'W', 'Z', 'U', 'S', 'Q', 'O', 'A', 'M', 'J', 'I', 'G', 'E', ')'}, 
            {'A', 'Z', 'B', 'Y', 'C', 'W', 'D', 'X', 'E', 'V', 'F', 'U', 'G', 'T', 'H', 'S', 'I', 'R', 'K', 'Q', 'J', '$', 'L', 'O', 'M', 'N', '@'}, 
            {'I', 'D', 'S', 'F', 'R', 'W', 'Q', '+', 'X', 'B', 'Z', 'V', 'U', 'E', 'J', 'T', 'K', 'P', 'A', 'O', 'G', 'N', 'M', 'C', 'L', 'H', '~'}, 
            {'C', 'N', 'M', 'A', '2', 'O', 'B', 'S', 'V', 'Z', 'Y', 'H', 'W', 'X', 'I', 'T', '£', 'Q', 'K', 'P', 'R', 'U', 'D', 'J', 'L', 'F', '#'}, 
            {'K', 'V', 'A', '_', 'B', 'W', 'U', 'C', 'L', '4', 'D', 'T', '=', 'E', 'Z', 'N', 'F', 'Y', 'O', 'S', 'H', 'P', 'G', 'Q', 'I', 'R', '?'}, 
            {'O', 'F', 'Q', 'W', '3', 'M', 'N', '-', 'A', 'L', 'C', 'R', 'K', 'U', 'T', 'B', '+', 'J', 'G', 'H', 'Z', 'S', '9', 'E', 'I', 'P', '!'}, 
            {'}', 'Y', '5', 'G', 'A', 'N', 'P', 'O', 'C', 'R', 'T', 'D', 'J', 'Z', 'L', 'K', 'X', 'F', 'V', '3', 'I', 'U', 'H', 'S', 'M', 'Q', '&'}, 
            {'H', 'K', '5', 'A', 'Q', 'V', 'B', 'S', 'X', '8', 'C', 'W', 'U', 'Z', 'D', 'Y', 'L', 'T', 'E', '2', 'P', 'F', 'N', '[', 'G', 'I', '$'}};
        
        String[] guessWord = {"Apple of my Eye", "Salt Everything", "Campgrounds", "Ghostbuster", "Cash is King", "Deviously", "Needle in a Haystack", "Recyclables", "Cat Got Your Tongue", "Indubitably", "Pressurised", "Misquoted", "Nightmares", "Shipwrecked", "Brainstormed Ideas", "Sculptured", "Haemoglobin", "Drumsticks Set", "Methodology Terms", "Exemplified", "Xylophone", "Seven Years of Bad Luck", "Haemoneurothorax", "Vibrantly", "Doomed people never blame themselves", "People in Glasshouse should not through stones"};
        
        Random rand = new Random();
        

        //set type
        int type = Integer.parseInt(JOptionPane.showInputDialog(null, "Mini Game Type 1 \nTo Encrypt Type 2 \nTo Decrypt Type 3"));

        switch (type) {
            case 1:
                Boolean play = true;

                
                
                for(int level = 1; play == true; level++){
                    //example word 1
                    String example1E = "Baker Quiz";
                    StringBuilder example1 = new StringBuilder(example1E);
                    //changing each character
                    for (int s = 0; s < example1.length(); s++) {
                        char in = toUpperCase(example1.charAt(s));
                        System.out.println(in);
                        for (int i = 0; i < 27; i++) {
                            char letter = alpha[0][i];
                            System.out.println(letter);
                            if (letter == in) {
                                example1.deleteCharAt(s);
                                example1.insert(s, alpha[level][i]);
                            }
                        }
                    }

                    //example word 2
                    String example2E = "Cloth";
                    StringBuilder example2 = new StringBuilder(example2E);
                    //changing each character
                    for (int s = 0; s < example2.length(); s++) {
                        char in = toUpperCase(example2.charAt(s));
                        System.out.println(in);
                        for (int i = 0; i < 27; i++) {
                            char letter = alpha[0][i];
                            System.out.println(letter);
                            if (letter == in) {
                                example2.deleteCharAt(s);
                                example2.insert(s, alpha[level][i]);
                            }
                        }
                    }

                    //example word 3
                    String example3E = "Jumping Fox";
                    StringBuilder example3 = new StringBuilder(example3E);
                    //changing each character
                    for (int s = 0; s < example3.length(); s++) {
                        char in = toUpperCase(example3.charAt(s));
                        for (int i = 0; i < 27; i++) {
                            char letter = alpha[0][i];
                            if (letter == in) {
                                example3.deleteCharAt(s);
                                example3.insert(s, alpha[level][i]);
                            }
                        }
                    }
                    
                    //example word 4
                    String example4E = "Draining";
                    StringBuilder example4 = new StringBuilder(example4E);
                    //changing each character
                    for (int s = 0; s < example4.length(); s++){
                        char in = toUpperCase(example4.charAt(s));
                        for (int i = 0; i < 27; i++){
                            char letter = alpha[0][i];
                            if (letter == in){
                                example4.deleteCharAt(s);
                                example4.insert(s, alpha[level][i]);
                            }
                        }
                    }
                    
                    //example word 5
                    String example5E = "Vowel";
                    StringBuilder example5 = new StringBuilder(example5E);
                    //Changing each character
                    for (int s = 0; s < example5.length(); s++){
                        char in = toUpperCase(example5.charAt(s));
                        for (int i = 0; i < 27; i++){
                            char letter = alpha[0][i];
                            if(letter == in){
                                example5.deleteCharAt(s);
                                example5.insert(s, alpha[level][i]);
                            }
                        }
                    }

                    //doing the guess word
                    String wordGuess = guessWord[rand.nextInt((25 - 0 + 1) + 0)];
                    StringBuilder guessWE = new StringBuilder(wordGuess);
                    //changing each character
                    for (int s = 0; s < guessWE.length(); s++) {
                        char in = toUpperCase(guessWE.charAt(s));
                        System.out.println(in);
                        for (int i = 0; i < 27; i++) {
                            char letter = alpha[0][i];
                            System.out.println(letter);
                            if (letter == in) {
                                guessWE.deleteCharAt(s);
                                guessWE.insert(s, alpha[level][i]);
                            }
                        }
                    }

                    if (level == 2){
                        JOptionPane.showMessageDialog(null, "Caeser Cipher involves shifting each letter of a message by a set number of letters over.\nWas developed by  Roman General Gaius Julius Caesar for correspondence with friends in Roman.\nDid ypu figure out what the shift key was?");
                    }
                    else if (level==1){
                        JOptionPane.showMessageDialog(null, "Mono-alphabetic Substitution is a one-to-one cipher.\nHistorically used by Romans with a simple cyclic displacement of the alphabet as the substitution.");
                    }

                    Boolean guessed = false;

                    for (int numG = 1; guessed == false; numG++) {
                        String guess = JOptionPane.showInputDialog(null, "Try Your Luck: \nExamples of the Cipher Code\n\n" + example1 + " ------- " + example1E + "\n" + example2 + " ------- " + example2E + "\n" + example3 + " ------- " + example3E + "\n" + example4 + " ------- " + example4E + "\n\n\nTry to translate: " + guessWE);
                        if (guess.equalsIgnoreCase(wordGuess)) {
                            JOptionPane.showMessageDialog(null, "Well done, It took you " + numG + " guesses to get it correct");
                            int cont = Integer.parseInt(JOptionPane.showInputDialog(null, "Do you want to do the next level?\n(Yes = 1 or No = 2"));
                            if (cont == 1) {
                                play = true;
                            } else if (cont == 2) {
                                play = false;
                            }
                            guessed = true;
                        }
                    }
                }
                
                break;



            case 2:
                //get input
                String input = JOptionPane.showInputDialog(null, "Enter Message: ");
                StringBuffer inputB = new StringBuffer(input);
                int key;
                key = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter Cipher Number: "));
                
                //changing each character
                for (int s = 0; s < inputB.length(); s++) {
                    char in = toUpperCase(input.charAt(s));
                    System.out.println(in);
                    for (int i = 0; i < 27; i++) {
                        char letter = alpha[0][i];
                        System.out.println(letter);
                        if (letter==in) {
                            inputB.deleteCharAt(s);
                            inputB.insert(s, alpha[key][i]);
                        }
                    }
                }
                
                inputS = inputB.toString();
                if (input.equalsIgnoreCase(inputS)) {
                    System.out.println("Error");
                }
                else{
                    System.out.println(inputB);
                    JOptionPane.showMessageDialog(null, inputS);
                }
                break;
            case 3:
                //get input
                String inputC = JOptionPane.showInputDialog(null, "Enter Message: ");
                StringBuilder input1 = new StringBuilder(inputC);
                
                Boolean wordCorrect = false;
                
                String check = JOptionPane.showInputDialog(null, "Know what the Cipher number is? \n(Yes or No)");
                
                for(int ver = 1; wordCorrect == false; ver++){
                    
                    //if cipher number is known
                    if(check.equalsIgnoreCase("Yes")){
                        ver = Integer.parseInt(JOptionPane.showInputDialog(null, "What is the Cipher number?"));
                    }
                    
                    //checking each char with the particalur cipher number
                    for(int s = 0; s < input1.length(); s++){
                        char in = toUpperCase(inputC.charAt(s));
                        System.out.println(in);
                        for (int i = 0; i < 27; i++){
                            char letter = alpha[ver][i];
                            if (in == letter){
                                input1.deleteCharAt(s);
                                input1.insert(s, alpha[0][i]);
                            }
                        }
                    }
                    String correct = JOptionPane.showInputDialog(null, "Is " + input1.toString() + " correct? \n(Yes: 1 or No: 2)");
                    if(correct.equalsIgnoreCase("1")){
                        System.out.println(inputC + " is decrypted as " + input1);
                        JOptionPane.showMessageDialog(null, (inputC + " is decrypted as " + input1 + " at cipher " + ver));
                        wordCorrect = true;
                    }
                    else if(correct.equalsIgnoreCase("2") && check.equalsIgnoreCase("Yes")){
                        ver = 1;
                    }
                    else if(correct.equalsIgnoreCase("2")){
                        wordCorrect = false;
                    }
                    else if(!correct.equalsIgnoreCase("1") || !correct.equalsIgnoreCase("2")){
                        JOptionPane.showMessageDialog(null, "Incorrect entry\n will contuine to the next version");
                    }
                }
                break;
                
            default:
                System.out.println("Error");
                JOptionPane.showMessageDialog(null, "Incorrect Type Entered");
                break;
        }
    }
}
