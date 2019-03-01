package com.alain;

public class Main {

    public static void main(String[] args) {

        String[] gameSelectionNames;

        Menu menu = new Menu();
        gameSelectionNames = menu.displayMenus();

        if (gameSelectionNames[0].equals("Recherche +/-") && gameSelectionNames[1].equals("Challenger")) {
            Game game = new com.alain.recherche.Challenger(gameSelectionNames[2]);
            game.startGame();
            while (game.playAgain()) {
                game = new com.alain.recherche.Challenger(gameSelectionNames[2]);
                game.startGame();
            }
        } else if (gameSelectionNames[0].equals("Recherche +/-") && gameSelectionNames[1].equals("Défenseur")) {
            Game game = new com.alain.recherche.Defender(gameSelectionNames[2]);
            game.startGame();
            while (game.playAgain()) {
                game = new com.alain.recherche.Defender(gameSelectionNames[2]);
                game.startGame();
            }
        } else if (gameSelectionNames[0].equals("Recherche +/-") && gameSelectionNames[1].equals("Duel")) {
            System.out.println("Cette partie n'est pas encore implémentée ! Revenez plus tard !\n");
        } else if (gameSelectionNames[0].equals("Mastermind") && gameSelectionNames[1].equals("Challenger")) {
            Game game = new com.alain.mastermind.Challenger(gameSelectionNames[2]);
            game.startGame();
            while (game.playAgain()) {
                game = new com.alain.mastermind.Challenger(gameSelectionNames[2]);
                game.startGame();
            }
        } else if (gameSelectionNames[0].equals("Mastermind") && gameSelectionNames[1].equals("Défenseur")) {
            Game game = new com.alain.mastermind.Defender(gameSelectionNames[2]);
            game.startGame();
            while (game.playAgain()) {
                game = new com.alain.mastermind.Defender(gameSelectionNames[2]);
                game.startGame();
            }
        } else if (gameSelectionNames[0].equals("Mastermind") && gameSelectionNames[1].equals("Duel")) {
            System.out.println("Cette partie n'est pas encore implémentée ! Revenez plus tard !\n");
        }
    }
}
