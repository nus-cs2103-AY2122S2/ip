package bernie.tasks;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import bernie.enums.Type;
import bernie.exceptions.InvalidArgumentException;

/**
 * TaskList helps to store tasks that are created. TaskList is used for the adding,
 * deleting, listing and marking of tasks.
 */

public class TaskList {
    private List<Task> tasks;
    /**
     * Constructs a TaskList containing an arrayList to contain tasks
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        assert tasks.size() >= 0;
        return tasks.size();
    }

    public boolean isEmpty() {
        assert tasks.size() >= 0;
        return tasks.size() == 0;
    }

    /**
     * Returns the Task that is indexed i
     * @param i int, the index of the Task
     * @return Task object
     */
    public Task getTask(int i) {
        Task retrievedTask = tasks.get(i);
        assert retrievedTask != null;
        return retrievedTask;
    }

    /**
     * Returns the number of tasks in the TaskList that are not yet done
     * @return int, number of tasks not done
     */
    public int numTasksLeft() {
        int count = 0;
        for (Task task : tasks) {
            if (!task.getIsDone()) {
                count++;
            }
        }
        assert (count >= 0);
        return count;
    }

    /**
     * Adds a type of Task given the parsedArr and taskType
     * @param parsedArr String[], determined by parser which parses the user input
     *                  into an array of arguments
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
     * Returns a String of every item contained in the TaskList
     * @return String, the list of tasks
     */
    public String listTasks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task currentTask = tasks.get(i);
            s.append(String.format("%d. %s\n", i + 1, currentTask));
        }
        return s.toString();
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

    /**
     * Checks if a taskExists given the task number
     * @param taskNum String, the task number
     * @throws InvalidArgumentException if the task number given does not exist
     */
    public void checkTaskExists(String taskNum) throws InvalidArgumentException {
        int index = Integer.parseInt(taskNum) - 1;
        try {
            tasks.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("Task number does not exist!");
        }
    }

    /**
     * Deletes tasks from the List
     * @param taskNum String, the task number we want to delete
     * @return Task, the task that is deleted
     */
    public Task deleteTask(String taskNum) {
        int taskIndex = Integer.parseInt(taskNum) - 1;
        return tasks.remove(taskIndex);
    }

    /**
     * Finds all tasks that have description containing the input given and returns the
     * resulting message
     * @param descriptionToFind String, user input
     * @return String, the message
     */
    public String findTasks(String descriptionToFind) {
        StringBuilder s = new StringBuilder();
        int count = 0;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            String taskDescription = task.getDescription();
            if (taskDescription.contains(descriptionToFind)) {
                count++;
                s.append(String.format("%d. %s\n", i + 1, task));
            }
        }
        assert count >= 0;
        if (count == 0) {
            return s.append("Nothing!")
                    .toString();
        }
        return s.toString();
    }

    /**
     * Initialises the tasks that are loaded via the Storage on start up
     * @param taskArgs String[], parsed by the parser, for creation of tasks
     * @param type Task type
     * @param isDone if the task is done or not: in symbol "X" for done, " " for not done
     */
    public void initTask(String[] taskArgs, String type, String isDone) {
        Task newTask = null;
        String description;
        String by;
        String at;
        switch (type) {
        case "todo":
            description = taskArgs[0];
            newTask = new ToDo(description);
            if (isDone.equals("X")) {
                newTask.markDone();
            }
            break;
        case "deadline":
            description = taskArgs[0];
            // need convert Jan 28 2022 to 2022-01-28 LocalDate
            by = taskArgs[1];
            LocalDate localDate = LocalDate.parse(by);
            newTask = new Deadline(description, localDate);
            if (isDone.equals("X")) {
                newTask.markDone();
            }
            break;
        case "event":
            description = taskArgs[0];
            at = taskArgs[1];
            newTask = new Event(description, at);
            if (isDone.equals("X")) {
                newTask.markDone();
            }
            break;
        default:
            break;
        }
        assert (newTask != null);
        tasks.add(newTask);
    }
}
