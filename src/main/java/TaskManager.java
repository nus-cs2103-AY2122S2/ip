import java.io.*;
import java.nio.Buffer;
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
     * Marks task in tasklist and updates accordingly in tasklist.txt
     *
     * @param taskIndex Index of task to be marked
     * @throws IOException
     */
    public static void markTask(int taskIndex) throws IOException {
        // Mark in tasklist array


    }

    /**
     * Removes task from tasklist.txt and updates the task array
     *
     * @param taskIndex Index of the task to be removed (starts at 1)
     * @throws IOException
     */
    public static void removeTask(int taskIndex) throws IOException {
        // Remove from tasklist array
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

    /**
     * Loads existing tasks into the tasklist array (for use at start of program)
     */
    public static void loadTasks() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("tasklist.txt"));
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {
            // Getting task type
            String taskType = currentLine.substring(0, 3); // First 3 characters eg. "[T]"
            if (taskType.equals("[T]")) {
                taskType = "todo";
            } else if (taskType.equals("[D]")) {
                taskType = "deadline";
            } else {
                taskType = "event";
            }

            // Getting completion state
            String completion = currentLine.substring(3, 6); // 4th - 6th characters eg. "[X]"
            boolean isComplete = completion.equals("[X]");

            // Getting description string (already formatted)
            String description = currentLine.substring(6);

            CustomTask task = new CustomTask(taskType, isComplete, description);
            TaskManager.taskList.add(task);

        }
        reader.close();

    }

    /**
     * Writes the current task list into tasklist.txt
     */
    public static void saveTaskList() throws IOException {
        File f = new File("tasklist.txt");
        f.delete(); // Delete current copy
        f.createNewFile();

        FileWriter writer = new FileWriter(f);
        for (Task task : taskList) {
            writer.write(task.toString() + "\n");
        }

        writer.close();
    }
}

