package com.alain.mastermind;

import com.alain.Game;

import java.io.IOException;
import java.util.*;

public class Defender extends MastermindGame{

    private int[] generatedCombination;
    private int[] playerCombination;
    LinkedList listCombinations;

    int trialNb;
    int[] nbBlacksAndWhites = new int[2];

    public Defender(String levelName) {
        super(levelName);
        this.trialNb = 0;
        listCombinations = new LinkedList<>();
    }

    @Override
    public void startGame() {
        this.displayGameTitle("Mastermind", "Défenseur", this.getLevelName());
        System.out.println("Entrez une combinaison de " + MastermindGame.getNbDigits() + " chiffres, compris entre 0 et " + (getNbColors()-1) +", que devra deviner l'ordinateur.\n");
        playerCombination = this.inputCombination();
        while (trialNb < MastermindGame.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + (trialNb+1) + " sur " + MastermindGame.getNbTrials() + "\n");
            if (this.trialNb == 0) {
                generatedCombination = generateCombination();
            }else{
                generateCombinationAfterResult(generatedCombination, getBlackHits(), getWhiteHits());
                //System.arraycopy(smartCombination, 0, generatedCombination,0, smartCombination.length);
            }
            nbBlacksAndWhites = this.compareInput(generatedCombination, playerCombination);
            this.displayResult(nbBlacksAndWhites, generatedCombination);
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

    private void generateCombinationAfterResult(int[] generatedCombination, int blackHits, int whiteHits) {
        if (listCombinations.size() == 0){
            generateAllSolutions(getNbColors(), getNbDigits());
        }
        listCombinations.remove(generatedCombination);
        //for (value : listCombinations){
//
        //          }


    }

    private List<int[]> generateAllSolutions(int nbColors, int nbDigits){
        int remain, divisor;
        nbColors = getNbColors();
        nbDigits = getNbDigits();
        int[] comb;
        double totalNbCombinations = Math.pow(nbColors, nbDigits);
        //We generate the list of all possible combinations
        List<int[]> listCombinations = new LinkedList<>();
        for ( int i = 0 ; i < totalNbCombinations;i++) {
            comb = new int[nbDigits];
            int j = nbDigits;
            int value = i;
            do {
                //On convertit en base nbColors
                divisor = value / nbColors;
                remain = value % nbColors;
                comb[j-1] = remain;
                j--;
                value = divisor;
            }while (divisor != 0);
            listCombinations.add(comb);
        }
        return listCombinations;
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
