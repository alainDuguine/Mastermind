package com.alain.mastermind;

import com.alain.Game;

public class Duel extends MastermindGame {

        private int[] solutionComputerCombination;
        private int[] solutionPlayerCombination;
        private int trialNbDuel;
        private Game gamePlayer;
        private Game gameComputer;


        //---------------------- CONSTRUCTOR ------------------------------

        public Duel(String levelName){
            super(levelName);
            this.gamePlayer = new Challenger(this.getLevelName());
            this.gameComputer = new Defender(this.getLevelName());
            this.trialNbDuel = 0;
        }

        //----------------------- METHODS ----------------------------------

        @Override
        public void startGame() {
            this.displayGameTitle("Mastermind", "Duel", this.getLevelName());
            this.solutionComputerCombination = this.generateCombination(this.getNbDigits(), this.getNbColors());
            System.out.println("Entrez une combinaison de " + this.getNbDigits() + " chiffres, compris entre 0 et " + (getNbColors() - 1) + ", que devra deviner l'ordinateur.\n");
            this.solutionPlayerCombination = this.inputCombination(this.getNbDigits(),this.getNbColors());
            while (this.trialNbDuel < this.getNbTrials() && !this.gamePlayer.isWin() && !this.gameComputer.isWin()) {
                if (isDev())
                    System.out.println("(Combinaison secrète : " + this.combinationToString(solutionComputerCombination) + ")\n");
                this.playTurn(this.trialNbDuel,this.solutionComputerCombination);
                this.trialNbDuel++;
            }
            this.endGameDuel(this.gameComputer.isWin(), this.gamePlayer.isWin(), this.trialNbDuel, this.combinationToString(this.solutionComputerCombination), this.combinationToString(solutionPlayerCombination));
        }

        @Override
        public void playTurn(int trialNb, int[] solutionCombination) {
            gamePlayer.playTurn(this.trialNbDuel,this.solutionComputerCombination);
            gameComputer.playTurn(this.trialNbDuel, this.solutionPlayerCombination);
        }


        @Override
        public void playTurn() {
        }

    }


