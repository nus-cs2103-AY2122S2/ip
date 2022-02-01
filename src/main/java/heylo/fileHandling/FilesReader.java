package heylo.fileHandling;

import heylo.tasks.Task;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading from files.
 */
public class FilesReader {
    /**
     * Returns the list of tasks previously added by the user.
     *
     * @return List of tasks.
     */
    public static ArrayList<Task> getTaskListFromFile() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            File f = new File("data/heylo.txt");

            Files.createDirectories(Paths.get("data"));

            if (!f.createNewFile()) {
                System.out.println("Loading your tasks list...");
            }
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                taskList.add(Task.convertStringToTask(s.nextLine()));
            }
            // Clear file after read
            PrintWriter pw = new PrintWriter("data/heylo.txt");
            pw.close();
            return taskList;
        } catch (IOException ioException) {
            System.out.println("An unknown error occurred.");
            System.exit(1);
        }
        return taskList;
    }
}