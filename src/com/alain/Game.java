package com.alain;

import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Game {

    private Scanner sc = new Scanner(System.in);
    private boolean win;

    //---------------------- CONSTRUCTOR ------------------------------


    public Game() {
        this.win = false;
    }

    //----------------------- METHODS ----------------------------------

    public abstract void startGame();

    public abstract void playTurn();

    /**
     * Display the GameTitle according to the game mode and the level
     */
    protected void displayGameTitle(String gameName, String modeName, String levelName){
        System.out.println("========================================");
        System.out.println(gameName + " : Niveau " + levelName);
        System.out.println("========================================\n");
        System.out.println("============= Mode " + modeName +" ============\n");
    }

    /**
     * generate a combination with Math.random
     * of nbDigits length, and highest number possible nbMax
     * @return generated combination
     */
    protected int[] generateCombination(int nbDigits, int nbMax){
        int[] generateCombination = new int[nbDigits];
        int i = 0;
        while (i < nbDigits) {
            generateCombination[i] = ((int) Math.floor(Math.random() * 10));
            while (generateCombination[i] >= nbMax) {
                generateCombination[i] = ((int) Math.floor(Math.random() * 10));
            }
            i++;
        }
        return generateCombination;
    }

    /**
     * Ask to input a combination, then control it (length and type).
     * Length of combination is set by var nbDigits, 0 is not accepted
     */
    protected int[] inputCombination(int nbDigits, int nbMax){
        //Controlling
        String playerCombination="";
        boolean responseIsGood;
        do {
            try {
                playerCombination = sc.next();
                responseIsGood = true;
                //We use a dynamic regular expression to check the input, setup by the nbColors (range of selection) and  nbDigits (number of digits)
                if (!(playerCombination.matches("^[0-" + (nbMax-1) + "]{" + nbDigits + "}$")))
                    throw new InputMismatchException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Vous devez saisir une suite de " + nbDigits +" entiers, compris entre 0 et " + (nbMax-1) + ".\n");
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
        return combinationToArray(playerCombination, nbDigits);
    }

    /**
     * Split the combination manually input,
     * and put it in the Array playerCombinationArray
     * Using modulo method
     */
    private int[] combinationToArray(String combination, int nbDigits){
        int[] playerCombination = new int[nbDigits];
        char character;
        String characterToString;

        for (int i=0; i<combination.length();i++) {
            character = combination.charAt(i);
            characterToString = String.valueOf(character);
            playerCombination[i] = Integer.parseInt(characterToString);
        }
        return playerCombination;
    }

    /**
     * Display text according to ending of the party, win or loss
     * @param trialNb number of trials played.
     * @param combination that was to found
     */
    protected void endGameResult (String className, int trialNb, String combination) {
        if (className.contains("Defender")){
            if (this.win) {
                System.out.println("Désolé ! Vous avez perdu, l'ordinateur a trouvé la combinaison  en " + (trialNb) + " essais !");
            } else {
                System.out.println("Bravo, vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison secrète, qui était : " + combination + "\n");
            }
        }else{
            if (this.win){
                System.out.println("\nBravo ! Vous avez gagné en " + (trialNb) +" essais !");
            }else{
                System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " + combination + "\n");
            }
        }
    }


    //---------------- GETTERS & SETTERS--------------------

    public boolean isWin() {
        return win;
    }

    protected void setWin(boolean win) {
        this.win = win;
    }
}
