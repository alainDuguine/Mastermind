package com.alain.recherche;

import com.alain.Game;

public class Challenger extends RechercheGame {

    int[] generatedCombination;

    public Challenger(String levelName) {
        super(levelName);
    }

    /**
     * Launch Game, call code generation, and ask for input
     */
    @Override
    public void startGame() {
        this.displayGameTitle("Recherche +/-", "Challenger", this.getLevelName());
        generatedCombination = this.generateCombination();
        while (getTrialNb() < RechercheGame.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (getTrialNb()+1) + " sur " + RechercheGame.getNbTrials() + "\n");
            System.out.println("Entrez une combinaison de " + RechercheGame.getNbDigits() + " chiffres.\n");
            this.inputCombination();
            String result = RechercheGame.compareInput(this.getPlayerCombinationArray(), this.getGenerateCombination());
            this.displayResult(result, this.getPlayerCombinationArray());
            setTrialNb(getTrialNb()+1);
        }
        if (this.isWin()){
            System.out.println("\nBravo ! Vous avez gagné en " + (trialNb) +" essais !");
        }else{
            System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " + combinationFormat(combinationToString(this.getGenerateCombination())) + "\n");
        }
        playAgain();
    }

    @Override
    public void playAgain(){
        String replay;
        sc.nextLine();
        System.out.println("\nRejouer ? O/N");
        replay = sc.nextLine();
        replay = replay.toLowerCase();
        if (replay.equals("o") || replay.equals("oui")){
            Game challenger = new Challenger(this.getLevelName());
            challenger.startGame();
        }
    }
}
