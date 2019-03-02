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

    /**
     * Launch Game, call code generation, and ask for input
     */
    @Override
    public void startGame() {
        this.displayGameTitle("Recherche +/-", "Challenger", this.getLevelName());
        this.generatedCombination = this.generateCombination();
        this.playTurn();
        if (this.isWin()){
            System.out.println("\nBravo ! Vous avez gagné en " + (this.trialNb) +" essais !");
        }else{
            System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " + combinationToString(generatedCombination) + "\n");
        }
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
