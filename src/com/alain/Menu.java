package com.alain;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

class Menu {
    private static final Logger logger = LogManager.getLogger();

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
                mainTitle += "\n==\t " + selectionArrays[i][gameSelectionIndex];
                gameSelectionNames[i] = selectionArrays[i][gameSelectionIndex];
            }
        }
        logger.trace("Game selection" + Arrays.toString(gameSelectionNames));
        //Formatting game name to fit with class call
        switch (gameSelectionNames[0]) {
            case "Recherche +/-":
                gameSelectionNames[0] = "recherche";
                break;
            case "Mastermind":
                gameSelectionNames[0] = "mastermind";
                break;
        }
        if (gameSelectionNames[1].equals("Défenseur"))
                gameSelectionNames[1] = "Defender";
        return gameSelectionNames;
    }

    /**
     * Display menus according to a list of elements,
     * Ask for player input, and control it.
     * @param mainTitle resources Title of the menu
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
        return this.playerInput(list.length-1);
    }

    /**
     * Read player input and check that it is good type and in the good range
     * @param nbMax maximum number accepted
     * @return the input choice as an int
     */
    private int playerInput(int nbMax) {
        //take and control input
        int choiceInput = 0;
        boolean responseIsGood;
        do {
            try {
                choiceInput = sc.nextInt()-1;
                responseIsGood = true;
                if (choiceInput < -1 || choiceInput > nbMax)
                    throw new ArrayIndexOutOfBoundsException();
            } catch (ArrayIndexOutOfBoundsException | InputMismatchException e) {
                logger.warn(" Mauvais Input", e);
                System.out.println("Vous devez saisir un nombre entier, compris entre 0 et " + (nbMax+1));
                sc.nextLine();
                responseIsGood = false;
            }
        } while (!responseIsGood);
        return choiceInput;
    }

    /**
     * display menu to choose what to do at the end of a game.
     * @return the input choice as an int
     */
    int replayMenu(){
        System.out.println("\n=========================");
        System.out.println("== Une autre partie ?");
        System.out.println("=========================");
        System.out.println("1 - Rejouer");
        System.out.println("2 - Revenir au menu principal");
        System.out.println("0 - Quitter");
        System.out.println("=========================\n");
        System.out.println("Quel est votre choix :\n");
        return this.playerInput(1);
    }
}
