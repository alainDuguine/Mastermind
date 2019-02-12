package com.alain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private Scanner sc = new Scanner(System.in);
    private String [] level = {"facile","normale", "difficile"};
    private int choiceLevel = 0;

    /**
     * Display Main Menu
     */
    public void displayMenu() {
        System.out.println("========================================");
        System.out.println("===== Bienvenue dans Recherche +/- =====");
        System.out.println("========================================");
        System.out.println("");
        System.out.println("1 - Commencer une nouvelle partie " + level[0]);
        System.out.println("2 - Commencer une nouvelle partie " + level[1]);
        System.out.println("3 - Commencer une nouvelle partie " + level[2]);
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
                if (this.choiceLevel < 0 || this.choiceLevel >= level.length)
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

    private void startGame(int choice) {
        clearScreen();
        System.out.println("Recherche +/- : Niveau " + level[this.choiceLevel]);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
