package com.alain.recherche;

import com.alain.Game;

public class Challenger extends RechercheGame implements Game{

    private int[] generatedCombination;
    private int[] playerCombination;
    private int trialNb;


    //---------------------- CONSTRUCTOR ------------------------------

    public Challenger(String levelName) {
        super(levelName);
        trialNb = 0;
    }

    //----------------------- METHODS ----------------------------------

    @Override
    public void startGame() {
        this.displayGameTitle("Recherche +/-", "Challenger", this.getLevelName());
        this.generatedCombination = this.generateCombination();
        this.playTurn();
        this.endGameResult(this.getClass().getName(),this.trialNb, generatedCombination);
    }

    @Override
    public void playTurn() {
        while (this.trialNb < this.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (this.trialNb+1) + " sur " + this.getNbTrials() + "\n");
            System.out.println("Entrez une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et 9.\n");
            this.playerCombination = this.inputCombination();
            String result = this.compareInput(this.playerCombination, this.generatedCombination);
            this.displayResult(this.trialNb, result,this.playerCombination);
            trialNb++;
        }
    }

}
