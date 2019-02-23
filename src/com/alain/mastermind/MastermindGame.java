package com.alain.mastermind;

import com.alain.Game;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class MastermindGame implements Game {

    Scanner sc = new Scanner(System.in);

    private String levelName;
    private static int nbColors;
    private static int nbDigits;
    private static int nbTrials;
    private boolean win;

    //Creating a pattern to separate input every 4 digits for better readability
    //Used in method compareInput()
    private String pattern = "####,####,####";
    private DecimalFormat decimalFormat = new DecimalFormat(pattern);

    //---------------------- CONSTRUCTOR ------------------------------

    public MastermindGame(String levelName) {
        this.levelName = levelName;
        getParameters(levelName);
        win = false;
    }

    //----------------------- METHODS ----------------------------------

    public abstract void startGame();

    public abstract void playAgain();

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

    public int[] generateCombination(){
        int[] generateCombination = new int[nbDigits];
        int i = 0;
        while (i < nbDigits) {
            generateCombination[i] = ((int) Math.floor(Math.random() * 10));
            while (generateCombination[i] == 0 || generateCombination[i] >= nbColors) {
                generateCombination[i] = ((int) Math.floor(Math.random() * 10));
            }
            i++;
        }
        return generateCombination;
    }

    /**
     * Ask to input a combination, then control it (length and type).
     * Length of combination is set by var nbDigits, 0 is not accepted
     */
    public int[] inputCombination(){
        //Controlling
        long playerCombination = 0;
        boolean responseIsGood;
        do {
            try {
                playerCombination = sc.nextLong();
                responseIsGood = true;
                //We use a dynamic regular expression to check the input, setup by the nbColors (range of selection) and  nbDigits (number of digits)
                if (String.valueOf(playerCombination).length() != nbDigits || !(String.valueOf(playerCombination).matches("^[1-" + nbColors + "]{" + nbDigits + "}$")))
                    throw new InputMismatchException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Vous devez saisir une suite de " + nbDigits +" entiers, compris entre 1 et " + this.nbColors + ".\n");
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
        return longCombinationToArray(playerCombination);
    }

    /**
     * Split the combination manually input,
     * and put it in the Array playerCombination
     * Using modulo method
     */
    public int[] longCombinationToArray(long combination){
        int[] playerCombination = new int[nbDigits];
        long input = combination;
        int i = 0;
        // We create a divisor buy powering 10 ^ nbDigits - 1
        long divisor = (long)Math.pow(10, (nbDigits)-1);
        long remain = 0;
        while (remain != 0 || i < nbDigits) {
            playerCombination[i] = (int) (input / divisor);
            remain = combination % divisor;
            input = remain;
            divisor /= 10;
            i++;
        }
        return playerCombination;
    }

    protected String compareInput(int[] testCombination, int[] solutionCombination) {
        int goodPlace = 0;
        int wrongPlace = 0;
        int[] test = testCombination.clone();
        int[] solution = solutionCombination.clone();

        String result = "";
        int i;
        //We first check if the numbers are in good spot, and we change their value for not finding them after
        for (i = 0; i < nbDigits; i++){
            if (test[i] == solution[i]){
                goodPlace++;
                test[i] *= 10;
                solution[i] *= 100;
            }
        }

        for (i = 0; i < nbDigits; i++) {
            for (int j = 0; j < nbDigits; j++) {
                if (test[i] == solution[j]) {
                    wrongPlace++;
                    test[i] *= 10;
                    solution[j] *= 100;
                }
            }
        }

        if (goodPlace == nbDigits){
            this.win = true;
        }
        return result = goodPlace + " bien placé(s) - " + wrongPlace + " mal placé(s)\n";
    }

    public void displayResult(int trialNb, String resultTrial, int[] combinationTrial){

        // Using a format to split long with " " every 4 digits, for better readability.
        // we convert the combinationArray into string first
        System.out.println("Essai n°" + (trialNb+1) + " : " + combinationToString(combinationTrial) + "\nRéponse :   " + resultTrial + "\n");
    }

    public String combinationToString(int [] combination) {
        String combinationString="";
        for (int value : combination) {
            combinationString += value;
        }
        return combinationString;
    }

    //---------------- GETTERS & SETTERS--------------------

    public static int getNbTrials() {
        return nbTrials;
    }

    public static int getNbDigits() {
        return nbDigits;
    }

    public String getLevelName() {
        return levelName;
    }

    public static int getNbColors() {
        return nbColors;
    }

    public boolean isWin() {
        return win;
    }

    private void getParameters(String levelName) {
        switch (levelName) {
            case "Facile":
                nbColors = 4;
                nbDigits = 4;
                nbTrials = 10;
                break;
            case "Normal":
                nbColors = 7;
                nbDigits = 6;
                nbTrials = 10;
                break;
            case "Difficile":
                nbColors = 10;
                nbDigits = 8;
                nbTrials = 10;
                break;
            default:
                nbColors = 0;
                break;
        }
    }
}
