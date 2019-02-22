package com.alain;

import com.alain.recherche.Challenger;
import com.alain.recherche.Defender;


public class Main {

    public static void main(String[] args) {

        String[] gameSelectionNames = new String[3];

        while (gameSelectionNames != null) {
            Menu menu = new Menu();
            gameSelectionNames = menu.displayMenus();

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
