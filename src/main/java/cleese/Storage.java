package cleese;

import task.Task;
import task.Todo;
import task.Event;
import task.Deadline;
import task.TasksList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Handles reading and writing to a specified file during program startup and termination.
 */
public class Storage {
    private static String filePath;
    private static File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Saves the current state of the TasksList to the file in a custom format
     * @param tasksList specified TasksList to save to file
     * @throws IOException
     */
    public static void saveToFile(TasksList tasksList) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath);
        for (int i = 0; i < tasksList.size(); i++) {
            fileWriter.write(tasksList.get(i).toStorageString() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Reads the custom format representing a saved state of the TasksList and restores it in the program
     * @param tasksList
     * @throws FileNotFoundException exception thrown when the file does not exist in the expected location
     */
    public static void readFromFile(TasksList tasksList) throws FileNotFoundException {
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
            }

            // if character is "X" mark the task as done
            if (taskInfo[1].equals("X")) {
                newTask.setDone();
            }
            tasksList.add(newTask);
        }
    }
}
