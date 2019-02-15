package com.alain;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
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

    //---------------------- CONSTRUCTOR ------------------------------

    public Game(String levelName, int levelNumber) {
        this.levelName = levelName;
        this.levelNumber = levelNumber;
        this.nbDigits = levelNumber * 4;
        this.nbTrials = getNbTrials();
        this.startGame();
        this.trialNb = 0;
    }

    //----------------------- METHODS ----------------------------------

    /**
     * Launch Game, call code generation, and ask for input
     */
    public void startGame() {
        System.out.println("\nRecherche +/- : Niveau " + levelName + "\n");
        this.generateCode();
        this.trialNb = 1;
        while (trialNb < nbTrials && this.win == false) {
            System.out.println("Essai n° " + trialNb + " sur " + nbTrials + "\n");
            System.out.println("Entrez une combinaison de " + nbDigits + " chiffres.\n");
            this.playerInput();
            this.splitInput();
            this.compareInput();
            trialNb++;
        }
        if (win){
            System.out.println("\nBravo ! Vous avez gagné en " + (this.trialNb-1) +" essais !");
        }else{
            System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " +this.combination);
        }
        System.out.println("Appuyez sur la touche entrée pour revenir au menu principal");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

     /**
     * Generate randomly the list of digits to guess
     */
    public void generateCode(){
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
    private void compareInput() {
        int i = 0;
        String resultTrial = "";
        String resultGood ="";

        for (int value : combination) {
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
        for ( i =0; i<this.nbDigits; i++){
            resultGood += "=";
        }

        if (resultTrial.equals(resultGood))
            this.win = true;

        System.out.println("Essai n°"+ trialNb+" : " + playerInput + "\nRéponse :   " + resultTrial +"\n");
    }

    //---------------- GETTERS --------------------

    private int getNbTrials() {
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
}
