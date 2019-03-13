package com.alain.mastermind;

import com.alain.Game;
import com.alain.Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;

abstract class MastermindGame extends Game {

    static final Logger logger = LogManager.getLogger();

    private String levelName;
    private static int nbColors;
    private static int nbDigits;
    private static int nbTrials;
    private static boolean dev;

    //---------------------- CONSTRUCTOR ------------------------------

    MastermindGame(String levelName){
        super();
        this.levelName = levelName;
        getProperties(levelName);
    }

    //----------------------- METHODS ----------------------------------

    /**
     * Pick randomly an object from the list of solutions possible
     * @param listCombinations listCombinations generated earlier
     * @return the combination choosen
     */
    int[] chooseCombinationFromList(LinkedList<int[]> listCombinations){
        Random generator = new Random();
        int randomIndex = generator.nextInt(listCombinations.size());
        return listCombinations.get(randomIndex);
    }

    /**
     * compare two combinations and return blacks and whites pen result
     * @param testedCombination combination tested
     * @param solutionCombination point of comparison
     * @return blacksAndWhites result int[] of blacks and whites pins
     */
    int[] compareInput(int[] testedCombination, int[] solutionCombination) {
        int blackHits = 0;
        int whiteHits = 0;
        int[] blacksAndWhites = new int [2];
        int[] test = testedCombination.clone();
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
    void displayResult(int trialNb, int[] blacksAndWhites, int[] combination){
        if (blacksAndWhites[0] == nbDigits){
            this.setWin(true);
        }
        logger.trace("Combination - " + Arrays.toString(combination) + "\n Result - " + Arrays.toString(blacksAndWhites));
        String result = blacksAndWhites[0] + " bien placé(s) - " + blacksAndWhites[1] + " mal placé(s)";
        System.out.println("Essai : " + combinationToString(combination) + "\nRéponse :   " + result + "\n");
    }

    /**
     * Convert int[] combination to String
     * @param combination to convert
     * @return combinationString combination converted
     */
    String combinationToString(int[] combination) {
        String combinationString="";
        for (int value : combination) {
            combinationString += value;
        }
        return combinationString;
    }

    //---------------- GETTERS & SETTERS--------------------

    int getNbTrials() {
        return nbTrials;
    }

    int getNbDigits() {
        return nbDigits;
    }

    String getLevelName() {
        return levelName;
    }

    int getNbColors() {
        return nbColors;
    }

    private static void getProperties(String levelName){
        Properties p = new Utilities().connectFileProperties();

        nbColors = Integer.parseInt(p.getProperty("mastermind."+levelName.toLowerCase()+".nbColors"));
        nbDigits = Integer.parseInt(p.getProperty("mastermind."+levelName.toLowerCase()+".nbDigits"));
        nbTrials = Integer.parseInt(p.getProperty("mastermind."+levelName.toLowerCase()+".nbTrials"));
        dev = Boolean.parseBoolean(p.getProperty("dev"));
        logger.trace("nbColors : " + nbColors + "nbDigits : " + nbDigits +"\nnbTrials : " + nbTrials);
    }

    static boolean isDev() {
        return dev;
    }
}
