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
        this.runMenu();
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
        while (this.choiceLevel != 0) {
            this.startGame(this.choiceLevel);
            this.displayMenu();
        }
        System.exit(0);
    }

    /**
     * Start game in fonction of difficulty level
     * @param choiceLevel difficulty level
     */
    private void startGame(int choiceLevel) {
        clearScreen();
        System.out.println("Recherche +/- : Niveau " + level[choiceLevel-1]);

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
