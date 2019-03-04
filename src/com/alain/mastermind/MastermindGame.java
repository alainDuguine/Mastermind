package com.alain.mastermind;

import com.alain.Game;

import java.util.LinkedList;
import java.util.Random;

public abstract class MastermindGame extends Game {

    private String levelName;
    private static int nbColors;
    private static int nbDigits;
    private static int nbTrials;

    //---------------------- CONSTRUCTOR ------------------------------

    protected MastermindGame(String levelName) {
        super();
        this.levelName = levelName;
        getParameters(levelName);
    }

    //----------------------- METHODS ----------------------------------

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
    protected String combinationToString(int[] combination) {
        String combinationString="";
        for (int value : combination) {
            combinationString += value;
        }
        return combinationString;
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
