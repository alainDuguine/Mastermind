package com.alain.recherche;

import com.alain.Game;
import com.alain.Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.Properties;

abstract class RechercheGame extends Game{

    private static final Logger logger = LogManager.getLogger();

    private String levelName;
    private static int nbDigits;
    private static int nbTrials;
    private static String resultGood = "";
    private static boolean dev;

//---------------------- CONSTRUCTOR ------------------------------

    RechercheGame(String levelName){
        super();
        this.levelName = levelName;
        getProperties(levelName);
        generateWinningPattern();
    }

    //----------------------- METHODS ----------------------------------

    /**
     * Generating the string that we should find for a winning game according to the number of digits necessary
     */
    private static void generateWinningPattern() {
        resultGood="";
        for (int i = 0; i < nbDigits; i++) {
            resultGood += "=";
        }
        resultGood = resultGood.replaceAll("(.{4})", "$1 ");
    }

    /**
     * Compare two Arrays digits by digits
     * and display "+" if the generatedCombination digit is higher, "-" if its lower, and "=" if equal.
     * @param testedCombination combination to test
     * @param solution combination which is searched by the Challenger/Defender
     */
    String compareInput(int[] testedCombination, int[] solution) {
        int i = 0;
        String resultTrial = "";

        for (int value : solution) {
            if (value == testedCombination[i]) {
                resultTrial += "=";
            } else if (value < testedCombination[i]) {
                resultTrial += "-";
            } else {
                resultTrial += "+";
            }
            i++;
        }
        logger.trace("Combination - " + Arrays.toString(testedCombination) + "\n Solution - " + Arrays.toString(solution) + "\n Result - " +resultTrial);
        //We add a " " every 4 digits for better readability
        return resultTrial.replaceAll("(.{4})", "$1 ");
    }

    /**
     * print out the result of a turn
     * @param resultTrial contains String to print
     * @param combinationTrial contains combination tried
     */
    void displayResult(String resultTrial, int[] combinationTrial){
        if (resultTrial.equals(resultGood)) {
            this.setWin(true);
        }
        System.out.println("Essai :   " + combinationToString(combinationTrial) + "\nRéponse : " + resultTrial + "\n");
    }

    /**
     * Convert int[] combination to String
     * @param combination to convert
     * @return combinationString combination converted
     */
    String combinationToString(int[] combination) {
        int i =0;
        StringBuilder combinationString= new StringBuilder();
        for (int value : combination) {
            combinationString.append(value);
            i++;
        }
        //We add a " " every 4 digits for better readability
        return combinationString.toString().replaceAll("(.{4})", "$1 ");
    }


    //---------------- GETTERS & SETTERS--------------------


    private static void getProperties(String levelName){
        Properties p = new Utilities().connectFileProperties();


        nbDigits = Integer.parseInt(p.getProperty("recherche."+levelName.toLowerCase()+".nbDigits"));
        nbTrials = Integer.parseInt(p.getProperty("recherche."+levelName.toLowerCase()+".nbTrials"));
        dev = Boolean.parseBoolean(p.getProperty("dev"));
        logger.trace("nbDigits : " + nbDigits +"\nnbTrials : " + nbTrials);
    }

    int getNbTrials() {
        return nbTrials;
    }

    int getNbDigits() {
        return nbDigits;
    }

    String getLevelName() {
        return levelName;
    }

    int getNbMax() {
        return 10;
    }

    static boolean isDev() {
        return dev;
    }
}
