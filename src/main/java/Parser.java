import java.io.File;
import java.io.FileNotFoundException;

public class Parser {

    String directory;
    String filePath;

    Parser (String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;
    }


    public void checkDir () throws FileNotFoundException {
        File dukeDirectory = new File(directory);
        if (!dukeDirectory.exists()) {
            throw new FileNotFoundException("Directory is not found! Please create one");
        }
    }

    public void checkFile() throws FileNotFoundException {
        File dukeFile = new File (filePath);
        if (!dukeFile.exists()) {
            throw new FileNotFoundException("File is not found in your directory! Please create one!");
        }
    }


}
