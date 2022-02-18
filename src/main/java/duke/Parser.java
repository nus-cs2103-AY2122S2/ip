package duke;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * deals with making sense of user command
 */
public class Parser {

    private String directory;
    private String filePath;

    /**
     * @param directory
     * @param filePath
     */
    public Parser(String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;
    }

    /**
     * check if directory exist
     * @throws FileNotFoundException
     */
    public void checkDir () {
        File dukeDirectory = new File(directory);
        if (!dukeDirectory.exists()) {
            dukeDirectory.mkdir();
        }
    }

    /**
     * check if file exist
     * @throws FileNotFoundException
     */
    public void checkFile() throws IOException {
        File dukeFile = new File (filePath);
        if (!dukeFile.exists()) {
            dukeFile.createNewFile();
        }
    }


}
