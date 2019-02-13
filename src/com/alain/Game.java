package com.alain;

public class Game {
    private String levelName;
    private int levelNumber;
    private int nbDigits;
    private int[] digits;
    private int trials;

    public Game(String levelName, int levelNumber) {
        this.levelName = levelName;
        this.levelNumber = levelNumber;
        this.nbDigits = levelNumber * 4;
        this.trials = getNbTrials();
        displayNewGame(levelNumber);
    }

    /**
     * Display start menu
     * @param choiceLevel difficulty level
     */
    public void displayNewGame(int choiceLevel) {
        System.out.println("Recherche +/- : Niveau " + levelName);
        generateCode(this.nbDigits);
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

    public void playerTrial(){
        System.out.println("Saisissez une combinaison de " + this.nbDigits + " chiffres :\n");
    }

    private int getNbTrials() {
        int nbTrials;
        switch (this.levelNumber) {
            case 1:
                nbTrials = 8;
                break;
            case 2:
                nbTrials = 12;
                break;
            case 3:
                nbTrials = 16;
                break;
            default:
                nbTrials = 0;
                break;
        }
        return nbTrials;
    }
}
