package com.alain.recherche;

import com.alain.Game;

import java.io.IOException;

public class Defender extends RechercheGame implements Game{

    private static int[] upperBound;
    private static int[] lowerBound;
    private int[] smartCombination;
    private String result ="";

    //---------------------- CONSTRUCTOR ------------------------------

    public Defender(String levelName) {
        super(levelName);
        upperBound = new int [getNbDigits()];
        lowerBound = new int [getNbDigits()];
        smartCombination = new int[getNbDigits()];
        boundInitializing();
    }

    //----------------------- METHODS ----------------------------------

    /**
     * Initialise the boundaries to (low) -1 and (high) 10
     */
    private static void boundInitializing() {
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
        this.displayGameTitle("Recherche +/-", "Défenseur", this.getLevelName());
        System.out.println("Entrez une combinaison de " + RechercheGame.getNbDigits() + " chiffres, que devra deviner l'ordinateur\n");
        this.inputCombination();
        this.playTurn();
        if (this.isWin()){
            System.out.println("Désolé ! Vous avez perdu, l'ordinateur a trouvé la combinaison  en " + (this.trialNb) +" essais !");
        }else{
            System.out.println("Bravo, vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison secrète, qui était : " + (combinationFormat(combinationToString(this.getPlayerCombinationArray()))) + "\n");
        }

    }

    @Override
    public void playTurn() {
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
    }

    /**
     * generate a "smart" combination according to the result obtained with the last trial.
     * We will define a range in which a number should be picked up randomly.
     * @param result is the result of the last trial
     * @param generatedCombination is the last combination tried
     * @return smartCombination which is the new combination to try
     */
    private int[] generateCombinationAfterResult(String result, int[] generatedCombination) {
        //We remove spaces from the result var
        result = result.replaceAll("\\s+","");

        for (int i = 0; i < result.length(); i++) {
            if (result.charAt(i) == '=') {
                //if the result of this digit is "=" we keep the same digit
                smartCombination[i] = generatedCombination[i];
            }else if (result.charAt(i) == '+') {
                /*if the result tells us that the digit should be higher ('+'),
                we use the digit as a lowerBound for the next random generation.
                 */
                lowerBound[i] = generatedCombination[i];
                //Then we generate the number higher than the next one, but lower than the highBound
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
