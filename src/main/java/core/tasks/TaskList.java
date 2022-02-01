package core.tasks;

import java.util.ArrayList;

import core.exceptions.EmptyArgumentException;
import core.exceptions.InvalidDeleteIndexException;
import core.exceptions.NoTaskToDeleteException;
import utilities.OutputFormatter;

/**
 * Class to maintain the list of tasks entered by the user.
 *
 * @author s7manth
 * @version 0.1
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructor for the TaskList class.
     */
    private TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Overloaded constructor for the TaskList class with an initial list of tasks.
     *
     * @param initialList The list of tasks to be preloaded.
     */
    private TaskList(ArrayList<Task> initialList) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(initialList);
    }

    /**
     * Factory method to obtain an instance of the TaskList class.
     *
     * @return An instance of the TaskList.
     */
    public static TaskList getInstance() {
        return new TaskList();
    }

    /**
     * Overloaded factory method to obtain an instance of the TaskList class with an initialized list.
     *
     * @param initialList The list of tasks to be preloaded.
     * @return An instance of the TaskList with the preloaded tasks.
     */
    public static TaskList getInstance(ArrayList<Task> initialList) {
        return new TaskList(initialList);
    }

    /**
     * Method to obtain the actual task list.
     *
     * @return The task list.
     */
    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }

    /**
     * Method to add a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Obtains the task in task list at a specified index.
     *
     * @param index The index of the task to get.
     * @return The task at the given index.
     */
    public Task getTaskByIndex(int index) {
        return this.taskList.get(index);
    }

    /**
     * Method to delete the task from the task list.
     *
     * @param input The number of the task to be deleted in the string format.
     * @return The deleted task.
     * @throws InvalidDeleteIndexException Throws an error if the index to delete the task is invalid.
     * @throws NoTaskToDeleteException Throws an error if the index to delete the task is not present.
     * @throws NumberFormatException Throws an error if the input is not parseable to an integer.
     */
    public Task deleteTask(String input) throws InvalidDeleteIndexException, NoTaskToDeleteException,
            NumberFormatException {
        if (input.isBlank() || input.isEmpty()) {
            throw new InvalidDeleteIndexException();
        }

        int index = Integer.parseInt(input) - 1;

        if (Integer.parseInt(input) >= this.taskList.size()) {
            throw new NoTaskToDeleteException();
        }
        Task toDelete = getTaskByIndex(index);
        this.taskList.remove(toDelete);
        return toDelete;
    }

    /**
     * Method to obtain the length of the task list.
     *
     * @return The length of the task list.
     */
    public int getLength() {
        return this.taskList.size();
    }

    /**
     * Method to obtain the task in the task list based on its id.
     *
     * @param id The id of the task to obtain from the task list.
     * @return The task with the mentioned id.
     */
    public Task getTaskByTaskId(int id) {
        for (Task task : taskList) {
            if (task.getTaskId() == id) {
                return task;
            }
        }
        return null;
    }

    /**
     * Method to mark the task at the specified index as completed.
     *
     * @param index The index of the task to be marked as completed.
     */
    public void completeTaskByIndex(int index) {
        this.taskList.get(index).complete();
    }

    /**
     * Method to mark the task with the specified id as completed.
     *
     * @param taskId The id of the task to be marked as completed.
     */
    public void completeTaskByTaskId(int taskId) {
        getTaskByTaskId(taskId).complete();
    }

    /**
     * Method to obtain a formatted output representing all the tasks present in the task list.
     *
     * @return The string output of the tasks in the task list.
     */
    public String formattedOutput() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        if (this.taskList.size() == 0) {
            outputFormatter.append("The task list is empty!");
        } else {
            outputFormatter.appendAll("Here are the tasks in your list:", "\n");
            int count = 1;

            for (Task task : this.taskList) {
                outputFormatter.appendAll(count, ".", task.toString());

                if (count++ < taskList.size()) {
                    outputFormatter.append("\n");
                }
            }
        }
        return outputFormatter.getFormattedOutput();
    }

    /**
     * Method to obtain the file compatible output for persistent storage.
     *
     * @return The output to store in the txt file.
     */
    public String exportFileOutput() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();

        for (Task t : this.taskList) {
            String[] arr;
            if (t instanceof ToDo) {
                arr = new String[] {t.getLabel(), t.status(), t.getTaskDescription()};
            } else {
                arr = new String[] {t.getLabel(), t.status(), t.getTaskDescription(), t.getPeripheralInfo()};
            }
            outputFormatter.appendAll(String.join(" | ", arr), "\n");
        }

        return outputFormatter.getFormattedOutput();
    }

    /**
     * Method to obtain a filtered list of tasks that have a string in their descriptions.
     *
     * @param toBeFound The string which is to be matched in the task descriptions.
     * @return The formatted output for the list of tasks that have the particular string in their descriptions.
     * @throws EmptyArgumentException Throws an exception when the string to be matched is empty or blank.
     */
    public String outputWithFoundString(String toBeFound) throws EmptyArgumentException {
        if (toBeFound.isBlank() || toBeFound.isEmpty()) {
            throw new EmptyArgumentException();
        }

        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        if (this.taskList.size() == 0) {
            outputFormatter.append("The task list is empty!");
        } else {
            outputFormatter.appendAll("Here are the matching tasks in your list:", "\n");
            int count = 1;
            int runningLength = 1;

            for (Task task : this.taskList) {
                if (task.getTaskDescription().contains(toBeFound)
                        || task.getTaskDescription().contains(toBeFound.toLowerCase())) {
                    outputFormatter.appendAll(count, ".", task.toString());
                    count++;
                    if (runningLength++ < this.taskList.size()) {
                        outputFormatter.append("\n");
                    }
                } else {
                    runningLength++;
                }
            }
        }
        return outputFormatter.getFormattedOutput();
    }

}
