package com.alain.mastermind;

import com.alain.Game;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public abstract class MastermindGame implements Game {

    private Scanner sc = new Scanner(System.in);

    private String levelName;
    private static int nbColors;
    private static int nbDigits;
    private static int nbTrials;
    private boolean win;

    //---------------------- CONSTRUCTOR ------------------------------

    protected MastermindGame(String levelName) {
        this.levelName = levelName;
        getParameters(levelName);
        win = false;
    }

    //----------------------- METHODS ----------------------------------

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
     * @return generated combination
     */
    protected int[] generateCombination(){
        int[] generateCombination = new int[nbDigits];
        int i = 0;
        while (i < nbDigits) {
            generateCombination[i] = ((int) Math.floor(Math.random() * 10));
            while (generateCombination[i] >= nbColors) {
                generateCombination[i] = ((int) Math.floor(Math.random() * 10));
            }
            i++;
        }
        return generateCombination;
    }

    /**
     * Pick randomly an object from the list of solutions possible
     * @param listCombinations listCombinations generated earlier
     * @return the combination choosen
     */
    protected int[] chooseCombinationFromList(LinkedList<int[]> listCombinations){
        Random generator = new Random();
        int randomIndex = generator.nextInt(listCombinations.size());
        return listCombinations.get(randomIndex);
    }

    /**
     * Ask to input a combination, then control it (length and type).
     * Length of combination is set by var nbDigits, 0 is not accepted
     */
    protected int[] inputCombination(){
        //Controlling
        String playerCombination="";
        boolean responseIsGood;
        do {
            try {
                playerCombination = sc.next();
                responseIsGood = true;
                //We use a dynamic regular expression to check the input, setup by the nbColors (range of selection) and  nbDigits (number of digits)
                if (!(playerCombination.matches("^[0-" + (nbColors-1) + "]{" + nbDigits + "}$")))
                    throw new InputMismatchException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Vous devez saisir une suite de " + nbDigits +" entiers, compris entre 0 et " + (getNbColors()-1) + ".\n");
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
        return combinationToArray(playerCombination);
    }

    /**
     * Split the combination manually input,
     * and put it in the Array playerCombination
     */
    private int[] combinationToArray(String combination){
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
     * compare two combinations and return blacks and whites pen result
     * @param testCombination combination tested
     * @param solutionCombination point of comparison
     * @return blacksAndWhites result int[] of blacks and whites pins
     */
    protected int[] compareInput(int[] testCombination, int[] solutionCombination) {
        int blackHits = 0;
        int whiteHits = 0;
        int[] blacksAndWhites = new int [2];
        int[] test = testCombination.clone();
        int[] solution = solutionCombination.clone();

        int i;
        //We first check if the numbers are in good spot, and we change their value for not finding them after
        for (i = 0; i < solutionCombination.length; i++) {
            if (test[i] == solution[i]) {
                blackHits++;
                test[i] += 10;
                solution[i] += 100;
            }
        }
        blacksAndWhites[0] = blackHits;

        for (i = 0; i < solutionCombination.length; i++) {
            for (int j = 0; j < solutionCombination.length; j++) {
                if (test[i] == solution[j]) {
                    whiteHits++;
                    test[i] += 10;
                    solution[j] += 100;
                }
            }
        }
        blacksAndWhites[1] = whiteHits;
        return blacksAndWhites;
    }

    /**
     * print out the result of a turn
     * @param trialNb number of trial currently played
     * @param blacksAndWhites result of the test
     * @param combination combination tested
     */
    protected void displayResult(int trialNb, int[] blacksAndWhites, int[] combination){
        if (blacksAndWhites[0] == nbDigits){
            this.win = true;
        }

        String result = blacksAndWhites[0] + " bien placé(s) - " + blacksAndWhites[1] + " mal placé(s)";
        System.out.println("Essai n°" + (trialNb+1) + " : " + combinationToString(combination) + "\nRéponse :   " + result + "\n");
    }

    /**
     * Convert int[] combination to String
     * @param combination to convert
     * @return combinationString combination converted
     */
    protected String combinationToString(int[] combination) {
        String combinationString="";
        for (int value : combination) {
            combinationString += value;
        }
        return combinationString;
    }

    /**
     * Ask for replay
     * @return boolean according to the choice
     */
    @Override
    public boolean playAgain() {
        String replay;
        sc.nextLine();
        System.out.println("\nRejouer ? O/N");
        replay = sc.nextLine();
        replay = replay.toLowerCase();
        return replay.equals("o") || replay.equals("oui");
    }

    //---------------- GETTERS & SETTERS--------------------

    protected int getNbTrials() {
        return nbTrials;
    }

    protected int getNbDigits() {
        return nbDigits;
    }

    protected String getLevelName() {
        return levelName;
    }

    protected int getNbColors() {
        return nbColors;
    }

    protected boolean isWin() {
        return win;
    }

    private void getParameters(String levelName) {
        switch (levelName) {
            case "Facile":
                nbColors = 6;
                nbDigits = 4;
                nbTrials = 10;
                break;
            case "Normal":
                nbColors = 8;
                nbDigits = 5;
                nbTrials = 10;
                break;
            case "Difficile":
                nbColors = 10;
                nbDigits = 6;
                nbTrials = 10;
                break;
            default:
                nbColors = 0;
                break;
        }
    }

}
