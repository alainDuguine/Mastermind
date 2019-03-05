package com.alain.recherche;

import java.io.IOException;

public class Challenger extends RechercheGame{

    private int[] generatedCombination;
    private int[] playerCombination;
    private int trialNb;

    //---------------------- CONSTRUCTOR ------------------------------

    public Challenger(String levelName) throws IOException {
        super(levelName);
        trialNb = 0;
    }

    //----------------------- METHODS ----------------------------------

    @Override
    public void startGame() {
        this.displayGameTitle("Recherche +/-", "Challenger", this.getLevelName());
        this.generatedCombination = this.generateCombination(this.getNbDigits(), this.getNbMax());
        while (this.trialNb < this.getNbTrials() && !this.isWin()) {
            this.playTurn();
            this.trialNb++;
        }
        this.endGameResult(this.getClass().getName(),this.trialNb, this.combinationToString(generatedCombination));
    }

    @Override
    public void playTurn() {
        System.out.println("Essayez de trouver la combinaison de l'ordinateur, en entrant une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et 9.\n");
        this.playerCombination = this.inputCombination(this.getNbDigits(), this.getNbMax());
        System.out.println("Essai n° " + (this.trialNb+1) + " sur " + this.getNbTrials() + "\n");
        String result = this.compareInput(this.playerCombination, this.generatedCombination);
        this.displayResult(result,this.playerCombination);
    }


    /**
     * playTurn method used to play in Duel mode
     * @param trialNb current trialNb
     * @param solutionCombination combination to be found
     */
    @Override
    public void playTurn(int trialNb, int[] solutionCombination) {
        int[] playerCombination;
        System.out.println("Essayez de trouver la combinaison de l'ordinateur, en entrant une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et 9.\n");
        playerCombination = this.inputCombination(this.getNbDigits(), this.getNbMax());
        System.out.println("Essai n° " + (trialNb+1) + " sur " + this.getNbTrials() + " - Joueur\n");
        String result = this.compareInput(playerCombination, solutionCombination);
        this.displayResult(result,playerCombination);
    }

}
