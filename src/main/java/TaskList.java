import java.io.*;
import java.util.ArrayList;

public class TaskList {

    public static ArrayList<Task> taskList = new ArrayList<>();


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
            TaskList.taskList.add(task);

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

