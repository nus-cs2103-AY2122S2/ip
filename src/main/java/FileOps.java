import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class FileOps {

    static String SAVE_PATH = "./data/save.txt";
    static String DATA_FOLDER_PATH = "./data";

    public static void saveFile(String textToAdd) {
        //check if data folder exists
        File dataFolder = new File(DATA_FOLDER_PATH);

        File saveFile = new File(SAVE_PATH);
        if (!dataFolder.exists()) {
            //if data folder does not exists, create folder
            dataFolder.mkdir();
        }
        try {
            FileWriter fw = new FileWriter(saveFile);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static String readFile() {
        Path saveFile = Path.of(SAVE_PATH);
        String returnStr = "";
        try {
            returnStr = Files.readString(saveFile);
        } catch (IOException e) {
            //read nothing
        }

        return returnStr;
    }


}
