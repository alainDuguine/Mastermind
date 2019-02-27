package com.alain.mastermind;

import com.alain.Game;

import java.io.IOException;
import java.util.*;

public class Defender extends MastermindGame{

    private int[] generatedCombination;
    private int[] playerCombination;
    private int trialNb;
    private int[] nbBlacksAndWhites;
    LinkedList <int[]> listCombinations;

    public Defender(String levelName) {
        super(levelName);
        trialNb = 0;
        playerCombination = new int[getNbDigits()];
        generatedCombination = new int [getNbDigits()];
        listCombinations = new LinkedList<>();
        nbBlacksAndWhites = new int[2];
    }

    @Override
    public void startGame() {
        this.displayGameTitle("Mastermind", "Défenseur", this.getLevelName());
        System.out.println("Entrez une combinaison de " + MastermindGame.getNbDigits() + " chiffres, compris entre 0 et " + (getNbColors()-1) +", que devra deviner l'ordinateur.\n");
        playerCombination = this.inputCombination();
        generateAllSolutions(getNbColors(), getNbDigits());
        while (trialNb < MastermindGame.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + ((trialNb)+1) + " sur " + MastermindGame.getNbTrials() + "\n");
            if (trialNb == 0) {
                generatedCombination = chooseCombinationFromList(listCombinations);
            }else{
                listCombinations = getListCombinationAfterResult(generatedCombination, nbBlacksAndWhites);
                generatedCombination = chooseCombinationFromList(listCombinations);
            }
            nbBlacksAndWhites = compareInput(generatedCombination,playerCombination);
            this.displayResult(trialNb, nbBlacksAndWhites, generatedCombination);
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

    /**
     * Compare the last combination tried, with the whole list of potentials solution,
     * And remove the one who don't have the same result (because it's a reciprocal operation).
     * @param lastPlayedCombination the last combination played
     * @param nbBlacksAndWhites the result given from the try
     * @return
     */
    private LinkedList<int[]> getListCombinationAfterResult(int[] lastPlayedCombination, int[] nbBlacksAndWhites) {
        int nbDelete = 0;
        int element[];
        int[] testBlacksAndWhites = new int[2];

        //We create an iterator to be able to delete items at the same time as we are reading
        Iterator<int[]> it = listCombinations.iterator();
        while(it.hasNext()){
            element = it.next();
            //We test all combinations in the list with the last played combination.
            //And remove each ones who gave a different result than nbBlacksAndWhites
            testBlacksAndWhites = compareInput(element, lastPlayedCombination);
            if (!(Arrays.equals(testBlacksAndWhites,nbBlacksAndWhites))){
                it.remove();
                nbDelete++;
            }
        }
        System.out.println("Taille liste " + listCombinations.size() + ", Nombre de suppressions : " + nbDelete + "\n");
        return listCombinations;
    }

    /**
     * Will generate all potentials solutions from n x {0,0,0,0...}  where n is nbDigits to {k,k,k,k...} where k is nbColors
     * @param nbColors
     * @param nbDigits
     */
    public void generateAllSolutions(int nbColors, int nbDigits){
        int remain, divisor;
        nbColors = getNbColors();
        nbDigits = getNbDigits();
        int[] comb;
        double totalNbCombinations = Math.pow(nbColors, nbDigits);
        //We generate the list of all possible combinations
        for ( int i = 0 ; i < totalNbCombinations;i++) {
            comb = new int[nbDigits];
            int j = nbDigits;
            int value = i;
            do {
                //Convert decimal list in base nbColors
                divisor = value / nbColors;
                remain = value % nbColors;
                comb[j-1] = remain;
                j--;
                value = divisor;
            }while (divisor != 0);
            listCombinations.add(comb);
        }
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
