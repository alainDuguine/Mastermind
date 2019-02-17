package com.alain;

import java.io.IOException;

public class IA extends RechercheGame{

    public IA(String[] gameSelectionNames) {
        gameName = gameSelectionNames[0];
        modeName = gameSelectionNames[1];
        levelName = gameSelectionNames[2];
        setNbDigitsAndNbTrials(levelName);
        trialNb = 0;
    }

    @Override
    public void startGame() {
        this.displayGameTitle(gameName, modeName, levelName);
        System.out.println("Entrez une combinaison de " + this.nbDigits + " chiffres, que devra deviner l'ordinateur\n");
        this.playerCombination();
        this.splitInput();
        this.setTrialNb(1);
        while (this.trialNb <= this.nbTrials && !this.isWin()) {
            System.out.println("Essai n° " + this.trialNb + " sur " + this.nbTrials + "\n");
            this.generateCombination();
            this.compareInput(this.getGeneratedCombination(), this.getPlayerCombinationArray());
            this.setTrialNb(this.trialNb+1);
            System.out.println("Appuyez sur la touche entrée pour continuer");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (this.isWin()){
            System.out.println("\nDésolé ! Vous avez perdu, l'ordinateur a trouvé la combinaison  en " + (this.trialNb-1) +" essais !");
        }else{
            System.out.println("\nBravo, vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison secrète, qui était : " + (combinationFormat(combinationToString(this.getPlayerCombinationArray()))) + "\n");
        }
        System.out.println("Appuyez sur la touche entrée pour revenir au menu principal");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
