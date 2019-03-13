package com.alain;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Game {
    private static final Logger logger = LogManager.getLogger();
    private Scanner sc = new Scanner(System.in);
    private boolean win;

    //---------------------- CONSTRUCTOR ------------------------------

    protected Game() {
        this.win = false;
    }

    //----------------------- METHODS ----------------------------------

    public abstract void startGame();

    public abstract void playTurn();

    public abstract void playTurn(int trialNb, int[] solutionCombination);

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
        logger.trace("Combination generated" + Arrays.toString(generateCombination));
        return generateCombination;
    }

    /**
     * Ask to input a combination, then control it (length and type).
     * Length of combination is set by var nbDigits, 0 is not accepted
     */
    protected int[] inputCombination(int nbDigits, int nbMax){
        //Controlling input
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
                logger.warn(e.getMessage());
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
                System.out.println("Bravo ! Vous avez gagné en " + (trialNb) +" essais !");
            }else{
                System.out.println("Désolé, vous avez perdu ! La combinaison secrète était : " + combination + "\n");
            }
        }
    }

    /**
     * Display text according to ending of the party, win or loss
     */
    protected void endGameDuel(boolean gameComputer, boolean gamePlayer, int trialNb, String computerCombination, String playerCombination) {
        System.out.println("=====================================================================\n");
        if (gameComputer && gamePlayer) {
            System.out.println("Match Nul ! Vous avez trouvé les combinaisons secrètes en même temps, en " + (trialNb) + " essais !\n");
        } else if (gameComputer){
            System.out.println("Désolé, vous avez perdu ! L'ordinateur a trouvé la combinaison en " + (trialNb) + " essais !\n");
        } else if (gamePlayer){
            System.out.println("Bravo ! Vous avez battu l'ordinateur en " + (trialNb) +" essais !\n");
        }else{
            System.out.println("Match nul ! Ni vous, ni l'ordinateur, n'avez trouvé les combinaisons secrètes !\n");
            System.out.println("La combinaison secrète de l'ordinateur était : " + computerCombination);
            System.out.println("Votre combinaison secrète était : " + playerCombination + "\n");
        }
        System.out.println("=====================================================================");
    }


    //---------------- GETTERS & SETTERS--------------------

    public boolean isWin() {
        return win;
    }

    protected void setWin(boolean win) {
        this.win = win;
    }
}
