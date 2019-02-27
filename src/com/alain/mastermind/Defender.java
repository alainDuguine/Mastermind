package com.alain.mastermind;

import com.alain.Game;

import java.io.IOException;
import java.util.*;

public class Defender extends MastermindGame{

    private int[] generatedCombination;
    private int[] playerCombination;
    private int[] smartCombination;
    private int trialNb;
    private int[] nbBlacksAndWhites;
    LinkedList <int[]> listCombinations;

    public Defender(String levelName) {
        super(levelName);
        trialNb = 0;
        smartCombination = new int[getNbDigits()];
        listCombinations = new LinkedList<>();
        nbBlacksAndWhites = new int[2];
    }

    @Override
    public void startGame() {
        this.displayGameTitle("Mastermind", "Défenseur", this.getLevelName());
        System.out.println("Entrez une combinaison de " + MastermindGame.getNbDigits() + " chiffres, compris entre 0 et " + (getNbColors()-1) +", que devra deviner l'ordinateur.\n");
        playerCombination = this.inputCombination();
        while (trialNb < MastermindGame.getNbTrials() && !this.isWin()) {
            System.out.println("Essai n° " + ((trialNb)+1) + " sur " + MastermindGame.getNbTrials() + "\n");
            if (trialNb == 0) {
                generatedCombination = generateCombination();
            }else{
                smartCombination = generateCombinationAfterResult(generatedCombination, nbBlacksAndWhites);
                //System.arraycopy(smartCombination, 0, generatedCombination,0, smartCombination.length);
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

    private int[] generateCombinationAfterResult(int[] generatedCombination, int[] nbBlacksAndWhites) {
        int nbDelete = 0;
        int temp[];
        int[] testBlacksAndWhites = new int[2];
        if (listCombinations.size() == 0){
            generateAllSolutions(getNbColors(), getNbDigits());
        }
        Iterator<int[]> it = listCombinations.iterator();
        while(it.hasNext()){
            temp = it.next();
            testBlacksAndWhites = compareInput(temp, generatedCombination);
            System.out.println("Arrays.equals(testBlacksAndWhites,nbBlacksAndWhites)" + Arrays.equals(testBlacksAndWhites,nbBlacksAndWhites));
            if (!(Arrays.equals(testBlacksAndWhites,nbBlacksAndWhites))){
                it.remove();
                nbDelete++;
            }
        }
        System.out.println("Taille liste " + listCombinations.size() + ", Nombre de suppressions : " + nbDelete);
        return testBlacksAndWhites;
    }

    private void generateAllSolutions(int nbColors, int nbDigits){
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
