package duke.io;

import duke.exception.DukeException;
import duke.Parser;
import duke.task.Task;
import duke.task.TaskList;
import duke.Ui;

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
            String[] input;
            LocalDate date;
            LocalTime time;
            while (s.hasNext()) {
                String[] inputLine = s.nextLine().split("\\|");
                String type = inputLine[0];
                boolean isDone = inputLine[1].equals("1");
                switch (type) {
                    case "T":
                        taskList.addToDo(inputLine[2],isDone);
                        break;
                    case "E":
                        input = inputLine[3].split(" ");
                        if (input.length != 2) {
                            throw new DukeException(Ui.MSG_FILEREADERROR);
                        }
                        date = parser.formatDate(input[0]);
                        time = parser.formatTime(date, input[1]);
                        taskList.addEvent(inputLine[2],isDone, date, time);
                        break;
                    case "D":
                        input = inputLine[3].split(" ");
                        if (input.length != 2) {
                            throw new DukeException(Ui.MSG_FILEREADERROR);
                        }
                        date = parser.formatDate(input[0]);
                        time = parser.formatTime(date, input[1]);
                        taskList.addDeadline(inputLine[2],date,time);
                }
            }
        } catch (FileNotFoundException e) {
            this.file.createNewFile();
        } catch (DukeException e) {
            Ui.print(e.getMessage());
        }
        return taskList;
    }
}
