package com.alain;

import com.alain.recherche.Challenger;
import com.alain.recherche.Defender;

public class Main {

    public static void main(String[] args) {

        int[] gameSelectionIndex = new int[3];
        String[] gameSelectionNames = new String[3];
        String choice = "";

        while (gameSelectionNames != null) {
            Menu menu = new Menu();
            gameSelectionIndex = menu.displayMenus();

            gameSelectionNames[0] = menu.getGameName(gameSelectionIndex[0]);
            gameSelectionNames[1] = menu.getModeName(gameSelectionIndex[1]);
            gameSelectionNames[2] = menu.getLevelName(gameSelectionIndex[2]);

            System.out.println(choice + "\n");

            if (gameSelectionNames[0].equals("Recherche +/-") && gameSelectionNames[1].equals("Challenger")) {
                Game game = new Challenger(gameSelectionNames[2]);
                game.startGame();
            } else if (gameSelectionNames[0].equals("Recherche +/-") && gameSelectionNames[1].equals("Défenseur")) {
                Game game = new Defender(gameSelectionNames[2]);
                game.startGame();
            } else if (gameSelectionNames[0].equals("Recherche +/-") && gameSelectionNames[1].equals("Duel")) {
                System.out.println("Cette partie n'est pas encore implémentée ! Revenez plus tard !\n");
            } else if (gameSelectionNames[0].equals("Mastermind") && gameSelectionNames[1].equals("Challenger")) {
                System.out.println("Cette partie n'est pas encore implémentée ! Revenez plus tard !\n");
            } else if (gameSelectionNames[0].equals("Mastermind") && gameSelectionNames[1].equals("Défenseur")) {
                System.out.println("Cette partie n'est pas encore implémentée ! Revenez plus tard !\n");
            } else if (gameSelectionNames[0].equals("Mastermind") && gameSelectionNames[1].equals("Duel")) {
                System.out.println("Cette partie n'est pas encore implémentée ! Revenez plus tard !\n");
            }
        }
    }
}
