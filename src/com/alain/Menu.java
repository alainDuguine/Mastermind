package com.alain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private String [] games = {"Recherche +/-", "Mastermind"};
    private String [] modes = {"Challenger", "Défenseur", "Duel"};
    private String [] levels = {"Facile","Normal", "Difficile"};

    /**
     * Call for menus, and return an array with the selected elements
     */
    public int[] displayMenus() {

        int [] gameSelection = new int [3];

        //Game Selection
        String title = "==\t LOGICAL GAMES\n==\t Choix du jeu :";
        gameSelection[0] = displayMenu(title, games);
        if (gameSelection[0] == -1) {
            //If "0" while game selection -> close app
            System.exit(0);
        }
        //Mode Selection
        title = "==\t " + games[gameSelection[0]] + "\n==\t Choix du mode :";
        gameSelection[1] = displayMenu(title, modes);
        //If "0" while menu selection -> restart menu selection
        if (gameSelection[1] == -1) {
            //Reinitializing array, otherwise at the end of selection, exception is thrown.
            gameSelection = new int[3];
            displayMenus();
        }
        //Level Selection
        title = "==\t " + games[gameSelection[0]] + " - Mode " + modes[gameSelection[1]] + "\n==\t Choix du niveau :";
        gameSelection[2] = displayMenu(title, levels);
        //If "0" while game selection -> restart menu selection
        if (gameSelection[2] == -1) {
            gameSelection = new int[3];
            displayMenus();
        }
        return gameSelection;
    }

    /**
     * Display menus according to a list of elements,
     * Ask for player input, and control it.
     * Add in gameSelection HashMap, the couple selection number, selection Title.
     * @param title Title of the menu
     * @param list List of element to choose from
     * @return the number Selected
     */
    private int displayMenu(String title, String[] list) {

        //Display menu
        int i = 0;
        System.out.println("=========================");
        System.out.println(title);
        System.out.println("=========================");
        for ( String value : list){
            System.out.println("==\t " + (i+1) + " - " + value);
            i++;
        }
        System.out.println("=========================");
        System.out.println("");
        System.out.println("0 - Quitter");
        System.out.println("");
        System.out.println("Quel est votre choix :\n");

        //take and control input
        int choiceInput = 0;
        boolean responseIsGood;
        do {
            try {
                choiceInput = sc.nextInt()-1;
                responseIsGood = true;
                if (choiceInput < -1 || choiceInput > list.length-1)
                    throw new ArrayIndexOutOfBoundsException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                System.out.println("Vous devez saisir un nombre entier, compris entre 0 et " + (list.length));
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
        return choiceInput;
    }

    public String getGameName(int index) {
        return games[index];
    }

    public String getModeName(int index) {
        return modes[index];
    }

    public String getLevelName(int index) {
        return levels[index];
    }
}
