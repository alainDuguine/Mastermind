# Mastermind
-----------------------------------------
**Logical games OpenClassRoom project 3**
-----------------------------------------

Project content two games:

1. Recherche +/-:

  The goal is to find the good number combination.
  The coder choose a secret combination,
  and gives indications to the decoder if each digit of the solution is higher (+) lower (-) or equal (=) to the decoder trial.
  The decoder tries to find the combination choosen by the coder.

2. Mastermind :

  The coder choose a combination of n digits in a range of 0 to k numbers (that represents color).
  He gives too indications to the decoder :
  * Number of digits with the good value in the good spot ,
  * Number of digits with the good value, but in the wrong spot.
  The decoder tries to guess the combination.

Each game can be played in three modes :

  1. Challenger :

  The player is the decoder and plays against the computer who randomly generate a combination to find.
  
  2. Defender :

  The player choose a combination that has to be found by the computer.
  *The mastermind uses the Donald Knuth Algorithm resolution.*
  
  3. Duel:

  Player and computer plays in turns, the first who find the other player's combination wins.
  *NOT IMPLEMENTED YET*
