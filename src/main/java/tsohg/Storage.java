package tsohg;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The class to handle File IO.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructor of the class.
     * @param filePath filePath of the data file.
     * @throws TsohgException If something wrong happens.
     */
    public Storage(String filePath) throws TsohgException {
        try {
            this.filePath = filePath;
            File file = new File(filePath);
            file.createNewFile();
        } catch (IOException e) {
            throw new TsohgException(e.getMessage());
        }
    }

    /**
     * Overwrites the file with a new content.
     * @param content The content of the file.
     * @throws TsohgException If something wrong happens.
     */
    public void write(String content) throws TsohgException {
        try {
            FileWriter writer = new FileWriter(filePath, false);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new TsohgException(e.getMessage());
        }
    }

    /**
     * Read the file content.
     * @return A scanner to read the content of a file.
     * @throws TsohgException If something wrong happens.
     */
    public Scanner read() throws TsohgException {
        try {
            File file = new File(filePath);
            return new Scanner(file);
        } catch (IOException e) {
            throw new TsohgException(e.getMessage());
        }
    }

    /**
     * Clear the content of the file.
     * @throws TsohgException If something wrong happens.
     */
    public void clear() throws TsohgException {
        this.write("");
    }

    /**
     * Appends the content to the file.
     * @param content The content to be appended.
     * @throws TsohgException If something wrong happens.
     */
    public void append(String content) throws TsohgException{
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            throw new TsohgException(e.getMessage());
        }
    }
}
