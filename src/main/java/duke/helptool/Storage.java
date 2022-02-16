package duke.helptool;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import duke.tag.Tag;
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
     * Create.
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
     * Write.
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
     * Gets tag.
     *
     * @param data the data
     * @return the tag
     */
    public Tag getTag(String data) {
        int tagPos = data.indexOf("#{");
        int tagEnd = data.indexOf("}");
        String tagDescription = data.substring(tagPos + 2, tagEnd);
        return new Tag(tagDescription);
    }

    /**
     * Handle todo task.
     *
     * @param data the data
     * @return the task
     */
    public Task handleTodo(String data) {
        Tag tag = getTag(data);
        int desPos = data.indexOf("}") + 1;
        Task tempTask = new ToDo(data.substring(desPos), tag);
        if (data.charAt(4) == 'X') {
            tempTask.markAsDone();
        }
        return tempTask;
    }

    /**
     * Handle deadline task.
     *
     * @param data      the data
     * @param formatter the formatter
     * @return the task
     */
    public Task handleDeadline(String data, DateTimeFormatter formatter) {
        int byPos = data.indexOf("(by:");
        String by = data.substring(byPos + 5, data.length() - 1);
        int desPos = data.indexOf("}") + 1;
        String description = data.substring(desPos, byPos - 1);
        Tag tag = getTag(data);
        Task tempTask = new Deadline(description, LocalDateTime.parse(by, formatter), tag);
        if (data.charAt(4) == 'X') {
            tempTask.markAsDone();
        }
        return tempTask;
    }

    /**
     * Handle event task.
     *
     * @param data      the data
     * @param formatter the formatter
     * @return the task
     */
    public Task handleEvent(String data, DateTimeFormatter formatter) {
        int atPos = data.indexOf("(at:");
        String at = data.substring(atPos + 5, data.length() - 1);
        int desPos = data.indexOf("}") + 1;
        String description = data.substring(desPos, atPos - 1);
        Tag tag = getTag(data);
        Task tempTask = new Event(description, LocalDateTime.parse(at, formatter), tag);
        if (data.charAt(4) == 'X') {
            tempTask.markAsDone();
        }
        return tempTask;
    }

    /**
     * Load array list.
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
                    tempTaskList.add(handleTodo(data));
                    break;
                case "D":
                    tempTaskList.add(handleDeadline(data, formatter));
                    break;
                case "E":
                    tempTaskList.add(handleEvent(data, formatter));
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
