package duke.util;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.Function;

public class Storage {
    // exception messages
    private final static String FILE_CANNOT_CREATE_MSG = "HEY! File or directory cannot be created!";
    private final static String FILE_LOADING_ERROR_MSG
            = "HEY! File load data cannot be read or may be corrupted! Your prev save may be gone, start anew.";
    private final static String CANNOT_WRITE_TO_FILE_MSG = "Cannot write data to file. What's up with that?";
    private final String fileDirPath;
    private final String fullFilePath;
    private File file;

    public Storage(String fileName, String fileDirPath) throws DukeException {
        this.fileDirPath = fileDirPath;
        fullFilePath = fileDirPath + "/" + fileName;

        initFile();
    }

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

    /* Load task list from file saved */
    public <T extends Loading> void loadFromSave(ArrayList<T> taskList, Function<String, T> factory)
            throws DukeException {
        try {
            // read file
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                T newTask = factory.apply(nextLine); // create the proper obj type

                newTask.extractFileData(nextLine);
                taskList.add(newTask);
            }

            scanner.close();

        } catch (IOException | DateTimeParseException exception) {
            throw new DukeException(FILE_LOADING_ERROR_MSG);
        }
    }

    /* save a list to a file */
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
