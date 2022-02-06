package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

class FileClass {

    public void createFile(String filePath) {
        File f = new File(filePath); //initialise the file
        if (!f.exists()) { //meaning f doesnt exist
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Path Directory is invalid!");
            }
        }
    } 
    //taken from W3.3c File Access
    public void writeFile(String filePath, String textToAdd) throws IOException { 
        FileWriter fw = new FileWriter(filePath, true); // initialise the filewriter
        fw.write(textToAdd + "\r\n");
        fw.close();
    }

    public void createDirectory(String filePath) {
        File f = new File(filePath); //initialise the file
        Path path = Paths.get(filePath);
        if (!f.exists()) { //meaning f doesnt exist
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.out.println("Path Directory cannot be created!");
            }
        }


    }
}