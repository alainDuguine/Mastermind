package com.alain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Scanner sc = new Scanner(System.in);
    private String [] level = {"Facile","Normal", "Difficile"};
    private int choiceLevel = 0;

    /**
     * Display Main Menu
     */
    public void displayMenu() {
        System.out.println("========================================");
        System.out.println("===== Bienvenue dans Recherche +/- =====");
        System.out.println("========================================");
        System.out.println("");
        System.out.println("**********************");
        System.out.println("** Nouvelle Partie ***");
        System.out.println("**********************");
        System.out.println("**\t 1 - "  + level[0] + "\t    **");
        System.out.println("**\t 2 - "  + level[1] + "\t    **");
        System.out.println("**\t 3 - "  + level[2] + "  **");
        System.out.println("**********************");
        System.out.println("");
        System.out.println("0 - Quitter");
        System.out.println("");
        System.out.println("Entrez le numéro de l'action désirée :");
        runMenu();
    }

    /**
     * Get the input, check if it's an int, and execute the game.
     */
    public void runMenu(){
        this.choiceLevel = 0;
        boolean responseIsGood;
        do {
            try {
                this.choiceLevel = sc.nextInt();
                responseIsGood = true;
                if (this.choiceLevel < 0 || this.choiceLevel > level.length)
                    throw new ArrayIndexOutOfBoundsException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Vous devez saisir un nombre entier, compris entre 0 et " + (level.length));
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
        if (this.choiceLevel!=0) {
            displayNewGame(this.choiceLevel);
            this.generateCode(choiceLevel*4);
        }else{
            System.exit(0);
        }
    }

    /**
     * Display start game
     * @param choiceLevel difficulty level
     */
    public void displayNewGame(int choiceLevel) {
        System.out.println("Recherche +/- : Niveau " + level[choiceLevel-1]);
    }

    /**
     * Generate randomly the list of digits to guess
     * @param nbDigits, number of digits to generate (choiceLevel * 4)
     * @return
     */
    public void generateCode(int nbDigits){
        int [] code = new int [nbDigits];
        int i = 0;
        while (i < nbDigits){
            code[i] = ((int) Math.floor(Math.random()*10));
            i++;
        }
        for ( int value : code ) {
            System.out.println( value );
        }
        displayMenu();
    }

}
