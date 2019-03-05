package com.alain.mastermind;

import com.alain.Game;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Properties;
import java.util.Random;

abstract class MastermindGame extends Game {

    private String levelName;
    private static int nbColors;
    private static int nbDigits;
    private static int nbTrials;

    //---------------------- CONSTRUCTOR ------------------------------

    MastermindGame(String levelName) throws IOException {
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
     * @param testCombination combination tested
     * @param solutionCombination point of comparison
     * @return blacksAndWhites result int[] of blacks and whites pins
     */
    int[] compareInput(int[] testCombination, int[] solutionCombination) {
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
    void displayResult(int trialNb, int[] blacksAndWhites, int[] combination){
        if (blacksAndWhites[0] == nbDigits){
            this.setWin(true);
        }

        String result = blacksAndWhites[0] + " bien placé(s) - " + blacksAndWhites[1] + " mal placé(s)";
        System.out.println("Essai n°" + (trialNb+1) + " : " + combinationToString(combination) + "\nRéponse :   " + result + "\n");
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

    private static void getProperties(String levelName) throws IOException {
        Properties p = new Properties();

        InputStream is = new FileInputStream("D:\\Développement\\Java\\Mastermind\\src\\resources\\config.properties");
        p.load(is);

        System.out.println("m"+levelName+"NbColors");
        System.out.println("m"+levelName+"NbDigits");
        System.out.println("m"+levelName+"NbTrials");
    }

}
