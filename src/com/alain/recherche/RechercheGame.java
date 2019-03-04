package com.alain.recherche;

import com.alain.Game;

public abstract class RechercheGame extends Game{

    private String levelName;
    private static int nbDigits;
    private static int nbTrials;
    private static String resultGood = "";
    private int nbMax =10;

//---------------------- CONSTRUCTOR ------------------------------

    protected RechercheGame(String levelName) {
        super();
        this.levelName = levelName;
        getParameters(levelName);
        generateWinningPattern();
    }

    //----------------------- METHODS ----------------------------------

    /**
     * Generating the string that we should find for a winning game according to the number of digits necessary
     */
    private static void generateWinningPattern() {
        resultGood="";
        for (int i = 0; i < nbDigits; i++) {
            if (i != 0 && i % 4 == 0) {
                resultGood += " ";
            }
            resultGood += "=";
        }
    }

    /**
     * Compare two Arrays digits by digits
     * and display "+" if the generatedCombination digit is higher, "-" if its lower, and "=" if equal.
     * @param testedCombination combination to test
     * @param solution combination which is searched by the Challenger/Defender
     */
     protected String compareInput(int[] testedCombination, int[] solution) {
        int i = 0;
        String resultTrial = "";

        for (int value : solution) {
            //We add a " " every 4 digits for better readability
            if (i != 0 && i % 4 == 0) {
                resultTrial += " ";
            }
            if (value == testedCombination[i]) {
                resultTrial += "=";
            } else if (value < testedCombination[i]) {
                resultTrial += "-";
            } else {
                resultTrial += "+";
            }
            i++;
        }
        return resultTrial;
    }

    /**
     * print out the result of a turn
     * @param resultTrial contains String to print
     * @param combinationTrial contains combination tried
     */
    protected void displayResult(int trialNb, String resultTrial, int[] combinationTrial){
        if (resultTrial.equals(resultGood)) {
            this.setWin(true);
        }
        System.out.println("Essai n°" + (trialNb+1) + " : " + combinationToString(combinationTrial) + "\nRéponse :   " + resultTrial + "\n");
    }

    /**
     * Convert int[] combination to String
     * @param combination to convert
     * @return combinationString combination converted
     */
    protected String combinationToString(int[] combination) {
        int i =0;
        String combinationString="";
        for (int value : combination) {
            //We add a " " every 4 digits for better readability
            if (i != 0 && i % 4 == 0) {
                combinationString += " ";
            }
            combinationString += value;
            i++;
        }
        return combinationString;
    }


    //---------------- GETTERS & SETTERS--------------------

    private void getParameters(String levelName){
        switch (levelName) {
            case "Facile":
                nbDigits = 4;
                nbTrials = 5;
                break;
            case "Normal":
                nbDigits = 8;
                nbTrials = 6;
                break;
            case "Difficile":
                nbDigits = 12;
                nbTrials = 8;
                break;
            default:
                nbDigits = 0;
                break;
        }
    }

    protected int getNbTrials() {
        return nbTrials;
    }

    protected  int getNbDigits() {
        return nbDigits;
    }

    protected String getLevelName() {
        return levelName;
    }

    public int getNbMax() {
        return nbMax;
    }

}
