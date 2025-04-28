package io.github.bilcitytycoon.Save;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class SaveManager {
    static String localSavesRoot = Gdx.files.local("saves").path();
    static Connection connection;

        public static Connection connect(String filename) {
            String url = "jdbc:sqlite:"+ localSavesRoot +"/"+ filename;
            try {
                connection = DriverManager.getConnection(url);
                return DriverManager.getConnection(url);
            } catch (SQLException e) {
                System.out.println("Connection failed: " + e.getMessage());
                return null;
            }
        }

        public static String generateSaveFileName(){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH-mm-ss");
            return "zeynel-yıldırım_"+dateTimeFormatter.format(java.time.LocalDateTime.now())+".db";
            //TODO: get university name
        }



}
