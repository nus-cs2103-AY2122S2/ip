package duke.io;

import duke.exception.DukeException;
import duke.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

/**
 * Represents the local persistent storage in Duke application.
 *
 * @author Zheng Teck
 * @version 1.0
 */
public class Storage {

    public static final String PATH = String.join(File.separator,"data","duke.txt");
    private final File file;
    private final Parser parser;

    private final String event = "E"; // Represents the event task type.
    private final String deadline = "D"; // Represents the deadline  task type.
    private final String todo = "T"; // Represents the todo task type.

    /**
     * Load the storage instance from local storage if exist else create a new instance.
     * @param parser
     */
    public Storage(Parser parser) {
        this.parser = parser;
        this.file = new File(PATH);
        if (!this.file.getParentFile().exists())  {
            this.file.getParentFile().mkdir();
        }
    }

    /**
     * This method writes data in the task list to local storage.
     *
     * @param tasks The list of task in the Duke application.
     * @exception IOException
     * @see IOException
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(PATH);
        StringBuilder result = new StringBuilder();
        for (Task task : tasks.getTaskList()) {
            result.append(task.formatToSave()).append("\n");
        }
        fw.write(result.toString());
        fw.close();
    }

    /**
     * This method read the local storage to import tasks from the local persistent storage.
     *
     * @return Returns the imported list of task from the local persistent storage.
     * @exception IOException
     * @see IOException
     */
    public TaskList importTasks() throws IOException {
        TaskList taskList = new TaskList();
        try {
            Scanner s = new Scanner(this.file);
            String[] dateTimeInput;
            LocalDate date;
            LocalTime time;
            while (s.hasNext()) {
                String[] inputLine = s.nextLine().split("\\|");
                String type = inputLine[0];
                boolean isDone = inputLine[1].equals("1");
                String description = inputLine[2];
                switch (type) {
                    case todo:
                        taskList.addToDo(description, isDone);
                        break;
                    case event:
                        dateTimeInput = inputLine[3].split(" ");
                        if (dateTimeInput.length != 2) {
                            throw new DukeException(Ui.MSG_FILEREADERROR);
                        }
                        date = parser.formatDate(dateTimeInput[0]);
                        time = parser.formatTime(date, dateTimeInput[1]);
                        taskList.addEvent(description, isDone, date, time);
                        break;
                    case deadline:
                        dateTimeInput = inputLine[3].split(" ");
                        if (dateTimeInput.length != 2) {
                            throw new DukeException(Ui.MSG_FILEREADERROR);
                        }
                        date = parser.formatDate(dateTimeInput[0]);
                        time = parser.formatTime(date, dateTimeInput[1]);
                        taskList.addDeadline(description, date, time);
                }
            }
        } catch (FileNotFoundException | DukeException e) {
            this.file.createNewFile();
        }
        return taskList;
    }
}
