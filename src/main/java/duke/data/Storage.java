package duke.data;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that is linked to a file on disk,
 * with methods to read and write from the file.
 */
public class Storage {
    /** Path of file to link to */
    private final Path filePath;

    private Storage(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Initialise a Storage instance linked to
     * file provided by filePath. Creates the file
     * if it does not exist.
     *
     * @param filePath Path of file to link.
     * @return Storage instance linked to Path
     * given by filePath.
     */
    public static Storage initStorage(Path filePath) throws IOException {
        if (Files.notExists(filePath)) {
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);
        }
        return new Storage(filePath);
    }

    /**
     * Writes each item in data as a new line to
     * linked filePath.
     *
     * @param data ArrayList of data to write.
     * @throws IOException If the write fails.
     */
    public void writeAll(ArrayList<String> data) throws IOException {
        Files.write(this.filePath, data, Charset.defaultCharset());
    }

    /**
     * Read all lines of linked filePath to
     * an ArrayList.
     *
     * @return ArrayList containing each line as an element.
     * @throws IOException If the read fails.
     */
    public ArrayList<String> readAll() throws IOException {
        List<String> lines = Files.readAllLines(this.filePath, Charset.defaultCharset());
        return new ArrayList<>(lines);
    }
}
