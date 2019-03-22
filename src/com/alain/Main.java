package com.alain;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


class Main {
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, ClassNotFoundException {

        String[] gameSelectionNames = null;
        String className;
        Menu menu = new Menu();
        int replay = 1;
        Game game;

        //We will create dynamically an object according to the users answer in the menu.
        // We need a Class, and a Constructor, to instantiate an object with parameters
        Class<?> clazz;
        Constructor constructor = null;

        do{
            switch (replay){
                case 0 :
                    game = (Game) constructor.newInstance(gameSelectionNames[2]);
                    game.startGame();
                    replay = menu.replayMenu();
                    break;
                case 1 :
                    menu = new Menu();
                    gameSelectionNames = menu.displayMenus();
                    //Loading the class
                    className = "com.alain." + gameSelectionNames[0] + "." + gameSelectionNames[1];
                    clazz = Class.forName(className);
                    //Getting an array with constructors present in the class
                    constructor = clazz.getDeclaredConstructor(String.class);
                    //Instantiation of the object Game according to the user selection
                    game = (Game) constructor.newInstance(gameSelectionNames[2]);
                    game.startGame();
                    replay = menu.replayMenu();
                    break;
            }
        }while (replay != -1);
        System.exit(0);
    }
}
