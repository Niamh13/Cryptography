/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.messenger;

import static java.lang.Character.toUpperCase;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author joshokeeffe
 */
public class Cipher {

    public static void Play() {
        //setting the main arrays
        String inputS;
        char[][] alpha = {
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '},
            {'F', 'P', 'U', 'G', 'Z', 'D', 'C', 'S', 'K', 'Q', 'N', 'A', 'V', 'X', 'M', 'E', 'H', 'Y', 'O', 'T', 'W', 'R', 'I', 'J', 'L', 'B', '@'},
            {'A', 'T', 'C', 'B', 'W', 'U', 'E', 'D', 'F', 'Z', 'V', 'N', '#', 'H', 'I', 'O', 'M', 'J', 'K', 'P', 'L', 'Q', 'Y', 'R', 'S', 'X', 'G'},
            {'Q', 'X', 'S', 'Y', 'E', 'T', 'R', 'D', 'K', '~', 'M', 'F', 'L', 'B', 'G', 'C', 'H', 'U', 'N', 'I', 'J', 'V', 'O', '*', 'P', 'Z', 'W'},
            {'Z', 'Y', 'X', 'W', 'V', 'U', '*', 'S', 'R', 'Q', 'P', 'O', 'N', 'M', 'L', 'K', 'J', '#', 'H', 'G', 'F', 'E', 'D', 'C', 'B', 'A', '£'},
            {'O', '}', 'U', 'W', 'F', '@', 'S', 'V', 'E', 'I', 'A', 'J', 'X', 'B', 'K', 'D', 'C', 'L', 'Y', '~', 'M', 'N', 'T', 'Q', 'R', 'P', 'R'},
            {'A', 'R', 'Q', 'S', 'P', 'F', 'B', '=', 'N', 'C', 'T', 'M', 'D', 'U', 'V', 'K', '+', 'W', 'J', 'X', 'I', 'F', 'Y', 'G', '-', 'H', 'Z'},
            {'S', 'X', 'T', 'U', 'A', 'I', 'Z', 'V', 'B', 'K', 'W', 'H', 'J', 'C', 'Y', 'L', 'D', 'M', 'N', 'E', 'G', 'O', 'F', 'P', 'Q', 'R', '&'},
            {'O', 'N', 'E', 'F', 'M', 'D', 'L', 'G', 'C', 'H', 'Z', 'V', 'B', 'I', 'U', 'Y', 'T', 'K', 'J', 'A', 'S', 'R', 'X', 'Q', 'W', 'P', '!'},
            {'T', 'U', 'M', 'A', 'S', 'O', 'N', 'I', 'E', 'L', 'Z', 'Y', 'X', 'W', 'R', 'Q', 'P', 'V', 'J', 'K', 'H', 'G', 'F', 'D', 'C', 'B', '?'},
            {'U', 'R', 'G', 'A', 'Y', 'Z', 'W', 'X', 'L', 'K', 'J', 'I', 'M', 'D', 'C', 'B', 'E', 'Q', 'F', 'P', 'S', 'H', 'T', 'O', 'V', 'N', '¬'},
            {'H', 'I', 'O', 'R', 'P', 'T', 'Q', 'V', 'X', 'A', 'J', 'K', 'B', 'L', 'Z', 'C', 'M', 'G', 'N', 'D', 'F', 'U', 'E', 'Y', 'W', 'S', '+'},
            {'T', 'O', 'B', 'Y', 'V', 'X', 'Z', 'W', 'D', 'R', 'E', 'N', 'A', 'F', 'J', 'G', 'M', 'U', 'Q', 'C', 'P', 'K', 'H', 'S', 'I', 'L', '-'},
            {'J', 'I', 'M', 'D', 'T', 'U', 'W', 'E', 'L', 'N', 'F', 'V', 'S', 'X', 'B', 'O', 'G', 'Y', 'A', 'P', 'H', 'C', 'Q', 'K', 'R', 'Z', '['},
            {'C', 'L', 'A', 'I', 'R', 'E', 'P', 'Z', 'W', 'S', 'X', 'B', 'M', 'Y', 'V', 'Q', 'D', 'T', 'F', 'U', 'O', 'G', 'K', 'J', 'H', 'N', ']'},
            {'B', 'L', 'I', 'N', 'K', 'Y', 'U', 'Q', 'X', 'O', 'W', 'R', 'S', 'E', 'P', 'J', 'D', 'H', 'Z', 'A', 'F', 'M', 'C', 'G', 'V', 'T', '{'},
            {'M', 'E', 'R', 'L', 'I', 'N', 'Z', 'T', 'G', 'O', 'U', 'A', 'Y', 'S', 'B', 'H', 'V', 'C', 'K', 'J', 'D', 'X', 'F', 'P', 'W', 'Q', '}'},
            {'Q', 'X', 'Y', 'J', 'R', 'A', 'S', 'K', 'L', 'B', 'T', 'I', 'M', 'C', 'N', 'H', 'U', 'D', 'G', 'Z', 'E', 'V', 'O', 'F', 'P', 'W', '_'},
            {'H', 'Q', 'M', 'A', 'W', 'R', 'B', 'X', 'I', 'S', 'N', 'Y', 'C', 'K', 'T', 'D', 'O', 'U', 'E', 'J', 'Z', 'F', 'P', 'L', 'V', 'G', '('},
            {'D', 'F', 'H', 'K', 'L', 'N', 'P', 'R', 'B', 'T', 'V', 'C', 'X', 'Y', 'W', 'Z', 'U', 'S', 'Q', 'O', 'A', 'M', 'J', 'I', 'G', 'E', ')'},
            {'A', 'Z', 'B', 'Y', 'C', 'W', 'D', 'X', 'E', 'V', 'F', 'U', 'G', 'T', 'H', 'S', 'I', 'R', 'K', 'Q', 'J', 'P', 'L', 'O', 'M', 'N', '@'},
            {'I', 'D', 'S', 'F', 'R', 'W', 'Q', 'Y', 'X', 'B', 'Z', 'V', 'U', 'E', 'J', 'T', 'K', 'P', 'A', 'O', 'G', 'N', 'M', 'C', 'L', 'H', '~'},
            {'C', 'N', 'M', 'A', 'E', 'O', 'B', 'S', 'V', 'Z', 'Y', 'H', 'W', 'X', 'I', 'T', 'G', 'Q', 'K', 'P', 'R', 'U', 'D', 'J', 'L', 'F', '#'},
            {'K', 'V', 'A', 'J', 'B', 'W', 'U', 'C', 'L', 'X', 'D', 'T', 'M', 'E', 'Z', 'N', 'F', 'Y', 'O', 'S', 'H', 'P', 'G', 'Q', 'I', 'R', '?'},
            {'O', 'F', 'Q', 'W', 'D', 'M', 'N', 'V', 'A', 'L', 'C', 'R', 'K', 'U', 'T', 'B', 'X', 'J', 'G', 'H', 'Z', 'S', 'Y', 'E', 'I', 'P', '!'},
            {'B', 'Y', 'E', 'G', 'A', 'N', 'P', 'O', 'C', 'R', 'T', 'D', 'J', 'Z', 'L', 'K', 'X', 'F', 'V', 'W', 'I', 'U', 'H', 'S', 'M', 'Q', '&'},
            {'H', 'K', 'O', 'A', 'Q', 'V', 'B', 'S', 'X', 'M', 'C', 'W', 'U', 'Z', 'D', 'Y', 'L', 'T', 'E', 'R', 'P', 'F', 'N', 'J', 'G', 'I', '$'}};

        String[] guessWord = {"Apple of my Eye", "Salt Everything", "Campgrounds", "Ghostbuster", "Cash is King", "Deviously", "Needle in a Haystack", "Recyclables", "Cat Got Your Tongue", "Indubitably", "Pressurised", "Misquoted", "Nightmares", "Shipwrecked", "Brainstormed Ideas", "Sculptured", "Haemoglobin", "Drumsticks Set", "Methodology Terms", "Exemplified", "Xylophone", "Seven Years of Bad Luck", "Haemoneurothorax", "Vibrantly", "Doomed people never blame themselves", "People in Glasshouse should not through stones"};

        Random rand = new Random();

        //set type
        int type = Integer.parseInt(JOptionPane.showInputDialog(null, "Mini Game Type 1 \nTo Encrypt Type 2 \nTo Decrypt Type 3"));

        switch (type) {
            case 1:
                Boolean play = true;

                for (int level = 1; play == true; level++) {
                    //example word 1
                    String example1E = "Bakers Quiz";
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
                    String example2E = "Clothes";
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
                    String example3E = "Jumpy Fox";
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
                    for (int s = 0; s < example4.length(); s++) {
                        char in = toUpperCase(example4.charAt(s));
                        for (int i = 0; i < 27; i++) {
                            char letter = alpha[0][i];
                            if (letter == in) {
                                example4.deleteCharAt(s);
                                example4.insert(s, alpha[level][i]);
                            }
                        }
                    }

                    //example word 5
                    String example5E = "Vowel";
                    StringBuilder example5 = new StringBuilder(example5E);
                    //Changing each character
                    for (int s = 0; s < example5.length(); s++) {
                        char in = toUpperCase(example5.charAt(s));
                        for (int i = 0; i < 27; i++) {
                            char letter = alpha[0][i];
                            if (letter == in) {
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
                        if (letter == in) {
                            inputB.deleteCharAt(s);
                            inputB.insert(s, alpha[key][i]);
                        }
                    }
                }

                inputS = inputB.toString();
                if (input.equalsIgnoreCase(inputS)) {
                    System.out.println("Error");
                } else {
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

                for (int ver = 1; wordCorrect == false; ver++) {

                    //if cipher number is known
                    if (check.equalsIgnoreCase("Yes")) {
                        ver = Integer.parseInt(JOptionPane.showInputDialog(null, "What is the Cipher number?"));
                    }

                    //checking each char with the particalur cipher number
                    for (int s = 0; s < input1.length(); s++) {
                        char in = toUpperCase(inputC.charAt(s));
                        System.out.println(in);
                        for (int i = 0; i < 27; i++) {
                            char letter = alpha[ver][i];
                            if (in == letter) {
                                input1.deleteCharAt(s);
                                input1.insert(s, alpha[0][i]);
                            }
                        }
                    }
                    String correct = JOptionPane.showInputDialog(null, "Is " + input1.toString() + " correct? \n(Yes: 1 or No: 2)");
                    if (correct.equalsIgnoreCase("1")) {
                        System.out.println(inputC + " is decrypted as " + input1);
                        JOptionPane.showMessageDialog(null, (inputC + " is decrypted as " + input1 + " at cipher " + ver));
                        wordCorrect = true;
                    } else if (correct.equalsIgnoreCase("2") && check.equalsIgnoreCase("Yes")) {
                        ver = 1;
                    } else if (correct.equalsIgnoreCase("2")) {
                        wordCorrect = false;
                    } else if (!correct.equalsIgnoreCase("1") || !correct.equalsIgnoreCase("2")) {
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
