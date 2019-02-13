package com.alain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private String [] level = {"Facile","Normal", "Difficile"};

    private int levelNumber = 0;
    private String levelName;

    /**
     * Display Main Menu and get the levelNumber from input
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
        //Input verification
        boolean responseIsGood;
        do {
            try {
                this.levelNumber = sc.nextInt();
                responseIsGood = true;
                if (this.levelNumber < 0 || this.levelNumber > level.length)
                    throw new ArrayIndexOutOfBoundsException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Vous devez saisir un nombre entier, compris entre 0 et " + (level.length));
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
    }

    public String getLevelName() {
        return this.level[levelNumber-1];
    }

    public int getLevelNumber() {
        return this.levelNumber;
    }

}
