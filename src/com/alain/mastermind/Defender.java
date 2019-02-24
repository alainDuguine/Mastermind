package com.alain.mastermind;

import com.alain.Game;

import java.io.IOException;

public class Defender extends MastermindGame{

    private int[] generatedCombination;
    private int[] playerCombination;
    private int[] smartCombination;
    String result="";

    int trialNb;

    public Defender(String levelName) {
        super(levelName);
        this.trialNb = 0;
    }

    @Override
    public void startGame() {
        this.displayGameTitle("Mastermind", "Défenseur", this.getLevelName());
        System.out.println("Entrez une combinaison de " + MastermindGame.getNbDigits() + " chiffres, compris entre 1 et " + getNbColors() +", que devra deviner l'ordinateur.\n");
        playerCombination = this.inputCombination();
        while (trialNb < MastermindGame.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (trialNb+1) + " sur " + MastermindGame.getNbTrials() + "\n");
            //if (this.trialNb == 0 ) {
                generatedCombination = generateCombination();
            //}else{
              //  smartCombination = generateCombinationAfterResult(result, generateCombination);
              //  System.arraycopy(smartCombination, 0, generateCombination,0, smartCombination.length);
            //}
            String result = this.compareInput(generatedCombination, playerCombination);
            this.displayResult(trialNb, result, generatedCombination);
            trialNb++;
            System.out.println("Appuyez sur la touche entrée pour continuer");
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (this.isWin()){
            System.out.println("Désolé ! Vous avez perdu, l'ordinateur a trouvé la combinaison  en " + (trialNb) +" essais !");
        }else{
            System.out.println("Bravo, vous avez gagné ! L'ordinateur n'a pas trouvé votre combinaison secrète, qui était : " + (combinationToString(playerCombination)) + "\n");
        }

        playAgain();
    }

    @Override
    public void playAgain() {
        String replay;
        sc.nextLine();
        System.out.println("\nRejouer ? O/N");
        replay = sc.nextLine();
        replay = replay.toLowerCase();
        if (replay.equals("o") || replay.equals("oui")){
            Game defender = new Defender(this.getLevelName());
            defender.startGame();
        }

    }
}
