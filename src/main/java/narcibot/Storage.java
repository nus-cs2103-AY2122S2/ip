package narcibot;
import java.io.*;

/**
 * Class for handling the reading and writing to the text file for saving and loading purposes.
 */
public class Storage {
    private File file;
    private File path;
    Storage(String fileName, String path) {
        this.path = new File(path);
        file = new File(path + "/" + fileName);
    }

    /**
     * Returns a BufferedReader if the file exists in the path.
     * @return BufferedReader
     * @throws FileNotFoundException
     */
    public BufferedReader load() throws FileNotFoundException {
        return new BufferedReader((new FileReader(file)));
    }

    /**
     * Returns a FileWriter if the file exists in the path, else it creates a new path and file.
     * @return FileWriter
     * @throws IOException
     */
    public FileWriter store() throws IOException {
        if (!path.exists()) {
            path.mkdir();
        }
        return new FileWriter(file);
    }
}
