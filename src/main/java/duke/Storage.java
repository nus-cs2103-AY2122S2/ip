package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * Represents a storage of data in Duke.
 */
public class Storage {
    private final String path;

    /**
     * Class constructor.
     *
     * @param path path of the file to store data.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Loads data from the storage file to the taskList of Duke.
     *
     * @param taskList taskList of the running Duke.
     */
    public void loadTo(TaskList taskList) {
        try {
            Scanner sc = fileReader();
            while (sc.hasNext()) {
                readOneTask(sc, taskList);
            }
        } catch (DukeException e) {
            taskList.clear();
        }
    }

    private Scanner fileReader() {
        try {
            File file = new File(path);
            file.createNewFile();
            Scanner sc = new Scanner(file);
            sc.useDelimiter(" \\| ");
            return sc;
        } catch (IOException e) {
            return new Scanner("");
        }
    }

    private void readOneTask(Scanner sc, TaskList taskList) {
        String taskType = sc.next();
        String isDoneSymbol = sc.next();
        String content = sc.next();
        boolean isDone = symbolToBoolean(isDoneSymbol);
        switch (taskType) {
        case "T":
            taskList.readFromFile(new Todo(content, isDone));
            break;
        case "D":
            taskList.readFromFile(new Deadline(content, LocalDate.parse(sc.next()), isDone));
            break;
        case "E":
            taskList.readFromFile(new Event(content, sc.next(), isDone));
            break;
        default:
            throw new DukeException("I cannot read the file. It is not in the expected format.");
        }
    }

    private boolean symbolToBoolean(String symbol) {
        switch (symbol) {
        case "0":
            return false;
        case "1":
            return true;
        default:
            throw new DukeException("I cannot read the file. It is not in the expected format.");
        }
    }

    /**
     * Writes data from the taskList of Duke to the storage file.
     * @param taskList taskList of the running Duke.
     */
    public String writeFrom(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(path, false);
            fw.write(taskList.toString());
            fw.close();
            return "Changes are saved. Hope to see you again soon!";
        } catch (IOException e) {
            return "I cannot save changes to file. "
                    + "You could save this text file manually and reboot Duke: \n"
                    + taskList;
        }
    }
}
