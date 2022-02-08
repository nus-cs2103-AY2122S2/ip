package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;

class FileClass {

    /**
     * Creates a file in the specific path. 
     * If the file does not exist in the path, 
     * a file at the specific file will be created instead.
     *
     * @param filePath filePath location of the file.
     * @throws IOException if the directory is invalid.
     */
    public void createFile(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) { 
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Path Directory is invalid!");
            }
        }
    } 

    /**
     * Writes output in a file in the specific path. 
     * If the file does not exist in the path, 
     * IOException will be thrown instead.
     *
     * @param filePath filePath location of the file.
     * @param textToAdd textToAdd texts that will be written in the file.
     * @throws IOException if the directory is invalid.
     */
    //taken from W3.3c File Access
    public void writeFile(String filePath, String textToAdd) throws IOException { 
        FileWriter fw = new FileWriter(filePath, true); // initialise the filewriter
        fw.write(textToAdd + "\r\n");
        fw.close();
    }

    /**
     * Creates a directory in the specific path. 
     * If the path does not exist, 
     * it will be created instead.
     *
     * @param filePath filePath location of the file.
     * @throws IOException if the path directory is invalid.
     */
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