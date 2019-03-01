package com.alain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private String[] selectionNames = {"jeu","mode","niveau"};
    private String [] games = {"Recherche +/-", "Mastermind"};
    private String [] modes = {"Challenger", "Défenseur", "Duel"};
    private String [] levels = {"Facile","Normal", "Difficile"};
    private String[][] selectionArray = new String[3][];
    private String[] gameSelectionName = new String[3];

    /**
     * Call for menus, and return an array with the selected elements
     */
    public String[] displayMenus() {

        String mainTitle ="==\t LOGICAL GAMES";
        String title="";

        int i=0;

        selectionArray[0] = games;
        selectionArray[1] = modes;
        selectionArray[2] = levels;

        //Game Selection
        for (String[] value : selectionArray) {
            title = "==\t Choix du " + selectionNames[i] + " :";
            gameSelectionName[i] = displayMenu(mainTitle, title, value);
            mainTitle += "\n==\t " + gameSelectionName[i];
            i++;
        }
        return gameSelectionName;
    }

    /**
     * Display menus according to a list of elements,
     * Ask for player input, and control it.
     * @param mainTitle Main Title of the menu
     * @param title Title of the menu
     * @param list List of element to choose from
     * @return the number Selected
     */
    private String displayMenu(String mainTitle, String title, String[] list) {
        String choiceName ="";
        //Display menu
        int i = 0;
        System.out.println("=========================");
        System.out.println(mainTitle);
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
        if (choiceInput == -1){
            System.exit(0);
        }
        choiceName = list[choiceInput];
        return choiceName;
    }
}
