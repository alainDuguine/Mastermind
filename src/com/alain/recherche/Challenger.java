package com.alain.recherche;

import com.alain.Game;

public class Challenger extends RechercheGame implements Game{

    private int[] generatedCombination;

    //---------------------- CONSTRUCTOR ------------------------------

    public Challenger(String levelName) {
        super(levelName);
    }

    //----------------------- METHODS ----------------------------------

    /**
     * Launch Game, call code generation, and ask for input
     */
    @Override
    public void startGame() {
        this.displayGameTitle("Recherche +/-", "Challenger", this.getLevelName());
        generatedCombination = this.generateCombination();
        this.playTurn();
        if (this.isWin()){
            System.out.println("\nBravo ! Vous avez gagné en " + (trialNb) +" essais !");
        }else{
            System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " + combinationFormat(combinationToString(this.getGenerateCombination())) + "\n");
        }
    }

    @Override
    public void playTurn() {
        while (getTrialNb() < RechercheGame.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (getTrialNb()+1) + " sur " + RechercheGame.getNbTrials() + "\n");
            System.out.println("Entrez une combinaison de " + RechercheGame.getNbDigits() + " chiffres.\n");
            this.inputCombination();
            String result = RechercheGame.compareInput(this.getPlayerCombinationArray(), this.getGenerateCombination());
            this.displayResult(result, this.getPlayerCombinationArray());
            setTrialNb(getTrialNb()+1);
        }
    }

}
