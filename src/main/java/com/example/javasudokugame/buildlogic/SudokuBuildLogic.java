package com.example.javasudokugame.buildlogic;

import com.example.javasudokugame.SudokuGame;
import com.example.javasudokugame.controller.ControlLogic;
import com.example.javasudokugame.controller.GameLogic;
import com.example.javasudokugame.data.LocalStorageImpl;
import com.example.javasudokugame.interfaces.IStorage;
import com.example.javasudokugame.interfaces.IUserInterfaceContract;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try{
            initialState = storage.getGameData();
        }catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
