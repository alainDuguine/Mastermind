package com.alain;

import java.io.IOException;

public class Human extends Game{

    public Human(String levelName, int levelNumber) {
        super(levelName, levelNumber);
    }

    /**
     * Launch Game, call code generation, and ask for input
     */
    @Override
    public void startGame() {
        String combinationString ="";

        System.out.println("\nRecherche +/- : Niveau " + this.getLevelName() + "\n");
        this.generateCombination();
        this.setTrialNb(1);
        while (this.getTrialNb() <= this.getNbTrials() && this.isWin() == false) {
            System.out.println("Essai n° " + this.getTrialNb() + " sur " + this.getNbTrials() + "\n");
            System.out.println("Entrez une combinaison de " + this.getNbDigits() + " chiffres.\n");
            this.playerInput();
            this.splitInput();
            this.compareInput();
            this.setTrialNb(this.getTrialNb()+1);
        }
        if (this.isWin()){
            System.out.println("\nBravo ! Vous avez gagné en " + (this.getTrialNb()-1) +" essais !");
        }else{

            for (int value : this.getCombination()) {
                combinationString += value;
            }
            System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " + combinationString);
        }
        System.out.println("Appuyez sur la touche entrée pour revenir au menu principal");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
