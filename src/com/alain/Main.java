package com.alain;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.displayMenu();
        while (menu.getLevelNumber() != 0){
            //Start a new game with level indications
            Game game = new Game(menu.getLevelName(), menu.getLevelNumber());
            menu.displayMenu();
        }
        System.exit(0);

    }
}
