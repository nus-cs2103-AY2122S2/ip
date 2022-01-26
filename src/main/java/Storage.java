import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Storage {
    private String fileDirPath;
    private String fullFilePath;

    private File file;

    // exception messages
    private final static String FILE_CANNOT_CREATE_MSG = "HEY! File or directory cannot be created!";
    private final static String FILE_LOADING_ERROR_MSG = "HEY! File load data cannot be read or may be corrupted! Your prev save may be gone, start anew.";
    private final static String CANNOT_WRITE_TO_FILE_MSG = "Cannot write data to file. What's up with that?";

    Storage(String fileName, String fileDirPath) throws DukeException {
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
    public void loadFromSave(ArrayList<Task> taskList) throws DukeException {
        try {
            // read file
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                StringTokenizer st = new StringTokenizer(scanner.nextLine(), "|");

                String type = st.nextToken();
                Task newTask = null;
                if (type.equals("T")) {
                    newTask = new Todo(Boolean.parseBoolean(st.nextToken()), st.nextToken());
                } else if (type.equals("E")) {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

                    newTask = new Event(Boolean.parseBoolean(st.nextToken()), st.nextToken(),
                            LocalDate.parse(st.nextToken(), dateFormatter),
                            LocalTime.parse(st.nextToken(), timeFormatter));
                } else if (type.equals("D")) {
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

                    newTask = new Deadline(Boolean.parseBoolean(st.nextToken()), st.nextToken(),
                            LocalDate.parse(st.nextToken(), dateFormatter),
                            LocalTime.parse(st.nextToken(), timeFormatter));
                }

                taskList.add(newTask);
            }

            scanner.close();

        } catch (IOException exception) {
            throw new DukeException(FILE_LOADING_ERROR_MSG);
        } catch (DateTimeParseException exception) {
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
