package duke;
import java.io.File;
import java.io.FileNotFoundException;

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
    public void checkDir () throws FileNotFoundException {
        File dukeDirectory = new File(directory);
        if (!dukeDirectory.exists()) {
            throw new FileNotFoundException("Directory is not found! Please create one");
        }
    }

    /**
     * check if file exist
     * @throws FileNotFoundException
     */
    public void checkFile() throws FileNotFoundException {
        File dukeFile = new File (filePath);
        if (!dukeFile.exists()) {
            throw new FileNotFoundException("File is not found in your directory! Please create one!");
        }
    }


}
