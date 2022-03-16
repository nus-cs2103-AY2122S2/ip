package duke.helpers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import duke.commands.Deadline;
import duke.commands.Event;
import duke.commands.Task;
import duke.commands.ToDo;

/**
 * Represents a storage for all Task data a Duke object saves according to user input.
 */
public class Storage {
    private static String filePath;
    private String dirPath;

    /**
     * Constructs a Storage object.
     *
     * @param dirPath directory path for directory of data to be stored.
     * @param filePath file path for data to be stored.
     */
    public Storage(String dirPath, String filePath) {
        this.dirPath = dirPath;
        this.filePath = filePath;
    }

    /**
     * Loads existing data on Tasks if it exists, creates directory and file to store data if it doesn't exist.
     *
     * @return An array of Task where a Duke object stores all Task data from the user.
     * @throws IOException If I/O operations fails or is interrupted.
     * @throws NoSuchFileException If file for storage of Task data could not be created.
     */
    public ArrayList<Task> loadFileContents() throws IOException {
        //@@tobihy-reused
        //Reused from https://github.com/tobihy/ip/blob/master/src/main/java/duke/storage/Storage.java
        // with minor modifications
        Path directoryPath = Paths.get(this.dirPath);
        if (!Files.exists(directoryPath)) {
            Files.createDirectories(directoryPath);
        }

        Path fPath = Paths.get(this.filePath);
        if (!Files.exists(fPath)) {
            Files.createFile(fPath);
        }

        ArrayList<Task> taskArr = new ArrayList<Task>();
        System.out.println("begin: " + taskArr);

        try {
            Scanner reader = new Scanner(new File(filePath));
            String line;
            while (reader.hasNextLine()) {
                line = reader.nextLine();
                String[] temp = line.split("#");
                String command = temp[0];
                boolean isMarked = temp[1].equals("X");
                String desc = temp[2];
                Task t;
                if (command.equals("T")) {
                    t = new ToDo(desc);
                } else if (command.equals("D")) {
                    String by = temp[3];
                    t = new Deadline(desc, by);
                } else {
                    String time = temp[3];
                    t = new Event(desc, time);
                }
                if (isMarked) {
                    t.markDone();
                } else {
                    t.markUndone();
                }
                taskArr.add(t);
            }
            System.out.println("\nend: " + taskArr);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return taskArr;
    }

    /**
     * Writes existing data on Tasks to a directory and file.
     *
     * @return An array of Task where a Duke object stores all Task data from the user.
     * @throws IOException If I/O operations fails or is interrupted.
     */
    public static ArrayList<Task> saveToFile(ArrayList<Task> taskArr) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task t : taskArr) {
                String toSave;
                String separator = "#";
                String command = t.getType();
                String mark = t.getStatusIcon();
                String desc = t.getDescription();
                String dateTime = (t.getDate().replace(" ", "-") + " " + t.getTime()).trim();
                boolean isDeadline = command.equals("D");
                boolean isEvent = command.equals("E");
                if (isDeadline | isEvent) {
                    toSave = String.join(separator, command, mark, desc, dateTime);
                } else {
                    toSave = String.join(separator, command, mark, desc);
                }
                writer.write(toSave + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return taskArr;
    }
}
