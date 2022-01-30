package luke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Implements the Storage class that reads from and writes to the filepath provided.
 */
public class StorageFile {

    private final File file;

    /**
     * Constructs a Storage class that is connected to the specified file path.
     * Throws IOException if unable to read/write to file path or unable to create new file path.
     *
     * @param filePath The specified file path.
     * @throws IOException If unable to read/write to file path or unable to create new file path.
     */
    public StorageFile(String filePath) throws IOException {
        this.file = new File(filePath);
        if (this.file.getParentFile() != null && !this.file.getParentFile().exists()) {
            this.file.getParentFile().mkdirs();
        }

        if (!this.file.exists()) {
            this.file.createNewFile();
        }

        if (!this.file.canRead() && !this.file.canWrite()) {
            throw new IOException("Unable to read or write to file.");
        }
    }

    /**
     * Saves the data in the storable class to the file.
     *
     * @param storable The data to store into file.
     * @throws IOException If unable to write to file.
     */
    public void save(Storable storable) throws IOException {
        FileWriter fw = new FileWriter(file);
        for (String str : storable.getData()) {
            fw.write(str + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Returns an ArrayList of String which is the data that has been read from the file.
     * Requires more processing to recreate the data as objects.
     *
     * @return An ArrayList of String which is the data that has been read from the file.
     * @throws FileNotFoundException If the file is not found.
     */
    public List<String> load() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
        return list;
    }
}
