package com.alain.recherche;

import com.alain.Game;
import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class RechercheGame implements Game{
    private Scanner sc = new Scanner(System.in);

    private String levelName;
    private static int nbDigits;
    private static int nbTrials;
    private static String resultGood = "";
    private boolean win;

    //---------------------- CONSTRUCTOR ------------------------------

    protected RechercheGame(String levelName) {
        this.levelName = levelName;
        getParameters(levelName);
        generateWinningPattern();
        win = false;
    }

    //----------------------- METHODS ----------------------------------

    /**
     * Generate randomly a combination in an Array.
     * length of combination is set by var nbDigits
     */
    protected int[] generateCombination() {
        int[] generateCombination = new int[nbDigits];
        int i = 0;
        while (i < nbDigits) {
            generateCombination[i] = ((int) Math.floor(Math.random() * 10));
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
    protected int[] inputCombination(){
        //Controlling
        String playerCombination="";
        boolean responseIsGood;
        do {
            try {
                playerCombination = sc.next();
                responseIsGood = true;
                if (playerCombination.matches("^[0-9]{" + (nbDigits-1) + "}$"))
                    throw new InputMismatchException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Vous devez saisir une suite de " + nbDigits +" entiers, compris entre 0 et 9");
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
        return combinationToArray(playerCombination);
    }

    /**
     * Split the combination manually input,
     * and put it in the Array playerCombinationArray
     * Using modulo method
     */
    private int[] combinationToArray(String combination){
        int[] playerCombination = new int[nbDigits];
        char character;
        String characterToString;

        for (int i=0; i<combination.length();i++) {
            character = combination.charAt(i);
            characterToString = String.valueOf(character);
            playerCombination[i] = Integer.parseInt(characterToString);
        }
        return playerCombination;
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
     * Display text according to ending of the party, win or loss
     * @param trialNb number of trials played.
     * @param combination that was to found
     */
    protected void endGameResult (String className, int trialNb, int[] combination) {
        if (className.contains("Defender")){
            if (this.isWin()) {
                System.out.println("Désolé ! Vous avez perdu, l'ordinateur a trouvé la combinaison  en " + (trialNb) + " essais !");
            } else {
                System.out.println("Bravo, vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison secrète, qui était : " + combinationToString(combination) + "\n");
            }
        }else{
            if (this.isWin()){
                System.out.println("\nBravo ! Vous avez gagné en " + (trialNb) +" essais !");
            }else{
                System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " + combinationToString(combination) + "\n");
            }
        }
    }

    /**
     * print out the result of a turn
     * @param resultTrial contains String to print
     * @param combinationTrial contains combination tried
     */
    protected void displayResult(int trialNb, String resultTrial, int[] combinationTrial){
        if (resultTrial.equals(resultGood))
            this.win = true;

        // Using a format to split long with " " every 4 digits, for better readability.
        // we convert the combinationArray into string first
        System.out.println("Essai n°" + (trialNb+1) + " : " + combinationToString(combinationTrial) + "\nRéponse :   " + resultTrial + "\n");
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
        return replay.equals("o") || replay.equals("oui");
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

    boolean isWin() {
        return win;
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

}
