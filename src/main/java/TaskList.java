/**
 * TaskList helps to store tasks given by the user. TaskList is contained in
 * the Bot class and handles the Task for the Bot.
 * 1st method: add, which adds inputs by the user into the storage.
 * 2nd method: list, which shows what's in the TaskList currently.
 */

public class TaskList {
    Task[] tasks;
    int nextIndex = 0;
    int done = 0;
    String lineBreak = "___________________________________________________________";
    /**
     * Constructs a TaskList containing an array to contain tasks
     */
    TaskList() {
        this.tasks = new Task[100];
    }

    int numTasksLeft() {
        return nextIndex - done;
    }

    /**
     * Adds a Task given the inputArr and taskType determined by Bernie.
     * @param inputArr String[], given by Bernie
     * @param taskType String[], either todo, deadline or event
     * @return Task created
     */
    Task addTask(String[] inputArr, String taskType) throws IllegalArgumentException {
        Task newTask = null;
        String description = "";
        String by;
        String at;
        switch (taskType) {
            case "todo":
                description = inputArr[0];
                newTask = new ToDo(description, nextIndex + 1);
                break;
            case "deadline":
                description = inputArr[0];
                by = inputArr[1];
                newTask = new Deadline(description, nextIndex + 1, by);
                break;
            case "event":
                description = inputArr[0];
                at = inputArr[1];
                newTask = new Event(description, nextIndex + 1, at);
                break;
            default:
                break;
        }
        tasks[nextIndex] = newTask;
        nextIndex++;
        return newTask;
    }

    /**
     * Prints out every item contained in the tasks array
     */
    void listTasks() {
        for (int i = 0; i < nextIndex; i++) {
            Task currentTask = tasks[i];
            System.out.printf("%d. %s\n", currentTask.id, currentTask);
        }
    }

    Task markTask(String action, String taskNumber) throws BernieException {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        if (action.equals("mark")) {
            tasks[taskIndex].markDone();
            done++;
        } else if (action.equals("unmark")) {
            tasks[taskIndex].markNotDone();
            done--;
        }
        return tasks[taskIndex];
    }

    boolean taskExists(String taskNum) throws BernieException {
        int index = Integer.parseInt(taskNum) - 1;
        if (index >= 0 && index < this.nextIndex) {
            return true;
        } else {
            throw new BernieException("Task number does not exist!");
        }
    }
}
