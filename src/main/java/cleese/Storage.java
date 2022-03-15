package cleese;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

/**
 * Handles reading and writing to a specified file during program startup and termination.
 */
public class Storage {
    private static String filePath;
    private static File file;

    /**
     * Constructor for Storage class
     * @param filePath for the location where we want to store the state of the program
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    /**
     * Saves the current state of the TasksList to the file in a custom format
     * @param taskList specified TasksList to save to file
     * @throws IOException
     */
    public static void saveToFile(TaskList taskList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); i++) {
            fileWriter.write(taskList.get(i).toStorageString() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Reads the custom format representing a saved state of the TasksList and restores it in the program
     * @param taskList
     * @throws FileNotFoundException exception thrown when the file does not exist in the expected location
     */
    public static void readFromFile(TaskList taskList) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            Task newTask = null;
            String[] taskInfo = scanner.nextLine().split("#");
            switch (taskInfo[0]) {
            case "T":
                newTask = new Todo(taskInfo[2]);
                break;
            case "D":
                newTask = new Deadline(taskInfo[2], LocalDateTime.parse(taskInfo[3],
                                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                break;
            case "E":
                newTask = new Event(taskInfo[2], LocalDateTime.parse(taskInfo[3],
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                break;
            default:
                System.out.println("Invalid Task Prefix");
            }

            // if character is "X" mark the task as done
            if (taskInfo[1].equals("X")) {
                newTask.setDone();
            }
            taskList.add(newTask);
        }
    }
}
