package com.alain;

import java.io.IOException;

public class Challenger extends RechercheGame{

    public Challenger(String levelName) {
        super(levelName);
        RechercheGame.setNbDigitsAndNbTrials(levelName);
        trialNb = 0;
    }
    /**
     * Launch Game, call code generation, and ask for input
     */
    @Override
    public void startGame() {
        this.displayGameTitle("Recherche +/-", "Challenger", levelName);
        this.generateCombination();
        while (this.trialNb <= RechercheGame.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (this.trialNb+1) + " sur " + RechercheGame.getNbTrials() + "\n");
            System.out.println("Entrez une combinaison de " + RechercheGame.getNbDigits() + " chiffres.\n");
            this.playerCombination();
            this.splitInput();
            String result = RechercheGame.compareInput(this.getPlayerCombinationArray(), this.getGeneratedCombination());
            this.displayResult(result);
            this.trialNb ++;
        }
        if (this.isWin()){
            System.out.println("\nBravo ! Vous avez gagné en " + (trialNb) +" essais !");
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
