package com.alain;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {

        String[] gameSelectionNames = null;
        String className;

        Menu menu = new Menu();
        int replay = 1;
        Game game;

        //We will create dynamically an object according to the users answer in the menu.
        // We need a Class, and a Constructor, to instanciate an object with parameters
        Class<?> clazz;
        Constructor<?>[] constructors;
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
                    constructors = clazz.getDeclaredConstructors();
                    for (Constructor<?> constructor1 : constructors) {
                        //We load the constructor in constructor
                        constructor = constructor1;
                    }
                    //Instanciation of the object Game according to the user selection
                    game = (Game) constructor.newInstance(gameSelectionNames[2]);
                    game.startGame();
                    replay = menu.replayMenu();
                    break;
            }
        }while (replay != -1);
        System.exit(0);
    }
}
