package duke.managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

import duke.DukeException;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


public class FileManager {
    private TaskList userTaskList;
    private String filePath;
    private Ui ui;

    /**
     * @param filePath representing file path where user task is saved in
     */
    public FileManager(String filePath) {
        userTaskList = new TaskList();
        this.filePath = filePath;
        ui = new Ui();
    }

    private void appendToFile(String filePath, String textToAppend) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(textToAppend);
            fw.close();
        } catch (IOException e) {
            ui.throwDukeException(("We did not manage to save your tasks:\n" + e.getMessage()));
        }
    }

    private void createFile(String filePath) throws DukeException {
        try {
            File yourFile = new File(filePath);
            yourFile.getParentFile().mkdirs(); //
            yourFile.createNewFile(); //Will not create new file if it exists
        } catch (IOException e) {
            ui.throwDukeException("We did not manage to create a file to save your tasks!");
        }
    }

    /**
     * Saves user tasks into txt at file path
     *
     * @throws DukeException if txt file at file path does not exist
     */
    public void saveTasks() throws DukeException {
        try {
            Files.deleteIfExists(new File(this.filePath).toPath());
        } catch (IOException e) {
            ui.throwDukeException("File does not exist!");
        }
        this.createFile(this.filePath); //Creates file at filePath if it does not exist
        for (Task task : this.userTaskList.getArrayList()) {
            this.appendToFile(this.filePath, task.toSaveDataFormat());
        }
    }

    /**
     * Loads users' saved tasks into an TaskList
     *
     * @return TaskList containing users' saved tasks
     * @throws DukeException
     */
    public TaskList loadTasks() throws DukeException {
        try {
            File file = new File(this.filePath);
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String loadedTask = s.nextLine();
                this.parseLoadedTask(loadedTask);
            }
        } catch (FileNotFoundException e) {
            ui.println("No past tasks found! Let's start creating tasks!");
        }
        return this.userTaskList;
    }

    private void parseLoadedTask(String loadedTask) throws DukeException {
        String[] loadedTaskSplit = loadedTask.split(Pattern.quote("|"));
        switch (loadedTaskSplit[0]) {
        case "T":
            Todo todo = new Todo(loadedTaskSplit[2]);
            this.userTaskList.addTask(todo, false);
            if (loadedTaskSplit[1].equals("1")) {
                this.userTaskList.markTaskDone(this.userTaskList.getSize() - 1, false);
            }
            break;
        case "D":
            Deadline deadline = new Deadline(loadedTaskSplit[2], LocalDate.parse(loadedTaskSplit[3],
                    DateTimeFormatter.ofPattern("MMM dd yyyy")).toString());
            // Parse the saved format "MMM dd yyyy" into yyyy-mm-dd which is what Deadline() class requires
            this.userTaskList.addTask(deadline, false);
            if (loadedTaskSplit[1].equals("1")) {
                this.userTaskList.markTaskDone(this.userTaskList.getSize() - 1, false);
            }
            break;
        case "E":
            Event event = new Event(loadedTaskSplit[2], loadedTaskSplit[3]);
            this.userTaskList.addTask(event, false);
            if (loadedTaskSplit[1].equals("1")) {
                this.userTaskList.markTaskDone(this.userTaskList.getSize() - 1, false);
            }
            break;
        default:
            throw new DukeException("Unexpected value: " + loadedTaskSplit[0]);
        }
    }
}
