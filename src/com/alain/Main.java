package com.alain;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        int [] gameSelection = new int [3];
        String choice="";

        while (gameSelection != null) {
            Menu menu = new Menu();
            gameSelection = menu.displayMenus();

            choice = "Vous avez choisi de jouer au " + menu.getGameName(gameSelection[0]);
            choice += " en mode " + menu.getModeName(gameSelection[1]);
            choice += " en niveau " + menu.getLevelName(gameSelection[2]);
            System.out.println(choice+"\n");


        }
    }
}
