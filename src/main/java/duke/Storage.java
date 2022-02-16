package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents a storage for data to be saved.
 */
public class Storage {
    private String dataPath;
    private File file;

    /**
     * Constructor for a Storage object
     *
     * @param dataPath the path of the file that is used to save the tasks.
     */
    public Storage(String dataPath) {
        this.dataPath = dataPath;
        checkFolderAndFile();
    }

    /**
     * Checks if a data directory and file specified by data path exist.
     * If not, create a new one.
     */
    public void checkFolderAndFile() {
        try {
            File directory = new File("data");
            directory.mkdir();
            this.file = new File(this.dataPath);
            if (file.createNewFile()) {
                System.out.println("File is not found. A new file has been created for you.");
            } else {
                System.out.println("File is found.");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Saves the tasks of the user into a file.
     *
     * @param taskList a list of the user's tasks.
     */
    public void writeToFile(TaskList taskList) {
        try {
            PrintWriter printWriter = new PrintWriter(new FileWriter(this.file, false));
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.getTask(i);
                assert task instanceof Deadline || task instanceof Event || task instanceof Deadline
                        : "Invalid task type.";
                if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    printWriter.println("D | " + deadline.getStatusIcon() + " | " + deadline.getDescription()
                            + " | " + deadline.getDateAndTime());
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    printWriter.println("E | " + event.getStatusIcon() + " | " + event.getDescription()
                            + " | " + event.getDateAndTime());
                } else if (task instanceof Todo) {
                    Todo todo = (Todo) task;
                    printWriter.println("T | " + todo.getStatusIcon() + " | " + todo.getDescription());
                }
            }
            printWriter.close();
        } catch (IOException e) {
            System.out.println("An error has occurred.");
        }
    }

    /**
     * Reads the tasks that the user has from the saved file.
     *
     * @return a list of the user's tasks.
     */
    public TaskList readFromFile() {
        TaskList taskList = new TaskList();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.file));
            String currentLine = bufferedReader.readLine();
            while (currentLine != null) {
                String[] taskDetails = currentLine.split(" \\| ");
                Task task;
                char taskType = currentLine.charAt(0);
                assert taskType == 'D' || taskType == 'E' || taskType == 'T'
                        : "Task type in storage file is invalid";
                if (taskType == 'D') {
                    task = new Deadline(taskDetails[2], taskDetails[3]);
                } else if (taskType == 'E') {
                    task = new Event(taskDetails[2], taskDetails[3]);
                } else {
                    task = new Todo(taskDetails[2]);
                }
                if (taskDetails[1].equals("[X]")) {
                    task.markAsDone();
                }
                taskList.addTask(task);
                currentLine = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }
}
