package bernie.tasks;

import bernie.enums.Type;
import bernie.exceptions.InvalidArgumentException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList helps to store tasks given by the user. TaskList is contained in
 * the Bot class and handles the Task for the Bot.
 * 1st method: add, which adds inputs by the user into the storage.
 * 2nd method: list, which shows what's in the TaskList currently.
 */

public class TaskList {
    private List<Task> tasks;
    /**
     * Constructs a TaskList containing an array to contain tasks
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Returns a Task that is indexed i
     * @param i int, the index of the Task
     * @return Task object
     */
    public Task getTask(int i) {
        return tasks.get(i);
    }

    public int numTasksLeft() {
        int count = 0;
        for (Task task : tasks) {
            if (!task.getIsDone()) {
                count++;
            }
        }
        return count;
    }

    /**
     * Adds a Task given the parsedArr and taskType determined by Bernie
     * @param parsedArr String[], given by Bernie
     * @param taskType Type, either TODO, DEADLINE or EVENT
     * @return Task created
     */
    public Task addTask(String[] parsedArr, Type taskType) {
        Task newTask = null;
        String description;
        LocalDate by;
        String at;
        switch (taskType) {
            case TODO:
                description = parsedArr[0];
                newTask = new ToDo(description);
                break;
            case DEADLINE:
                description = parsedArr[0];
                by = LocalDate.parse(parsedArr[1]);
                newTask = new Deadline(description, by);
                break;
            case EVENT:
                description = parsedArr[0];
                at = parsedArr[1];
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
    public void listTasks() {
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
     */
    public Task markTask(Type action, String taskNumber) {
        int taskIndex = Integer.parseInt(taskNumber) - 1;
        if (action.equals(Type.MARK)) {
            tasks.get(taskIndex).markDone();
        } else if (action.equals(Type.UNMARK)) {
            tasks.get(taskIndex).markNotDone();
        }
        return tasks.get(taskIndex);
    }

    public Task taskExists(String taskNum) throws InvalidArgumentException {
        int index = Integer.parseInt(taskNum) - 1;
        try {
            return tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Task number does not exist!");
        }
    }

    /**
     * Deletes tasks from the List
     * @param taskNum String, the task number
     * @return Task, the task that is deleted
     */
    public Task deleteTask(String taskNum) {
        int taskIndex = Integer.parseInt(taskNum) - 1;
        return tasks.remove(taskIndex);
    }

    /**
     * Finds all tasks that have description containing the input given and prints them out
     * @param descriptionToFind String, user input
     */
    public void findTasks(String descriptionToFind) {
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(descriptionToFind)) {
                count++;
                System.out.printf("%d. %s\n", i + 1, task);
            }
        }
        if (count == 0) {
            System.out.println("Nothing!");
        }
    }
}
