package duke.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

import duke.exception.DukeException;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    // exception messages
    private static final String FILE_CANNOT_CREATE_MSG = "HEY! File or directory cannot be created!";
    private static final String FILE_LOADING_ERROR_MSG =
            "HEY! File load data cannot be read or may be corrupted! Your prev save may be gone, start anew.";
    private static final String CANNOT_WRITE_TO_FILE_MSG = "Cannot save data to file. What's up with that?";
    private static final String CANNOT_CREATE_OBJ = "Objection creation issue while trying to read data.";

    private final String fileDirPath;
    private final String fullFilePath;
    private File file;

    /**
     * Default constructor for Storage.
     */
    public Storage() {
        fileDirPath = "";
        fullFilePath = "";
    }

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
     * If file and directory already exists, will just open the file.</p>
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
        if (file.exists()) {
            return;
        }

        try {
            file.createNewFile();
        } catch (IOException exception) {
            throw new DukeException(FILE_CANNOT_CREATE_MSG);
        }
    }

    /**
     * Load task list from file saved.
     *
     * @param list List to store the new objects created based on the data.
     * @param factory Lambda that takes in a string and returns a new object that extends Loading.
     * @param <T> Object that should extend Loading.
     * @throws DukeException If there are issues reading the file or format of file is wrong.
     */
    public <T extends Loading> void loadFromSave(ArrayList<T> list, Function<String, T> factory) throws DukeException {
        try {
            // read file
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                T newTask = factory.apply(nextLine); // create the proper obj type

                if (newTask == null) {
                    throw new DukeException(CANNOT_CREATE_OBJ);
                }

                newTask.extractDataFromLine(nextLine);
                list.add(newTask);
            }

            scanner.close();
        } catch (IOException | DukeException exception) {
            list.clear(); //clear list if issue with loading data
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

        } catch (Exception exception) {
            throw new DukeException(CANNOT_WRITE_TO_FILE_MSG);
        }
    }
}
