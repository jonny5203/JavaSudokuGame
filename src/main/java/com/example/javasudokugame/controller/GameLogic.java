package com.example.javasudokugame.controller;

import com.example.javasudokugame.SudokuGame;
import com.example.javasudokugame.model.GameState;
import com.example.javasudokugame.model.Rows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.example.javasudokugame.SudokuGame.GRID_BOUNDARY;

public class GameLogic {

    //TODO: create an interface that talks to a fasade pattern instead
    public static SudokuGame getNewGame(){
        return new SudokuGame(
                GameState.NEW,
                GameGenerator.getNewGameGrid()
        );
    }

    public static GameState checkForCompletion(int[][] grid){
        if(sudokuIsInvalid(grid)) return GameState.ACTIVE;
        if(tilesAreNotFilled(grid)) return GameState.ACTIVE;
        return GameState.COMPLETE;
    }

    //TODO: very repetitive code, maybe implement a helper function instead
    public static boolean sudokuIsInvalid(int[][] grid){
        if(rowsAreInvalid(grid)) return true;
        if(columnsAreInvalid(grid)) return true;
        if(squareAreInvalid(grid)) return true;
        return false;
    }

    private static boolean rowsAreInvalid(int[][] grid) {
        for(int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
            List<Integer> row = new ArrayList<>();
            for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
                row.add(grid[xIndex][yIndex]);
            }

            if(collectionHasRepeats(row)) return true;
        }

        return false;
    }

    private static boolean columnsAreInvalid(int[][] grid) {
        for(int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++){
            List<Integer> row = new ArrayList<>();
            for(int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++){
                row.add(grid[xIndex][yIndex]);
            }

            if(collectionHasRepeats(row)) return true;
        }

        return false;
    }

    private static boolean squareAreInvalid(int[][] grid) {
        //TODO: Probably use a switch instead
        if(rowOfSquaresIsInvalid(Rows.TOP, grid)) return true;
        if(rowOfSquaresIsInvalid(Rows.MIDDLE, grid)) return true;
        if(rowOfSquaresIsInvalid(Rows.BOTTOM, grid)) return true;

        return false;
    }

    private static boolean rowOfSquaresIsInvalid(Rows rows, int[][] grid) {
        switch (rows){
            case TOP:
                if(squareIsInvalid(0, 0, grid)) return true;
                if(squareIsInvalid(3, 0, grid)) return true;
                if(squareIsInvalid(6, 0, grid)) return true;
                return false;
            case MIDDLE:
                if(squareIsInvalid(0, 3, grid)) return true;
                if(squareIsInvalid(3, 3, grid)) return true;
                if(squareIsInvalid(6, 3, grid)) return true;
                return false;
            case BOTTOM:
                if(squareIsInvalid(0, 6, grid)) return true;
                if(squareIsInvalid(3, 6, grid)) return true;
                if(squareIsInvalid(6, 6, grid)) return true;
                return false;
            default:
                return false;
        }
    }

    private static boolean squareIsInvalid(int xIndex, int yIndex, int[][] grid) {
        int yIndexEnd = yIndex + 3;
        int xIndexEnd = xIndex + 3;

        List<Integer> square = new ArrayList<>();

        while(yIndex < yIndexEnd){
            while (xIndex < xIndexEnd){
                square.add(grid[xIndex][yIndex]);
                xIndex++;
            }

            xIndex -= 3;

            yIndex++;
        }

        if(collectionHasRepeats(square)) return true;
        return false;
    }

    public static boolean collectionHasRepeats(List<Integer> collection) {
        for(int index = 1; index <= GRID_BOUNDARY; index++){
            if(Collections.frequency(collection, index) > 1) return true;
        }

        return false;
    }

    public static boolean tilesAreNotFilled(int[][] grid) {
        for (int xIndex = 0; xIndex < GRID_BOUNDARY; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_BOUNDARY; yIndex++) {
                if (grid[xIndex][yIndex] == 0) return true;
            }
        }
        return false;
    }
}
