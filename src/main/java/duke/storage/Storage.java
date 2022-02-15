package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exception.DukeException;
import duke.exception.ErrorString;
import duke.task.DeadLine;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

/**
 * Storage handles the updating and loading of tasks with the specified file path.
 *
 * @author yl-ang
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage object with the specified filePath.
     *
     * @param filePath FilePath the path to the file containing the tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Stores the updated tasks into the file at filePath.
     *
     * @param taskLst The current list of tasks.
     * @throws DukeException if IOException from accessing the file at filePath.
     */
    public void save(ArrayList<Task> taskLst) throws DukeException {
        File dir = new File("data");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String output = "";
        for (Task task : taskLst) {
            output += task.toStringForStorage() + "\n";
        }

        FileWriter myWriter;
        try {
            myWriter = new FileWriter(this.filePath, false);
            myWriter.write(output);
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException(ErrorString.ERROR_FILE_IO_ERROR.toString());
        }
    }

    /**
     * Loads the tasks from filePath.
     *
     * @return List of tasks loaded from filePath.
     */
    public ArrayList<Task> load() throws DukeException {

        File file = new File (this.filePath);
        ArrayList<Task> taskLst = new ArrayList<>();

        FileReader myReader;
        if (!file.exists()) {
            return taskLst;
        }

        try {
            myReader = new FileReader(this.filePath);
            BufferedReader inStream = new BufferedReader(myReader);

            String inString;
            while ((inString = inStream.readLine()) != null) {
                String[] taskString = inString.split(" \\| ");

                Task currTask;

                switch(taskString[0]) {
                case "T":
                    currTask = new ToDo(taskString[2]);
                    break;
                case "D":
                    currTask = new DeadLine(taskString[2], taskString[3]);
                    break;
                case "E":
                    currTask = new Event(taskString[2], taskString[3]);
                    break;
                default:
                    throw new DukeException(ErrorString.ERROR_STORAGE_INVALID_TASK.toString());
                }

                if (taskString[1].equals("1")) {
                    currTask.markAsDone();
                }
                taskLst.add(currTask);
            }

        } catch (IOException e) {
            throw new DukeException(ErrorString.ERROR_FILE_IO_ERROR.toString());
        }

        return taskLst;
    }
}
