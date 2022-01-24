import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList helps to store tasks given by the user. TaskList is contained in
 * the Bot class and handles the Task for the Bot.
 * 1st method: add, which adds inputs by the user into the storage.
 * 2nd method: list, which shows what's in the TaskList currently.
 */

public class TaskList {
    List<Task> tasks;
    /**
     * Constructs a TaskList containing an array to contain tasks
     */
    TaskList() {
        this.tasks = new ArrayList<>();
    }

    int numTasksLeft() {
        int count = 0;
        for (Task task : tasks) {
            if (!task.isDone) {
                count++;
            }
        }
        return count;
    }

    /**
     * Adds a Task given the inputArr and taskType determined by Bernie.
     * @param inputArr String[], given by Bernie
     * @param taskType String[], either todo, deadline or event
     * @return Task created
     */
    Task addTask(String[] inputArr, String taskType) throws IllegalArgumentException {
        Task newTask = null;
        String description;
        String by;
        String at;
        switch (taskType) {
            case "todo":
                description = inputArr[0];
                newTask = new ToDo(description);
                break;
            case "deadline":
                description = inputArr[0];
                by = inputArr[1];
                newTask = new Deadline(description, by);
                break;
            case "event":
                description = inputArr[0];
                at = inputArr[1];
                newTask = new Event(description, at);
                break;
            default:
                break;
        }
        tasks.add(newTask);
        return newTask;
    }

    /**
     * Prints out every item contained in the tasks array
     */
    void listTasks() {
        if (tasks.size() == 0) {
            System.out.println("NOTHING! :D");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            System.out.printf("%d. %s\n", i + 1, currentTask);
        }
    }

    /**
     * Marks or unmarks Task accordingly
     * @param action String, mark or unmark
     * @param taskNumber String, the taskNumber we want to mark or unmark
     * @return the resulting Task after mark or unmark
     * @throws BernieException if we attempt to mark a marked Task or unmark an unnmarked Task
     */
    Task markTask(Bernie.Type action, String taskNumber) throws BernieException {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        if (action.equals(Bernie.Type.MARK)) {
            tasks.get(taskIndex).markDone();
        } else if (action.equals(Bernie.Type.UNMARK)) {
            tasks.get(taskIndex).markNotDone();
        }
        return tasks.get(taskIndex);
    }

    Task taskExists(String taskNum) throws BernieException {
        int index = Integer.parseInt(taskNum) - 1;
        try {
            Task existingTask = tasks.get(index);
            return existingTask;
        } catch (IndexOutOfBoundsException e) {
            throw new BernieException("Task number does not exist!");
        }
    }

    /**
     * Deletes tasks from the List
     * @param taskNum String, the task number
     * @return Task, the task that is deleted
     */
    Task deleteTask(String taskNum) {
        int taskIndex = Integer.parseInt(taskNum) - 1;
        return tasks.remove(taskIndex);
    }

    /**
     * Saves tasks whenever the taskList changes
     * @param tasksFile File
     * @throws IOException for any IO errors
     */
    void save(File tasksFile) throws IOException {
        FileWriter fileWriter = new FileWriter(tasksFile);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        if (tasks.size() == 0) {
            writer.write("NOTHING! :D");
        }
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            writer.write(String.format("%d. %s\n", i + 1, currentTask));
        }
        writer.close();
    }

    void read(File tasksFile) throws IOException {
        FileReader fileReader = new FileReader(tasksFile);
        BufferedReader reader = new BufferedReader(fileReader);
        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            System.out.println(line);
        }
        reader.close();
    }
}
