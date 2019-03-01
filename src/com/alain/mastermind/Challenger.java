package com.alain.mastermind;

import com.alain.Game;

public class Challenger extends MastermindGame implements Game{

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
        generatedCombination = this.generateCombination();
        playTurn();
        if (this.isWin()){
            System.out.println("\nBravo ! Vous avez gagné en " + (trialNb) +" essais !");
        }else{
            System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " + combinationToString(generatedCombination) + "\n");
        }
    }

    @Override
    public void playTurn() {
        while (trialNb < this.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (trialNb+1) + " sur " + this.getNbTrials() + "\n");
            System.out.println("Entrez une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et " + (getNbColors()-1) +".\n");
            playerCombination = this.inputCombination();
            nbBlacksAndWhites = this.compareInput(playerCombination, generatedCombination);
            this.displayResult(trialNb, nbBlacksAndWhites, playerCombination);
            trialNb++;
        }
    }
}


