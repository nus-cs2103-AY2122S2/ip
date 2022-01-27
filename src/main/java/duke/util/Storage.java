package duke.util;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    // exception messages
    private final static String FILE_CANNOT_CREATE_MSG = "HEY! File or directory cannot be created!";
    private final static String FILE_LOADING_ERROR_MSG
            = "HEY! File load data cannot be read or may be corrupted! Your prev save may be gone, start anew.";
    private final static String CANNOT_WRITE_TO_FILE_MSG = "Cannot write data to file. What's up with that?";
    private final String fileDirPath;
    private final String fullFilePath;
    private File file;

    /**
     * Constructor for Storage.
     *
     * @param fileName Name of data file.
     * @param fileDirPath File directory path of data file.
     * @throws DukeException If there are issues creating or reading the file.
     */
    public Storage(String fileName, String fileDirPath) throws DukeException {
        this.fileDirPath = fileDirPath;
        fullFilePath = fileDirPath + "/" + fileName;

        initFile();
    }

    /**
     * Initializes file and directory.
     *
     * <p>Creates file and directory if they don't exist yet base on the file name and directory path provided.
     * If file and directory already exists, will just open the file.</>
     *
     * @throws DukeException If cannot create new file.
     */
    private void initFile() throws DukeException {
        File directory = new File(fileDirPath);

        // create directory if exist
        if (!directory.exists()) {
            directory.mkdir();
        }

        // check if file exist
        file = new File(fullFilePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                throw new DukeException(FILE_CANNOT_CREATE_MSG);
            }
        }
    }

    /**
     * Load task list from file saved.
     *
     * @param list List to store the new objects created based on the data.
     * @param factory Function<String, T> that takes in an input and returns a new object that extends Loading.
     * @param <T> Object that should extend Loading.
     * @throws DukeException If there are issues reading the file or format of file is wrong.
     */
    public <T extends Loading> void loadFromSave(ArrayList<T> list,
            Function<String, T> factory)
            throws DukeException {
        try {
            // read file
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                T newTask = factory.apply(nextLine); // create the proper obj type

                newTask.extractFileData(nextLine);
                list.add(newTask);
            }

            scanner.close();

        } catch (IOException exception) {
            throw new DukeException(FILE_LOADING_ERROR_MSG);
        }
    }

    /**
     * Save data in list to a file.
     *
     * @param list A list that data needs to be saved. The objects must extend Saving.
     * @throws DukeException If there is problem writing to the file.
     */
    public void saveList(ArrayList<? extends Saving> list) throws DukeException {
        try {
            // write data to file
            FileWriter fileWriter = new FileWriter(file);
            for (Saving item : list) {
                fileWriter.write(item.saveFileFormat());
            }
            fileWriter.close();

        } catch (IOException exception) {
            throw new DukeException(CANNOT_WRITE_TO_FILE_MSG);
        }
    }
}
