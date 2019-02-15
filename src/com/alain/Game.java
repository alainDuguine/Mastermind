package com.alain;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class Game {
    private Scanner sc = new Scanner(System.in);
    private String levelName;
    private int levelNumber;
    private int nbDigits;
    private int[] combination;
    private int[] splitInput;
    private int nbTrials;
    private int trialNb;
    private long playerInput = 0;
    private boolean win = false;

    //Creating a pattern to separate input every 4 digits for better readability
    String pattern = "####,####,####";
    DecimalFormat decimalFormat = new DecimalFormat(pattern);

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
     * Generate randomly the list of digits to guess
     */
    public void generateCombination(){
        this.combination = new int [nbDigits];
        int i = 0;
        while (i < nbDigits) {
            this.combination[i] = ((int) Math.floor(Math.random() * 10));
            //We refuse 0 as first digit
            while (this.combination[0] == 0){
                this.combination[i] = ((int) Math.floor(Math.random() * 10));
            }
            i++;
        }
    }

    /**
     * Ask for player input, then control it (length and type).
     */
    public void playerInput(){
        //Controlling
        boolean responseIsGood;
        do {
            try {
                this.playerInput = sc.nextLong();
                responseIsGood = true;
                if (String.valueOf(playerInput).length() != nbDigits)
                    throw new InputMismatchException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Vous devez saisir une suite de " + nbDigits +" entiers, compris entre 0 et 9");
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
    }

    /**
     * split player input with modulo and putting it in an array
     */
    public void splitInput(){
        splitInput = new int[nbDigits];
        long input = playerInput;
        int i = 0;
        // We create a divisor buy powering 10 ^ nbDigits - 1
        long divisor = (long)Math.pow(10, (nbDigits)-1);
        long remain = 0;
        while (remain != 0 || i < nbDigits) {
            splitInput[i] = (int) (input / divisor);
            remain = playerInput % divisor;
            input = remain;
            divisor /= 10;
            i++;
        };
    }

    /**
     * Compare player Input, with generated combination
     * display "+" if the combination digit is higher, "-" if its lower, and "=" if equal.
     */
    public void compareInput() {
        int i = 0;
        String resultTrial = "";
        String resultGood ="";

        for (int value : combination) {
            if (i != 0 && i%4 == 0){
                resultTrial += " ";
            }
            if (value == splitInput[i]) {
                resultTrial += "=";
            } else if (value < splitInput[i]){
                resultTrial += "-";
            }else{
                resultTrial += "+";
            }
            i++;
        }

        //Generating the string of victory to compare to the trial, according to the level
        for ( i = 0; i<this.nbDigits; i++){
            if (i != 0 && i%4 == 0){
                resultGood += " ";
            }
            resultGood += "=";
        }

        if (resultTrial.equals(resultGood))
            this.win = true;

        System.out.println("Essai n°"+ trialNb+" : " + decimalFormat.format(playerInput) + "\nRéponse :   " + resultTrial +"\n");
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

    public int[] getCombination() {
        return combination;
    }
}
