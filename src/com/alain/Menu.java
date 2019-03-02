package com.alain;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner sc = new Scanner(System.in);
    private String[] selectionNames = {"jeu","mode","niveau"};
    private String[] games = {"Recherche +/-", "Mastermind"};
    private String[] modes = {"Challenger", "Défenseur", "Duel"};
    private String[] levels = {"Facile","Normal", "Difficile"};

    private String[][] selectionArrays = new String[3][];
    private String[] gameSelectionNames = new String[3];

    /**
     * Call for menus, and return an array with the selected elements
     * Menus are called dynamically according to the array selectionArrays,
     * the Menu titles are dynamically changed according to the selection.
     */
    String[] displayMenus() {

        String mainTitle ="==\t LOGICAL GAMES";
        String title;
        int i;
        int gameSelectionIndex;

        selectionArrays[0] = games;
        selectionArrays[1] = modes;
        selectionArrays[2] = levels;

        //Game Selection
        for (i = 0; i < selectionNames.length; i++){
        //for (String[] value : selectionArrays) {
            title = "==\t Choix du " + selectionNames[i] + " :";
            gameSelectionIndex = displayMenu(mainTitle, title, selectionArrays[i]);
            // If user selected Quitter it brings him back one step further
            //And erase the last entry in mainTitle.
            if (gameSelectionIndex == -1){
                i=i-2;
                if (i<-1)
                    System.exit(0);
                mainTitle = mainTitle.replace("\n==\t " + gameSelectionNames[i+1], "");
            }else {
                gameSelectionNames[i] = selectionArrays[i][gameSelectionIndex];
                mainTitle += "\n==\t " + gameSelectionNames[i];
            }
        }
        return gameSelectionNames;
    }

    /**
     * Display menus according to a list of elements,
     * Ask for player input, and control it.
     * @param mainTitle Main Title of the menu
     * @param title Title of the menu
     * @param list List of element to choose from
     * @return the number Selected
     */
    private int displayMenu(String mainTitle, String title, String[] list) {
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
        System.out.println("=========================\n");
        if (mainTitle.length() <= 17) {
            System.out.println("0 - Quitter\n");
        }else {
            System.out.println("0 - Précédent\n");
        }
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
}
