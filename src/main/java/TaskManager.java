import java.io.*;
import java.util.ArrayList;

public class TaskManager {

    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds task to tasklist.txt and updates the task array
     *
     * @param task
     * @throws IOException
     */
    public static void addTask(Task task) throws IOException {
        FileWriter writer = new FileWriter("tasklist.txt", true);
        taskList.add(task);
        writer.write(task.toString());
        writer.write("\n");
        writer.close();
    }

    /**
     * Removes task from tasklist.txt and updates the task array
     *
     * @param taskIndex Index of the task to be removed (starts at 1)
     * @throws IOException
     */
    public static void removeTask(int taskIndex) throws IOException {
        taskList.remove(taskIndex);

        // Create new file to write into
        File newFile = new File("temp.txt");
        newFile.createNewFile();

        FileWriter writer = new FileWriter("temp.txt", true);
        BufferedReader reader = new BufferedReader(new FileReader("tasklist.txt"));

        int counter = -1;
        String currentLine;
        while ((currentLine = reader.readLine()) != null) { // Read till end of file
            counter += 1;
            System.out.print(currentLine + "\n");
            if (counter == taskIndex) {
                continue;
            } else {
                writer.write(currentLine + "\n");
            }
        }

        // Cleanup
        writer.close();
        reader.close();

        File deleteFile = new File("tasklist.txt");
        deleteFile.delete();
        File newTaskListFile = new File("temp.txt");
        newTaskListFile.renameTo(new File("tasklist.txt"));

    }
}

