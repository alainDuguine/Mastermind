package com.alain;

import java.io.IOException;

public class Human extends RechercheGame{

    public Human(String[] gameSelectionNames) {
        gameName = gameSelectionNames[0];
        modeName = gameSelectionNames[1];
        levelName = gameSelectionNames[2];
        setNbDigitsAndNbTrials(levelName);
        trialNb = 0;
    }
    /**
     * Launch Game, call code generation, and ask for input
     */
    @Override
    public void startGame() {
        this.displayGameTitle(gameName, modeName, levelName);
        this.generateCombination();
        this.setTrialNb(1);
        while (this.trialNb <= this.nbTrials && !this.isWin()) {
            System.out.println("Essai n° " + this.trialNb + " sur " + this.nbTrials + "\n");
            System.out.println("Entrez une combinaison de " + this.nbDigits + " chiffres.\n");
            this.playerCombination();
            this.splitInput();
            this.compareInput(this.getPlayerCombinationArray(), this.getGeneratedCombination());
            this.setTrialNb(trialNb+1);
        }
        if (this.isWin()){
            System.out.println("\nBravo ! Vous avez gagné en " + (trialNb-1) +" essais !");
        }else{
            System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " + combinationFormat(combinationToString(this.getGeneratedCombination())) + "\n");
        }
        System.out.println("Appuyez sur la touche entrée pour revenir au menu principal");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
