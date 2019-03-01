package com.alain.mastermind;

import com.alain.Game;

public class Challenger extends MastermindGame{

    private int[] generatedCombination;
    private int[] playerCombination;
    int[] nbBlacksAndWhites;

    int trialNb;

    public Challenger(String levelName) {
        super(levelName);
        trialNb=0;
    }


    @Override
    public void startGame() {
        this.displayGameTitle("Mastermind +/-", "Challenger", this.getLevelName());
        generatedCombination = this.generateCombination();
        while (trialNb < this.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (trialNb+1) + " sur " + this.getNbTrials() + "\n");
            System.out.println("Entrez une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et " + (getNbColors()-1) +".\n");
            playerCombination = this.inputCombination();
            nbBlacksAndWhites = this.compareInput(playerCombination, generatedCombination);
            this.displayResult(trialNb, nbBlacksAndWhites, playerCombination);
            trialNb++;
        }
        if (this.isWin()){
            System.out.println("\nBravo ! Vous avez gagné en " + (trialNb) +" essais !");
        }else{
            System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " + combinationToString(generatedCombination) + "\n");
        }
        playAgain();
    }

    @Override
    public void playAgain(){
        String replay;
        sc.nextLine();
        System.out.println("Rejouer ? O/N");
        replay = sc.nextLine();
        replay = replay.toLowerCase();
        if (replay.equals("o") || replay.equals("oui")){
            Game challenger = new Challenger(this.getLevelName());
            challenger.startGame();
        }
    }



}


