package com.example.javasudokugame;

import com.example.javasudokugame.model.GameState;
import com.example.javasudokugame.utill.SudokuUtilities;

import java.io.Serializable;

public class SudokuGame implements Serializable {
    private GameState gameState;
    private final int[][] gridState;

    public static final int GRID_BOUNDARY = 9;

    public SudokuGame(GameState gameState, int[][] gridState) {
        this.gameState = gameState;
        this.gridState = gridState;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public int[][] getCopyOfGridState() {
        //Returns a copy of the gridstate and protects the current state
        return SudokuUtilities.copyToNewArray(gridState);
    }
}
