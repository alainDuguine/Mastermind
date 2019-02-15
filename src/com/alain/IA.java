package com.alain;

import java.io.IOException;

public class IA extends Game{


    public IA(String levelName, int levelNumber) {
        super(levelName, levelNumber);
    }

    @Override
    public void startGame() {
        this.setModeName("Défenseur");
        this.displayGameTitle();
        System.out.println("Entrez une combinaison de " + this.getNbDigits() + " chiffres, que devra deviner l'ordinateur\n");
        this.playerCombination();
        this.splitInput();
        this.setTrialNb(1);
        while (this.getTrialNb() <= this.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + this.getTrialNb() + " sur " + this.getNbTrials() + "\n");
            this.generateCombination();
            this.compareInput(this.getGeneratedCombination(), this.getPlayerCombinationArray());
            this.setTrialNb(this.getTrialNb()+1);
            System.out.println("Appuyez sur la touche entrée pour continuer");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (this.isWin()){
            System.out.println("\nBravo ! Vous avez gagné en " + (this.getTrialNb()-1) +" essais !");
        }else{
            System.out.println("\nDésolé, vous avez perdu ! La combinaison secrète était : " + (this.getPlayerCombination()) + "\n");
        }
        System.out.println("Appuyez sur la touche entrée pour revenir au menu principal");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
