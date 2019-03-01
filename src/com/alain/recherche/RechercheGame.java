package com.alain.recherche;

import com.alain.Game;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class RechercheGame implements Game{
    private Scanner sc = new Scanner(System.in);

    private String levelName;
    private static int nbDigits;
    private static int nbTrials;
    private static String resultGood = "";

    int trialNb;

    private long playerCombination = 0;
    protected int[] generateCombination;
    private int[] playerCombinationArray;
    private boolean win;

    //Creating a pattern to separate input every 4 digits for better readability
    //Used in method compareInput()
    private String pattern = "####,####,####";
    private DecimalFormat decimalFormat = new DecimalFormat(pattern);

    //---------------------- CONSTRUCTOR ------------------------------

    protected RechercheGame(String levelName) {
        this.levelName = levelName;
        getNbDigitsAndNbTrials(levelName);
        trialNb = 0;
        generateWinningPattern();
        win = false;
    }

    //----------------------- METHODS ----------------------------------

    /**
     * Generate randomly a combination in an Array.
     * length of combination is set by var nbDigits
     * 0 can't be a value
     */
    protected int[] generateCombination() {
        this.generateCombination = new int[nbDigits];
        int i = 0;
        while (i < nbDigits) {
            this.generateCombination[i] = ((int) Math.floor(Math.random() * 10));
            while (this.generateCombination[i] == 0) {
                this.generateCombination[i] = ((int) Math.floor(Math.random() * 10));
            }
            i++;
        }
        return generateCombination;
    }

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
     * Ask to input a combination, then control it (length and type).
     * Length of combination is set by var nbDigits, 0 is not accepted
     */
    protected void inputCombination(){
        //Controlling
        boolean responseIsGood;
        do {
            try {
                playerCombination = sc.nextLong();
                responseIsGood = true;
                //if (String.valueOf(playerCombination).length() != nbDigits || String.valueOf(playerCombination).contains("0"))
                if (!(String.valueOf(playerCombination).matches("^[0-9]{" + nbDigits + "}$")))
                    throw new InputMismatchException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Vous devez saisir une suite de " + nbDigits +" entiers, compris entre 0 et 9");
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
        longCombinationToArray();
    }

    /**
     * Split the combination manually input,
     * and put it in the Array playerCombinationArray
     * Using modulo method
     */
    private void longCombinationToArray(){
        playerCombinationArray = new int[nbDigits];
        long input = playerCombination;
        int i = 0;
        // We create a divisor buy powering 10 ^ nbDigits - 1
        long divisor = (long)Math.pow(10, (nbDigits)-1);
        long remain = 0;
        while (remain != 0 || i < nbDigits) {
            playerCombinationArray[i] = (int) (input / divisor);
            remain = playerCombination % divisor;
            input = remain;
            divisor /= 10;
            i++;
        }
    }

    /**
     * Compare two Arrays digits by digits
     * and display "+" if the generatedCombination digit is higher, "-" if its lower, and "=" if equal.
     * @param testedCombination combination to test
     * @param solution combination which is searched by the Challenger/Defender
     */
    static String compareInput(int[] testedCombination, int[] solution) {
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
    protected void displayResult(String resultTrial, int[] combinationTrial){
        if (resultTrial.equals(resultGood))
            this.win = true;

        // Using a format to split long with " " every 4 digits, for better readability.
        // we convert the combinationArray into string first
        System.out.println("Essai n°" + (trialNb+1) + " : " + combinationFormat(combinationToString(combinationTrial)) + "\nRéponse :   " + resultTrial + "\n");
    }


    /**
     * Format the combination with the pattern designed earlier (####,####,####)
     * that will add a space every 4 digits
     * @param combination a combination in String
     * @return the formatted combination in String
     */
    protected String combinationFormat(String combination){
        long combinationToLong = Long.parseLong(combination);
        return decimalFormat.format(combinationToLong);
    }

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
        if (replay.equals("o") || replay.equals("oui")){
            return true;
        }else{
            return false;
        }
    }

    //---------------- GETTERS & SETTERS--------------------

    private static void getNbDigitsAndNbTrials(String levelName){
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

    boolean isWin() {
        return win;
    }

    protected int[] getGenerateCombination() {
        return generateCombination;
    }

    protected int[] getPlayerCombinationArray() {
        return playerCombinationArray;
    }

    protected static int getNbTrials() {
        return nbTrials;
    }

    protected static int getNbDigits() {
        return nbDigits;
    }

    protected String getLevelName() {
        return levelName;
    }

    protected int getTrialNb() {
        return trialNb;
    }

    protected void setTrialNb(int trialNb) {
        this.trialNb = trialNb;
    }
}
