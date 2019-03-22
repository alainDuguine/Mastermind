package com.alain.mastermind;

public class Challenger extends MastermindGame{

    private int[] generatedCombination;
    private int[] nbBlacksAndWhites;
    private int trialNb;

    //---------------------- CONSTRUCTOR ------------------------------

    public Challenger(String levelName){
        super(levelName);
        this.trialNb=0;
    }

    //----------------------- METHODS ----------------------------------

    @Override
    public void startGame() {
        this.displayGameTitle("Mastermind +/-", "Challenger", this.getLevelName());
        this.generatedCombination = this.generateCombination(this.getNbDigits(), this.getNbColors());
        while (this.trialNb < this.getNbTrials() && !this.isWin()) {
            if (isDev())
                System.out.println("Combinaison secrète : " + this.combinationToString(generatedCombination));
            this.playTurn();
            this.trialNb++;
        }
        this.endGameResult(this.getClass().getName(), this.trialNb, this.combinationToString(this.generatedCombination));
    }

    @Override
    public void playTurn() {
        int[] playerCombination;
        System.out.println("Essayez de trouver la combinaison de l'ordinateur, en entrant une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et " + (getNbColors()-1) +".\n");
        playerCombination = this.inputCombination(this.getNbDigits(), this.getNbColors());
        System.out.println("Essai n° " + (this.trialNb+1) + " sur " + this.getNbTrials() + "\n");
        this.nbBlacksAndWhites = this.compareInput(playerCombination, this.generatedCombination);
        this.displayResult(this.nbBlacksAndWhites, playerCombination);
    }

    /**
     * playTurn method used to play in Duel mode
     * @param trialNb current trialNb
     * @param solutionCombination combination to be found
     */
    @Override
    public void playTurn(int trialNb, int[] solutionCombination) {
        int[] playerCombination;
        System.out.println("Essayez de trouver la combinaison de l'ordinateur, en entrant une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et " + (getNbColors()-1) +".\n");
        playerCombination = this.inputCombination(this.getNbDigits(), this.getNbColors());
        System.out.println("Essai n° " + (trialNb+1) + " sur " + this.getNbTrials() + " - Joueur\n");
        this.nbBlacksAndWhites = this.compareInput(playerCombination, solutionCombination);
        this.displayResult(this.nbBlacksAndWhites, playerCombination);
    }
}


