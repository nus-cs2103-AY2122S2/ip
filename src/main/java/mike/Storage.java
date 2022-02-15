package mike;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath FilePath of the file that should be stored or retrieved.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the file for use by other methods.
     *
     * @return File object (namely storedList.txt).
     */
    public File loadFile() {
        assert !this.filePath.isEmpty() : "filePath should not be an empty string";
        File file = new File(filePath);
        return file;
    }

    /**
     * Stores the list according to the specified filePath.
     *
     * @param listInStorageFormat A String representation of the entire list of tasks.
     */
    void storeList(String listInStorageFormat) {
        try {
            File file = new File(filePath);
            FileWriter fw = new FileWriter(file);
            fw.write(listInStorageFormat);
            fw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


}
