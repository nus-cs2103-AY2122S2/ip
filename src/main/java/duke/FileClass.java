package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents a class which contains methods with regard to
 * creating a file, writing an output to a specific file and
 * creating a directory.
 */
class FileClass {

    /**
     * Creates a file in the specific path.
     * If the file does not exist in the path,
     * a file at the specific file will be created instead.
     *
     * @param filePath Location of the file.
     * @throws IOException If the directory is invalid.
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
     * @param filePath Location of the file.
     * @param textToAdd Texts that will be written in the file.
     * @throws IOException if the directory is invalid.
     */
    //taken from W3.3c File Access
    public void writeFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAdd + "\r\n");
        fw.close();
    }

    /**
     * Creates a directory in the specific path.
     * If the path does not exist,
     * it will be created instead.
     *
     * @param filePath Location of the file.
     * @throws IOException If the path directory is invalid.
     */
    public void createDirectory(String filePath) {
        File f = new File(filePath);
        Path path = Paths.get(filePath);
        if (!f.exists()) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                System.out.println("Path Directory cannot be created!");
            }
        }


    }
}
