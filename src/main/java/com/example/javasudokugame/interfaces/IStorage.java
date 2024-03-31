package com.example.javasudokugame.interfaces;

import com.example.javasudokugame.SudokuGame;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStorage {
    void updateGameData(SudokuGame gameData) throws IOException;
    SudokuGame getGameData() throws IOException;
}
