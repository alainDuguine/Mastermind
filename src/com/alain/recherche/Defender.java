package com.alain.recherche;

import java.io.IOException;

public class Defender extends RechercheGame{

    private int[] playerCombination;
    private int[] generatedCombination;
    private int[] upperBound;
    private int[] lowerBound;
    private int[] smartCombination;
    private String result ="";
    private int trialNb;

    //---------------------- CONSTRUCTOR ------------------------------

    public Defender(String levelName){
        super(levelName);
        trialNb = 0;
        upperBound = new int [getNbDigits()];
        lowerBound = new int [getNbDigits()];
        smartCombination = new int[getNbDigits()];
        boundInitializing();
    }

    //----------------------- METHODS ----------------------------------

    @Override
    public void startGame() {
        this.displayGameTitle("Recherche +/-", "Défenseur", this.getLevelName());
        System.out.println("Entrez une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et 9, que devra deviner l'ordinateur.\n");
        this.playerCombination = this.inputCombination(this.getNbDigits(),10);
        while (this.trialNb < this.getNbTrials() && !this.isWin()) {
            this.playTurn();
            this.trialNb ++;
        }
        this.endGameResult(this.getClass().getName(), this.trialNb, this.combinationToString(playerCombination));
    }

    @Override
    public void playTurn() {
        System.out.println("Essai n° " + (this.trialNb+1) + " sur " + this.getNbTrials() + "\n");
        if (this.trialNb == 0 ) {
            this.generatedCombination = this.generateCombination(this.getNbDigits(), 10);
        }else{
            this.generatedCombination = generateCombinationAfterResult(this.result, this.generatedCombination);
        }
        this.result = this.compareInput(this.generatedCombination, this.playerCombination);
        this.displayResult(this.result, this.generatedCombination);
        System.out.println("Appuyez sur la touche entrée pour continuer");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * playTurn method used to play in Duel mode
     * @param trialNb current trialNb
     * @param solutionCombination combination to be found
     */
    @Override
    public void playTurn(int trialNb, int[]solutionCombination) {
        System.out.println("Essai n° " + (trialNb+1) + " sur " + this.getNbTrials() + " - Ordinateur\n");
        if (trialNb == 0 ) {
            this.generatedCombination = this.generateCombination(this.getNbDigits(), 10);
        }else{
            this.generatedCombination = generateCombinationAfterResult(this.result, this.generatedCombination);
        }
        this.result = this.compareInput(this.generatedCombination, solutionCombination);
        this.displayResult(this.result, generatedCombination);
    }

    /**
     * Initialise the boundaries to (low) -1 and (high) 10
     */
    private void boundInitializing() {
        for (int i = 0; i < this.upperBound.length; i++){
            this.upperBound[i] = 10;
            this.lowerBound[i] = -1;
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
                this.smartCombination[i] = generatedCombination[i];
            }else if (result.charAt(i) == '+') {
                /*if the result tells us that the digit should be higher ('+'),
                we use the digit as a lowerBound for the next random generation.
                 */
                this.lowerBound[i] = generatedCombination[i];
                //Then we generate the number higher than the next one, but lower than the highBound
                do {
                    this.smartCombination[i] = ((int) Math.floor(Math.random() * 10));
                } while (this.smartCombination[i] >= this.upperBound[i] || this.smartCombination[i] <= this.lowerBound[i]);
            }else {
                this.upperBound[i] = generatedCombination[i];
                do {
                    this.smartCombination[i] = ((int) Math.floor(Math.random() * 10));
                } while (this.smartCombination[i] >= this.upperBound[i] || this.smartCombination[i] <= this.lowerBound[i]);
            }

        }
        return smartCombination;
    }
}
