package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * An instance of storage which is used to read/write to the data file of the user.
 */
public class Storage {

    private final File dataFile;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the path to the locally-stored file
     */
    public Storage(String filePath) {
        dataFile = new File(filePath);

        // ensures that the path to the file exists and supports r/w access
        canReadWrite(dataFile.getParentFile());
        dataFile.getParentFile().mkdirs();
        try {
            dataFile.createNewFile();

            // ensures that the file has r/w access
            assert canReadWrite(dataFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads list the {@link #dataFile} and returns a `List` used to populate `TaskList`.
     *
     * @return the content of {@link #dataFile} in a `List`
     * @throws DukeException if {@link #dataFile} could not be located or read
     */
    public List<String> read() throws DukeException {
        List<String> dataList;

        // try block should populate dataList unless no r/w permission
        try (Scanner sc = new Scanner(dataFile)) {
            //TODO: modify this a little to include to A-Stream
            dataList = sc.useDelimiter("\\n")
                    .tokens()
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to locate/read file.");
        }

        // if dataList is empty, dataFile has no entry
        return dataList;
    }

    /**
     * Stores the content of `TaskList` into {@link #dataFile}.
     *
     * @param tasks the `TaskList` containing this instance's `Task`
     * @return true if this method runs successfully
     * @throws DukeException if {@link #dataFile} could not be located or written to
     */
    public boolean write(TaskList tasks) throws DukeException {
        try (FileWriter fw = new FileWriter(dataFile, false)) {
            for (int i = 0; i < tasks.size(); i++) {
                try {
                    fw.write(tasks.get(i).toFile());
                } catch (IOException e) {
                    throw new DukeException("Unable to write to file.");
                }
            }
            return true;
        } catch (IOException e) {
            // most likely r/w permissions
            throw new DukeException("Unable to locate/write to file.");
        }
    }

    private boolean canReadWrite(File f) {
        return f.canRead() && f.canWrite();
    }
}
