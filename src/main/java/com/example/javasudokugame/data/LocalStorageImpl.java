package com.example.javasudokugame.data;

import com.example.javasudokugame.SudokuGame;
import com.example.javasudokugame.controller.GameLogic;
import com.example.javasudokugame.interfaces.IStorage;

import java.io.*;

public class LocalStorageImpl implements IStorage {

    private static File GAME_DATA = new File(
            System.getProperty("user.home"),
            "gamedata.txt"
    );

    @Override
    public void updateGameData(SudokuGame gameData) throws IOException {
        try{
            FileOutputStream fileOutputStream = new FileOutputStream(GAME_DATA);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(gameData);
            objectOutputStream.close();
        }catch (IOException e){
            throw new IOException("Unable to access Game Data");
        }
    }

    @Override
    public SudokuGame getGameData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(GAME_DATA);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        try{
            SudokuGame gameState = (SudokuGame) objectInputStream.readObject();
            objectInputStream.close();
            return gameState;
        }catch (ClassNotFoundException e){
            e.printStackTrace();
            throw new IOException("File Not Found");
        }
    }
}
