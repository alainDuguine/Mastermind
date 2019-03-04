package com.alain.recherche;

import com.alain.Game;

public class Duel extends RechercheGame{

    private int[] solutionComputerCombination;
    private int[] solutionPlayerCombination;
    private int trialNbDuel;
    private Game gamePlayer;
    private Game gameComputer;


    //---------------------- CONSTRUCTOR ------------------------------

    public Duel(String levelName) {
        super(levelName);
        gamePlayer = new Challenger(this.getLevelName());
        gameComputer = new Defender(this.getLevelName());
        trialNbDuel = 0;
    }

    //----------------------- METHODS ----------------------------------

    @Override
    public void startGame() {

        this.displayGameTitle("Recherche +/-", "Duel", this.getLevelName());
        this.solutionComputerCombination = this.generateCombination(this.getNbDigits(), this.getNbMax());
        System.out.println("Entrez une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et 9, que devra deviner l'ordinateur\n");
        this.solutionPlayerCombination = this.inputCombination(this.getNbDigits(),10);
        while (this.trialNbDuel < this.getNbTrials() && !gamePlayer.isWin() && !gameComputer.isWin()) {
            this.playTurn(this.trialNbDuel,this.solutionComputerCombination);
            this.trialNbDuel++;
        }
        this.endGameDuel(gameComputer.isWin(), gamePlayer.isWin(), trialNbDuel, this.combinationToString(solutionComputerCombination), this.combinationToString(solutionPlayerCombination));
    }

    @Override
    public void playTurn() {
    }

    @Override
    public void playTurn(int trialNb, int[] solutionCombination) {
        gamePlayer.playTurn(this.trialNbDuel,this.solutionComputerCombination);
        gameComputer.playTurn(this.trialNbDuel, this.solutionPlayerCombination);
    }

}
