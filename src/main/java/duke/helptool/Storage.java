package duke.helptool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;


/**
 * The type Storage.
 */
public class Storage {
    private final String filePath;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Create file.
     *
     * @throws DukeException the duke exception
     */
    public void create() throws DukeException {
        try {
            File myObj = new File(this.filePath);
            boolean isCreated = myObj.createNewFile();
        } catch (IOException e) {
            throw new DukeException("IO exception occurred.");
        }
    }

    /**
     * Write to file.
     *
     * @param taskList the task list
     * @throws DukeException the duke exception
     */
    public void write(TaskList taskList) throws DukeException {
        try {
            FileWriter myWriter = new FileWriter(filePath, false);

            for (int i = 0; i < taskList.getSize(); i++) {
                String output = String.format("%s\n", taskList.getTask(i).toString());
                myWriter.write(output);
            }
            myWriter.close();
        } catch (IOException e) {
            throw new DukeException("IO exception occurred when write to file.");
        }
    }

    /**
     * Load from file.
     *
     * @return the array list
     * @throws DukeException the duke exception
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            File myObj = new File(this.filePath);
            Scanner myReader = new Scanner(myObj);
            ArrayList<Task> tempTaskList = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String tempType = data.substring(1, 2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
                switch (tempType) {
                case "T":
                    Task tempTask = new ToDo(data.substring(7));
                    if (data.charAt(4) == 'X') {
                        tempTask.markAsDone();
                    }
                    tempTaskList.add(tempTask);
                    break;
                case "D":
                    int byPos = data.indexOf("(by:");
                    String by = data.substring(byPos + 5, data.length() - 1);
                    String description = data.substring(7, byPos - 1);
                    tempTask = new Deadline(description, LocalDateTime.parse(by, formatter));
                    if (data.charAt(4) == 'X') {
                        tempTask.markAsDone();
                    }
                    tempTaskList.add(tempTask);
                    break;
                case "E":
                    int atPos = data.indexOf("(at:");
                    String at = data.substring(atPos + 5, data.length() - 1);
                    description = data.substring(7, atPos - 1);
                    tempTask = new Event(description, LocalDateTime.parse(at, formatter));
                    if (data.charAt(4) == 'X') {
                        tempTask.markAsDone();
                    }
                    tempTaskList.add(tempTask);
                    break;
                default:
                    break;
                }
            }
            myReader.close();
            return tempTaskList;
        } catch (FileNotFoundException e) {
            this.create();
            return null;
        }
    }
}
