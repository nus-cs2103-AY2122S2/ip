import Tasks.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all storage of tasks to a file.
 */

public class Storage {

    File file = new File("../data/duke.txt");

    /**
     * Initializes storage object.
     */
    public Storage() {}

    /**
     * Writes a single task to the file.
     *
     * @param task task to write to the file.
     * @throws FileNotFoundException if file not found.
     * @throws IOException if io failed.
     */
    public void writeTask(Task task) throws IOException {
        FileWriter fw = new FileWriter(file);
        fw.write(task.toString());
    }

    /**
     * Writes all the tasks in the list to the file.
     * @param tasks list of tasks to write.
     * @throws FileNotFoundException if file not found.
     * @throws IOException if io failed.
     */
    public void writeTasks(ArrayList<Task> tasks) throws IOException{
        FileWriter fw = new FileWriter(file);
        for (Task task : tasks) {
            fw.write(task.toString());
        }
    }

    /**
     * Read entire file, return the tasks as a list of tasks.
     * @return list of all tasks in the file.
     * @throws IOException if file cannot be acessed or created;
     */
    public ArrayList<Task> readTasks() throws IOException{
        //create the file if it does not yet exist
        file.createNewFile();
        Scanner scanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNext()) {
            String s = scanner.nextLine();
            String[] sections = s.split(" ");

            //create task based on the string read, add to tasks
            if (s.charAt(1) == 'E') {
               String name = Stri

            }
        }
    }

}
