package com.alain;

public class Game {
    private String levelName;
    private int levelNumber;
    private int[] digits;
    private int trials;

    public Game(String levelName, int levelNumber) {
        this.levelName = levelName;
        this.levelNumber = levelNumber;
        displayNewGame(levelNumber);
    }

    /**
     * Display start menu
     * @param choiceLevel difficulty level
     */
    public void displayNewGame(int choiceLevel) {
        System.out.println("Recherche +/- : Niveau " + levelName);
        generateCode(levelNumber*4);
    }


    /**
     * Generate randomly the list of digits to guess
     * @param nbDigits, number of digits to generate (choiceLevel * 4)
     * @return
     */
    public void generateCode(int nbDigits){
        this.digits = new int [nbDigits];
        int i = 0;
        while (i < nbDigits){
            this.digits[i] = ((int) Math.floor(Math.random()*10));
            i++;
        }
        for ( int value : this.digits ) {
            System.out.println( value );
        }
    }
}
