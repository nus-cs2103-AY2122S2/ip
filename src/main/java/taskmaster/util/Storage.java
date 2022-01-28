package taskmaster.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

import taskmaster.task.Task;



/**
 * This class encapsulates Storage which handles
 * reading and writing of information to the text
 * file.
 */

public class Storage {

    /**
     * Helper function to create directory if it does not exist.
     * Might throw SecurityException if unable to write at directory.
     * Not necessary to catch since it's a RuntimeException.
     */

    private void createDirectory() {
        String path = Paths.get("").toAbsolutePath() + "/data/";
        File dir = new File(path);

        if (dir.mkdir()) {
            System.out.println("\nI've created the directory " + dir.getName());
            System.out.println("\nYou better be grateful, kid");

        } else {
            System.out.println("\nERROR! What? I'm unable to create directory");
        }
    }

    /**
     * Helper function to create file if the file does not exist
     */

    private void createFile() {
        String path = Paths.get("").toAbsolutePath() + "/data/Duke.txt";
        File filename = new File(path);

        try {
            if (filename.createNewFile()) {
                System.out.println("\nI've created the file " + filename.getName());
                System.out.println("You better be grateful, kid");
            }

        } catch (IOException e) {
            System.out.println("\nWhat's this? An error occurred when I tried to create the file");
        }
    }

    /**
     *  Parse the information from the text file and add
     *  the events parsed to the task list.
     *
     * @param taskList task list the tasks will be added to.
     */

    public void loadFile(TaskList taskList) {
        try {
            String dir = Paths.get("").toAbsolutePath() + "/data/";
            String filename = Paths.get("").toAbsolutePath() + "/data/Duke.txt";
            File directory = new File(dir);
            File fileToBeLoaded = new File(filename);

            if (!directory.exists()) {
                System.out.println("\nHUH? The directory doesn't exist?!");
                createDirectory();
            }
            if (!fileToBeLoaded.exists()) {
                System.out.println("HUH? The file doesn't exist?!");
                createFile();
            }

            Scanner reader = new Scanner(fileToBeLoaded);
            ParseFiles parser = new ParseFiles();
            while (reader.hasNextLine()) {
                Task currentTask = parser.parseTask(reader.nextLine());
                taskList.add(currentTask);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("\nWhat's this? An error occurred when I tried to load the file");
        }
    }

    /**
     * Updates the list of events on the text file based on the
     * list of events that is currently present in the task list.
     *
     * @param taskList Current Task List.
     */
    public void updateList(TaskList taskList) {
        try {
            String filename = Paths.get("").toAbsolutePath() + "/data/Duke.txt";
            FileWriter writer = new FileWriter(filename);
            writer.write(taskList.listTasksInTextFormat());
            writer.close();

        } catch (IOException e) {
            System.out.println("ERROR: Writing task lists to file");
        }
    }

}
