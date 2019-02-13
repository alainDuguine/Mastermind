package com.alain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    Game game = new Game();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    @Test
    public void Given_Easy_When_DisplayMenuLevel_Then_DisplayEasyStart(){
        game.displayNewGame(1);
        assertEquals("Recherche +/- : Niveau Facile\n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_Normal_When_DisplayMenuLevel_Then_DisplayNormalStart(){
        game.displayNewGame(2);
        assertEquals("Recherche +/- : Niveau Normal\n", outContent.toString().replace("\r\n", "\n"));
    }

    @Test
    public void Given_Hard_When_DisplayMenuLevel_Then_DisplayHardStart(){
        game.displayNewGame(3);
        assertEquals("Recherche +/- : Niveau Difficile\n", outContent.toString().replace("\r\n", "\n"));
    }




}