package com.alain.mastermind;

import com.alain.Game;

public class Challenger extends MastermindGame{

    private int[] generatedCombination;
    private int[] playerCombination;
    private int[] nbBlacksAndWhites;

    private int trialNb;

    //---------------------- CONSTRUCTOR ------------------------------

    public Challenger(String levelName) {
        super(levelName);
        trialNb=0;
    }

    //----------------------- METHODS ----------------------------------

    @Override
    public void startGame() {
        this.displayGameTitle("Mastermind +/-", "Challenger", this.getLevelName());
        this.generatedCombination = this.generateCombination(this.getNbDigits(), this.getNbColors());
        this.playTurn();
        this.endGameResult(this.getClass().getName(), this.trialNb, this.combinationToString(this.generatedCombination));
    }

    @Override
    public void playTurn() {
        while (this.trialNb < this.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (this.trialNb+1) + " sur " + this.getNbTrials() + "\n");
            System.out.println("Entrez une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et " + (getNbColors()-1) +".\n");
            this.playerCombination = this.inputCombination(this.getNbDigits(), this.getNbColors());
            this.nbBlacksAndWhites = this.compareInput(this.playerCombination, this.generatedCombination);
            this.displayResult(this.trialNb, this.nbBlacksAndWhites, this.playerCombination);
            this.trialNb++;
        }
    }
}


