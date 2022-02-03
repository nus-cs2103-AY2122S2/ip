import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskManager {

    // Global writer to tasklist.txt that can be used by any class (mostly only used by this one though)
    public static FileWriter writer;

    static {
        try {
            writer = new FileWriter("tasklist.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds task to tasklist.txt and updates the task array
     *
     * @param task
     * @throws IOException
     */
    public static void addTask(Task task) throws IOException {
        taskList.add(task);
        writer.write(task.toString());
        writer.write("\n");
    }

}

