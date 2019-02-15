package com.alain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    Scanner sc = new Scanner(System.in);
    private String levelName;
    private int levelNumber;
    private int nbDigits;
    private int[] combination;
    private int nbTrials;
    private int trialNb;
    private long playerInput = 0;

    public Game(String levelName, int levelNumber) {
        this.levelName = levelName;
        this.levelNumber = levelNumber;
        this.nbDigits = levelNumber * 4;
        this.nbTrials = getNbTrials();
        this.startGame();
        this.trialNb = 0;
    }

    /**
     * Launch Game, call code generation, and ask for input
     */
    public void startGame( ) {
        System.out.println("Recherche +/- : Niveau " + levelName);
        generateCode(this.nbDigits);
        this.trialNb = 1;
        while (trialNb < nbTrials) {
            System.out.println("Essai n° " + trialNb + " sur " + nbTrials + "\n");
            System.out.println("Entrez une combinaison de " + nbDigits + " chiffres.");
            playerInput();
            splitInput();

            trialNb++;
        }
    }

    /**
     * Generate randomly the list of digits to guess
     * @param nbDigits, number of digits to generate (choiceLevel * 4)
     * @return
     */
    public void generateCode(int nbDigits){
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
        for ( int value : this.combination ) {
            System.out.println( value );
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
        int [] splitInput = new int[nbDigits];
        int i =0;
        // We create a divisor buy powering 10 ^ nbDigits - 1
        long divisor = (long)Math.pow(10, (nbDigits)-1);
        long remain = 0;
        while (remain != 0 || i < nbDigits) {
            splitInput[i] = (int) (playerInput / divisor);
            remain = playerInput % divisor;
            playerInput = remain;
            divisor /= 10;
            System.out.println(splitInput[i]);
            i++;
        };
    }


    private int getNbTrials() {
        int nbTrials;
        switch (this.levelNumber) {
            case 1:
                nbTrials = 8;
                break;
            case 2:
                nbTrials = 12;
                break;
            case 3:
                nbTrials = 16;
                break;
            default:
                nbTrials = 0;
                break;
        }
        return nbTrials;
    }
}
