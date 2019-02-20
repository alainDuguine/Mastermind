package com.alain;

import java.io.IOException;

public class Defender extends RechercheGame{



    public Defender(String levelName) {
        super(levelName);
    }

    @Override
    public void startGame() {
        this.displayGameTitle("Recherche +/-", "Défenseur", levelName);
        System.out.println("Entrez une combinaison de " + RechercheGame.getNbDigits() + " chiffres, que devra deviner l'ordinateur\n");
        this.InputCombination();
        this.longCombinationToArray();
        while (this.trialNb <= RechercheGame.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (this.trialNb+1) + " sur " + RechercheGame.getNbTrials() + "\n");
            this.generateCombination();
            String result = RechercheGame.compareInput(this.getGenerateCombination(), this.getPlayerCombinationArray());
            this.displayResult(result, this.getGenerateCombination());
            this.trialNb ++;
            System.out.println("Appuyez sur la touche entrée pour continuer");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (this.isWin()){
            System.out.println("\nDésolé ! Vous avez perdu, l'ordinateur a trouvé la combinaison  en " + (this.trialNb) +" essais !");
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
