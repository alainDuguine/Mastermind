package com.alain.recherche;

import java.io.IOException;

public class Defender extends RechercheGame{

    static int[] upperBound;
    static int[] lowerBound;
    private int [] smartCombination;

    public Defender(String levelName) {
        super(levelName);
        upperBound = new int [getNbDigits()];
        lowerBound = new int [getNbDigits()];
        smartCombination = new int[getNbDigits()];
        boundInitializing();
    }

    /**
     * Initialise the boundaries to (low) -1 and (high) 10
     */
    static void boundInitializing() {
        for (int i = 0; i < upperBound.length; i++){
            if (i == 0){
                //We refuse 0 as first digit
                lowerBound[i] = 0;
                upperBound[i] = 10;
            }else {
                upperBound[i] = 10;
                lowerBound[i] = -1;
            }
        }
    }

    @Override
    public void startGame() {
        String result ="";
        this.displayGameTitle("Recherche +/-", "Défenseur", levelName);
        System.out.println("Entrez une combinaison de " + RechercheGame.getNbDigits() + " chiffres, que devra deviner l'ordinateur\n");
        this.InputCombination();
        this.longCombinationToArray();
        while (this.trialNb < RechercheGame.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (this.trialNb+1) + " sur " + RechercheGame.getNbTrials() + "\n");
            if (this.trialNb == 0 ) {
                this.generateCombination();
            }else{
                smartCombination = generateCombinationAfterResult(result, generateCombination);
                System.arraycopy(smartCombination, 0, generateCombination,0, smartCombination.length);
            }
            result = RechercheGame.compareInput(generateCombination, this.getPlayerCombinationArray());
            this.displayResult(result, generateCombination);
            this.trialNb ++;
            System.out.println("Appuyez sur la touche entrée pour continuer");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (this.isWin()){
            System.out.println("Désolé ! Vous avez perdu, l'ordinateur a trouvé la combinaison  en " + (this.trialNb) +" essais !");
        }else{
            System.out.println("Bravo, vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison secrète, qui était : " + (combinationFormat(combinationToString(this.getPlayerCombinationArray()))) + "\n");
        }
        System.out.println("Appuyez sur la touche entrée pour revenir au menu principal");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private int[] generateCombinationAfterResult(String result, int[] generatedCombination) {

        result = result.replaceAll("\\s+","");

        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '=') {
                smartCombination[i] = generatedCombination[i];
            }else if (result.charAt(i) == '+') {
                lowerBound[i] = generatedCombination[i];
                do {
                    smartCombination[i] = ((int) Math.floor(Math.random() * 10));
                } while (smartCombination[i] >= upperBound[i] || smartCombination[i] <= lowerBound[i]);
            }else {
                upperBound[i] = generatedCombination[i];
                do {
                    smartCombination[i] = ((int) Math.floor(Math.random() * 10));
                } while (smartCombination[i] >= upperBound[i] || smartCombination[i] <= lowerBound[i]);
            }

        }
        return smartCombination;
    }
}
