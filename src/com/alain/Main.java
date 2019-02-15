package com.alain;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayMenu();
        while (menu.getLevelNumber() != 0){
            //Start a new game with level indications
            IA game = new IA(menu.getLevelName(), menu.getLevelNumber());
            game.startGame();
            menu.displayMenu();
        }
        System.exit(0);
    }
}
