package io.github.bilcitytycoon.Save;

import com.badlogic.gdx.Gdx;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for managing save file naming and file path generation for BilCity Tycoon.
 * Handles local save directory path and timestamped file name formatting.
 */
public class SaveManager {

    /** Root path for all local save files */
    public static String localSavesRoot = Gdx.files.local("saves").path();

    /**
     * Generates a timestamped file name for a save file.
     *
     * @param name Player's save file base name
     * @return File name string in the format: name-dd_MM_yyyy_HH-mm-ss.data
     */
    public static String generateSaveFileName(String name) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd_MM_yyyy_HH-mm-ss");
        return name + "-" + dateTimeFormatter.format(java.time.LocalDateTime.now()) + ".data";
    }

    /**
     * Returns the full path for a new save file.
     *
     * @param name Player's base save name
     * @return Full file path string
     */
    public static String generateSaveFilePath(String name) {
        return localSavesRoot + "/" + generateSaveFileName(name);
    }
}
