package duke.ui;

import duke.action.Action;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    private File file;

    /**
     * Constructs a new Storage object with a
     * given file path.
     * @param filePath file path location
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Writes the input text to the file
     * given by its file path.
     * @param filePath the path of the file
     * @param textToAdd the text to add to the file
     * @throws IOException if file path is not found
     */
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    /**
     * Returns the same storage object after verifying
     * the existence of the required directory and file.
     * @return this storage object
     * @throws DukeException if the required directory or file is missing
     */
    public Storage load() throws DukeException {
        Path directoryExists = Paths.get("C:/repos/ip/data");
        //check if directory exists
        if (!Files.exists(directoryExists)) {
            throw new DukeException("Missing C:/repos/ip/data");
        }
        if (!file.exists()) {
            throw new DukeException("Missing C:/repos/ip/data/tasks.txt");
        }
        return this;
    }

    /**
     * Executes the writing of the contents of the
     * taskLst onto the file.
     * @param taskList the taskList containing a list of the
     *                 tasks
     */
    public void save(TaskList taskList) {
        ArrayList<Action> list = taskList.getList();
        if (!list.isEmpty()) {
            StringBuilder s = new StringBuilder();
            for (Action act : list) {
                s.append(act.toString()).append("\n");
            }
            try {
                writeToFile(file.getPath(), s.toString());
            } catch (IOException e) {
                System.out.println("Save error");
            }
        }
    }
}
