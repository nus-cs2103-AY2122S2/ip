import java.io.*;

/**
 * This is an FileAction class that allows the saving of
 * contents into a pre-specified file
 *
 * @author  Hsiao Jiet
 * @version 1.0
 * @since   2022-1-25
 */

public class FileAction {
    protected String filepath = "C:\\NUS\\CS2103\\iP\\data\\duke.txt";
    protected FileWriter file;
    protected PrintWriter writer;

    public FileAction() throws IOException {
        file = new FileWriter(filepath);
        writer = new PrintWriter(file);
    }

    public void save(String cont) {
        writer.write("Hello");
        writer.write(cont);
    }

    public void changeFilePath(String fileName) {
        filepath = fileName;
    }
}
