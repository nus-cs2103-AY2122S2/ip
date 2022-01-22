package duke;

import duke.exceptions.UnknownFileEntry;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all storage of duke.tasks to a file.
 */

public class Storage {

    File file = new File("src/main/data/duke.txt");

    /**
     * Initializes storage object.
     */
    public Storage() {}

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
        //create the file if it does not yet exist
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
