package com.alain;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Game {
    private Scanner sc = new Scanner(System.in);
    private String levelName;
    private int levelNumber;
    private int nbDigits;
    private int[] generatedCombination;
    private int[] playerCombinationArray;
    private int nbTrials;
    private int trialNb;
    private long playerCombination = 0;
    private boolean win = false;
    private String modeName ="";

    //Creating a pattern to separate input every 4 digits for better readability
    //Used in method compareInput()
    private String pattern = "####,####,####";
    private DecimalFormat decimalFormat = new DecimalFormat(pattern);

    //---------------------- CONSTRUCTOR ------------------------------

    public Game(String levelName, int levelNumber) {
        this.levelName = levelName;
        this.levelNumber = levelNumber;
        this.nbDigits = levelNumber * 4;
        this.nbTrials = getNbTrials();
        this.trialNb = 0;
    }

    //----------------------- METHODS ----------------------------------

    public abstract void startGame();

     /**
     * Generate randomly a combination in an Array.
     * length of combination is set by var nbDigits
     */
    public void generateCombination(){
        this.generatedCombination = new int [nbDigits];
        int i = 0;
        while (i < nbDigits) {
            this.generatedCombination[i] = ((int) Math.floor(Math.random() * 10));
            //We refuse 0 as first digit
            while (this.generatedCombination[0] == 0){
                this.generatedCombination[i] = ((int) Math.floor(Math.random() * 10));
            }
            i++;
        }
    }

    /**
     * Ask to input a combination, then control it (length and type).
     * Length of combiantion is set by var nbDigits
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
        };
    }

    /**
     * Compare two Arrays digits by digits
     * and display "+" if the controlCombination digit is higher, "-" if its lower, and "=" if equal.
     * @param inputCombination combination to test
     * @param controlCombination combination which is searched by the Human/IA
     */
    public void compareInput(int[] inputCombination, int[] controlCombination) {
        int i = 0;
        String resultTrial = "";
        String resultGood ="";

        for (int value : controlCombination) {
            //We add a " " every 4 digits for better readability
            if (i != 0 && i%4 == 0){
                resultTrial += " ";
            }
            if (value == inputCombination[i]) {
                resultTrial += "=";
            } else if (value < inputCombination[i]){
                resultTrial += "-";
            }else{
                resultTrial += "+";
            }
            i++;
        }

        //Generating the string that we should find for a winning game according to the number of digits necessary
        for ( i = 0; i<this.nbDigits; i++){
            if (i != 0 && i%4 == 0){
                resultGood += " ";
            }
            resultGood += "=";
        }

        if (resultTrial.equals(resultGood))
            this.win = true;

        // Using a format to split long with " " every 4 digits, for better readability.
        // we convert the combinationArray into string then into long
        long combinationToLong = Long.parseLong(combinationToString(inputCombination));
        System.out.println("Essai n°"+ trialNb+" : " + decimalFormat.format(combinationToLong) + "\nRéponse :   " + resultTrial +"\n");
    }

    /**
     * Display the GameTitle according to the game mode and the level
     */
    public void displayGameTitle(){
        System.out.println("========================================");
        System.out.println("Recherche +/- : Niveau " + this.getLevelName());
        System.out.println("========================================");
        System.out.println("");
        System.out.println("============== Mode " + this.modeName +"=============\n");
    }

    //---------------- GETTERS & SETTERS--------------------

    public int getNbTrials() {
        int nbTrials;
        switch (this.levelNumber) {
            case 1:
                nbTrials = 6;
                break;
            case 2:
                nbTrials = 8;
                break;
            case 3:
                nbTrials = 10;
                break;
            default:
                nbTrials = 0;
                break;
        }
        return nbTrials;
    }

    public String getLevelName() {
        return levelName;
    }

    public int getNbDigits() {
        return nbDigits;
    }

    public int getTrialNb() {
        return trialNb;
    }

    public boolean isWin() {
        return win;
    }

    public void setTrialNb(int trialNb) {
        this.trialNb = trialNb;
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

    public void setModeName(String mode) {
        modeName = mode;
    }

    public int[] getPlayerCombinationArray() {
        return playerCombinationArray;
    }

    public long getPlayerCombination() {
        return playerCombination;
    }
}
