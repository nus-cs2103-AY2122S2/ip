package duke;

import duke.exceptions.UnknownFileEntry;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all storage of duke.tasks to a file.
 */

public class Storage {

    private Path filePath;
    private File file;

    /**
     * Initializes storage object.
     */
    public Storage() {
        //gets the absolute file path in users home directory to store the saved tasks.
        filePath = Paths.get(System.getProperty("user.home"), "dukeChatBot", "data", "savedTasks.txt");
        file = new File(filePath.toString());

    }

    /**
     * Writes a single task to the file.
     *
     * @param task task to write to the file.
     * @throws IOException if io failed.
     */
    public void writeTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(task.toFileString() + "\n");
        fw.close();
    }

    /**
     * Writes all the duke.tasks in the list to the file.
     * @param tasks list of duke.tasks to write.
     * @throws IOException if io failed.
     */
    public void writeTasks(ArrayList<Task> tasks) throws IOException{
        FileWriter fw = new FileWriter(file);
        for (Task task : tasks) {
            fw.write(task.toFileString() + "\n");
        }
        fw.close();
    }

    /**
     * Read entire file, return the duke.tasks as a list of duke.tasks.
     * @return list of all duke.tasks in the file.
     * @throws IOException if file cannot be acessed or created.
     * @throws UnknownFileEntry if file contains an unknown entry.
     */
    public ArrayList<Task> readTasks() throws IOException, UnknownFileEntry{
        //create the file's parent directories if they do not exist
        file.getParentFile().mkdirs();
        //create the file itself if it does not exist
        file.createNewFile();
        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] sections = s.split(" : ");

            //create task based on the string read, add to duke.tasks
            if (sections[0].equals("T")) {
                tasks.add(new Task(sections[2], sections[1] == "1"));
            } else  if (sections[0].equals("D")) {
                tasks.add(new Deadline(sections[2], sections[3],sections[1] == "1"));
            } else if (sections[0].equals("E")) {
                tasks.add(new Event(sections[2], sections[3], sections[1] == "1"));
            } else {
                throw new UnknownFileEntry("unknown line read in task file");
            }
        }
        return tasks;
    }

}
