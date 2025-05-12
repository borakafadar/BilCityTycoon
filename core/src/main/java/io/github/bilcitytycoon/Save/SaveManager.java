package io.github.bilcitytycoon.Save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import io.github.bilcitytycoon.Player;

import java.time.format.DateTimeFormatter;

public class SaveManager {
    public static String localSavesRoot = Gdx.files.local("saves").path();


    public static String generateSaveFileName(String name){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH-mm-ss");
        return name+"-"+dateTimeFormatter.format(java.time.LocalDateTime.now())+".data";
    }
    public static String generateSaveFilePath(String name){
        return localSavesRoot + "/" + generateSaveFileName(name);
    }
}
