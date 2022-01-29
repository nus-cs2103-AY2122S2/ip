package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Storage {

    private final File dataFile;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the path to the locally-stored file
     */
    public Storage(String filePath) {
        dataFile = new File(filePath);

        // dir/file check should not throw unless no r/w permission
        dataFile.getParentFile().mkdirs();
        try {
            dataFile.createNewFile();
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

        // try block should populate dataList
        try (Scanner sc = new Scanner(dataFile)) {
            dataList = sc.useDelimiter("\\n")
                    .tokens()
                    .collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            throw new DukeException("Unable to locate/read data file.");
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
                    e.printStackTrace();
                }
            }
            return true;
        } catch (IOException e) {
            throw new DukeException("Unable to locate/write to data file.");
        }
    }
}