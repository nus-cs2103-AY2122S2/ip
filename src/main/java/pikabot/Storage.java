package pikabot;

import pikabot.task.Task;
import pikabot.task.Deadline;
import pikabot.task.Event;
import pikabot.task.Todo;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Saves the tasks in the computer whenever the task list changes and
 * loads the data from the computer when PikaBot starts up.
 */
public class Storage {

    String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath Path of data file on computer.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if data file exists on user's computer.
     *
     * @return Boolean value whether data file exists.
     */
    public boolean doesFileExist() {
        return Files.exists(Paths.get(filePath));
    }

    /**
     * Parses data file from computer into a TaskList.
     *
     * @param f Data file to be read from.
     * @return TaskList of tasks parsed from data file.
     * @throws FileNotFoundException If file is not found.
     */
    public static TaskList fileToTaskList(File f) throws FileNotFoundException {
        Scanner sc = new Scanner(f);
        ArrayList<Task> arrList = new ArrayList<>();
        TaskList taskList = new TaskList(arrList);

        while (sc.hasNextLine()) {
            String taskStr = sc.nextLine();
            char taskType = taskStr.charAt(1);
            boolean isTaskDone = (taskStr.charAt(4) == 'X');
            taskStr = taskStr.substring(7);

            if (taskType == 'D') {
                String[] taskDetails = taskStr.split("\\(by: ", 2);
                String description = taskDetails[0];
                String date = taskDetails[1].substring(0, taskDetails[1].length()-1);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                Deadline deadline = new Deadline(description, LocalDate.parse(date, dtf));
                if (isTaskDone) {
                    deadline.markAsDone();
                }
                taskList.add(deadline);
            } else if (taskType == 'E') {
                String[] taskDetails = taskStr.split("\\(at: ", 2);
                String description = taskDetails[0];
                String date = taskDetails[1].substring(0, taskDetails[1].length()-1);
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("d-MMM-yyyy");
                Event event = new Event(description, LocalDate.parse(date, dtf));
                if (isTaskDone) {
                    event.markAsDone();
                }
                taskList.add(event);
            } else {
                Todo todo = new Todo(taskStr);
                if (isTaskDone) {
                    todo.markAsDone();
                }
                taskList.add(todo);
            }
        }
        return taskList;
    }

    /**
     * Writes tasks to data file on computer.
     *
     * @param taskList List of tasks.
     * @throws IOException If the named file exists
     * but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot
     * be opened for any other reason.
     */
    public void TaskListToFile(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task : taskList.getTaskList()) {
            fw.write(task.toString() + "\n");
        }
        fw.close();
    }

    /**
     * Adds new task to data file on computer.
     *
     * @param taskToAdd Task to be added to data file on computer.
     * @throws IOException If the named file exists but is
     * a directory rather than a regular file, does not exist
     * but cannot be created, or cannot be opened for any other reason.
     */
    public void appendToFile(Task taskToAdd) throws IOException{
        if (! doesFileExist()) {
            String[] pathDetails = filePath.split("/", 2);
            String folderStr = pathDetails[0];
            File folder = new File(folderStr);
            folder.mkdir();
        }
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(taskToAdd.toString() + "\n");
        fw.close();
    }
}
