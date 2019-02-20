package com.alain;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class RechercheGame implements Game {
    private Scanner sc = new Scanner(System.in);

    protected String levelName;

    static int nbDigits;
    static int nbTrials;
    static String resultGood = "";

    protected int trialNb;

    private int[] generatedCombination;
    private int[] playerCombinationArray;
    private long playerCombination = 0;
    private boolean win = false;

    //Creating a pattern to separate input every 4 digits for better readability
    //Used in method compareInput()
    private String pattern = "####,####,####";
    private DecimalFormat decimalFormat = new DecimalFormat(pattern);

    //---------------------- CONSTRUCTOR ------------------------------

    public RechercheGame(String levelName) {
        this.levelName = levelName;
    }

    //----------------------- METHODS ----------------------------------

    public abstract void startGame();

    /**
     * Generate randomly a combination in an Array.
     * length of combination is set by var nbDigits
     */
    public void generateCombination() {
        this.generatedCombination = new int[nbDigits];
        int i = 0;
        while (i < nbDigits) {
            this.generatedCombination[i] = ((int) Math.floor(Math.random() * 10));
            //We refuse 0 as first digit
            while (this.generatedCombination[0] == 0) {
                this.generatedCombination[i] = ((int) Math.floor(Math.random() * 10));
            }
            i++;
        }
        generateWinningPattern();
    }

    /**
     * Generating the string that we should find for a winning game according to the number of digits necessary
     */
    private static void generateWinningPattern() {

        for (int i = 0; i < nbDigits; i++) {
            if (i != 0 && i % 4 == 0) {
                resultGood += " ";
            }
            resultGood += "=";
        }
    }

    /**
     * Ask to input a combination, then control it (length and type).
     * Length of combination is set by var nbDigits
     */
    public void playerCombination(){
        //Controlling
        boolean responseIsGood;
        do {
            try {
                this.playerCombination = sc.nextLong();
                responseIsGood = true;
                if (String.valueOf(playerCombination).length() != nbDigits)
                    throw new InputMismatchException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Vous devez saisir une suite de " + nbDigits +" entiers, compris entre 0 et 9");
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
    }

    /**
     * Split the combination manually input,
     * and put it in the Array playerCombinationArray
     * Using modulo method
     */
    public void splitInput(){
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
     * and display "+" if the controlCombination digit is higher, "-" if its lower, and "=" if equal.
     * @param inputCombination combination to test
     * @param controlCombination combination which is searched by the Challenger/IA
     */
    static String compareInput(int[] inputCombination, int[] controlCombination) {
        int i = 0;
        String resultTrial = "";

        for (int value : controlCombination) {
            //We add a " " every 4 digits for better readability
            if (i != 0 && i % 4 == 0) {
                resultTrial += " ";
            }
            if (value == inputCombination[i]) {
                resultTrial += "=";
            } else if (value < inputCombination[i]) {
                resultTrial += "-";
            } else {
                resultTrial += "+";
            }
            i++;
        }
        return resultTrial;
    }


    public void displayResult(String resultTrial){
        if (resultTrial.equals(resultGood))
            this.win = true;

        // Using a format to split long with " " every 4 digits, for better readability.
        // we convert the combinationArray into string first
        System.out.println("Essai n°" + (trialNb+1) + " : " + combinationFormat(combinationToString(playerCombinationArray)) + "\nRéponse :   " + resultTrial + "\n");
    }


    /**
     * Format the combination with the pattern designed earlier (####,####,####)
     * that will add a space every 4 digits
     * @param combination a combination in String
     * @return the formatted combination in String
     */
    public String combinationFormat(String combination){
        long combinationToLong = Long.parseLong(combination);
        return decimalFormat.format(combinationToLong);
    }

    /**
     * Display the GameTitle according to the game mode and the level
     */
    public void displayGameTitle(String gameName, String modeName, String levelName){
        System.out.println("========================================");
        System.out.println(gameName + " : Niveau " + levelName);
        System.out.println("========================================");
        System.out.println("");
        System.out.println("============= Mode " + modeName +" ============\n");
    }

    //---------------- GETTERS & SETTERS--------------------

    static void setNbDigitsAndNbTrials(String levelName){
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
                nbTrials = 7;
                break;
            default:
                nbDigits = 0;
                break;
        }
    }

    boolean isWin() {
        return win;
    }

    public int[] getGeneratedCombination() {
        return generatedCombination;
    }

    public String combinationToString(int [] combination) {
        String combinationString="";
        for (int value : combination) {
            combinationString += value;
        }
        return combinationString;
    }

    public int[] getPlayerCombinationArray() {
        return playerCombinationArray;
    }

    public static int getNbTrials() {
        return nbTrials;
    }

    public static int getNbDigits() {
        return nbDigits;
    }
}
