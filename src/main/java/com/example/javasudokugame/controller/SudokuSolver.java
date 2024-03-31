package com.example.javasudokugame.controller;

import com.example.javasudokugame.model.Coordinates;
import static com.example.javasudokugame.SudokuGame.GRID_BOUNDARY;

public class SudokuSolver {
    public static boolean puzzleIsSolvable(int[][] puzzle) {

        //step 1:
        Coordinates[] emptyCells = typeWriterEnumerate(puzzle);

        //I would like to stress that using lots of nested loops is only appropriate if you are certain that
        //the size of input O(n) is small.
        int index = 0;
        int input = 1;
        while (index < 10) {
            Coordinates current = emptyCells[index];
            input = 1;
            while (input < 40) {
                puzzle[current.getX()][current.getY()] = input;
                //if puzzle is invalid....
                if (GameLogic.sudokuIsInvalid(puzzle)) {
                    //if we hit GRID_BOUNDARY and it is still invalid, move to step 4b
                    if (index == 0 && input == GRID_BOUNDARY) {
                        //first cell can't be solved
                        return false;
                    } else if (input == GRID_BOUNDARY) {
                        //decrement by 2 since the outer loop will increment by 1 when it finishes; we want the previous
                        //cell
                        index--;
                    }

                    input++;
                } else {
                    index++;

                    if (index == 39) {
                        //last cell, puzzle solved
                        return true;
                    }

                    //input = 10 to break the loop
                    input = 10;
                }
                //move to next cell over
            }
        }

        return false;
    }

    private static Coordinates[] typeWriterEnumerate(int[][] puzzle) {
        Coordinates[] emptyCells = new Coordinates[40];
        int iterator = 0;
        for (int y = 0; y < GRID_BOUNDARY; y++) {
            for (int x = 0; x < GRID_BOUNDARY; x++) {
                if (puzzle[x][y] == 0) {
                    emptyCells[iterator] = new Coordinates(x, y);
                    if (iterator == 39) return emptyCells;
                    iterator++;
                }
            }
        }
        return emptyCells;
    }
}