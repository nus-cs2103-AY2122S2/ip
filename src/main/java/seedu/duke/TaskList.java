package seedu.duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import seedu.exception.DukeException;
import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Task;
import seedu.task.ToDo;

/**
 * Contains the current task list at any point of time when the chatbot is running.
 * Provides methods to modify the task list (i.e. add, delete) and retrieve task
 * information (i.e. size of the task list).
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Creates an instance of a TaskList object based on the current
     * existing list of tasks passed in (if not empty).
     *
     * @param taskList Current lists of tasks, each as a string in the array list.
     * @throws DukeException If error is met while creating the task list.
     */
    public TaskList(ArrayList<String> taskList) throws DukeException {
        assert taskList != null : "TaskList->TaskList: Tasks list cannot be null.";

        for (String task : taskList) {
            String taskType = task.substring(0, 7);
            boolean isTaskDone = false;
            String dateTime;
            if (taskType.charAt(4) == 'X') {
                isTaskDone = true;
            }
            switch (taskType.charAt(1)) {
            case 'T':
                String taskTodo = task.substring(7);
                this.taskList.add(new ToDo(taskTodo, isTaskDone));
                break;
            case 'E':
                String[] taskEvent = task.substring(7).split(" /at ");
                dateTime = LocalDate.parse(taskEvent[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                this.taskList.add(new Event(taskEvent[0], isTaskDone, dateTime));
                break;
            case 'D':
                String[] taskDeadline = task.substring(7).split(" /by ");
                dateTime = LocalDate.parse(taskDeadline[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                this.taskList.add(new Deadline(taskDeadline[0], isTaskDone, dateTime));
                break;
            default:
                throw new DukeException("I was not able to create your task list :(");
            }
        }
    }

    /**
     * Creates an instance of a TaskList object with no existing tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a new task to the task list.
     *
     * @param t New task to be added.
     */
    public void add(Task t) {
        assert t != null : "TaskList->add: Task to be added cannot be null.";

        this.taskList.add(t);
    }

    /**
     * Deletes an existing task from the task list based on the task ID.
     *
     * @param taskId Task ID to be deleted.
     */
    public void delete(int taskId) {
        taskList.remove(taskId);
    }

    /**
     * Gets the number of tasks in the current existing list.
     *
     * @return Number of tasks in the list as an integer.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Gets and returns the current existing tasks list.
     *
     * @return Task list as an array list of Task objects.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets a specific task in the current existing list based on the index.
     *
     * @param taskId Task ID of task to be retrieved.
     * @return Specific task object in the task list.
     */
    public Task getTasks(int taskId) {
        return taskList.get(taskId);
    }

    /**
     * Finds and returns tasks containing the specified keyword as a new task list object.
     *
     * @param keyword Keyword to be found in the tasks' descriptions.
     * @return A new task list object with tasks containing the specified keyword.
     * @throws DukeException If error is met while creating the TaskList object to be returned.
     */
    public TaskList findTasks(String keyword) throws DukeException {
        assert keyword != null : "TaskList->findTasks: Keyword to search for tasks cannot be null.";

        ArrayList<String> result = new ArrayList<>();
        for (Task t : taskList) {
            if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(t.toWrite());
            }
        }
        return new TaskList(result);
    }

    /**
     * Finds if there are any event clashes with the provided date.
     *
     * @param dateTime Date which we want to search for any clashes in the task list.
     * @return True if there are other event tasks in the tasks list that clashes in date, false otherwise.
     */
    public boolean findEventClashes(String dateTime) {
        assert dateTime != null : "TaskList->findClashes: Date to search for task clashes cannot be null.";

        for (Task t : taskList) {
            if (t instanceof Event) {
                String eventDate = ((Event) t).getDate();
                if (eventDate.contains(dateTime.toLowerCase())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Overrides toString() method to print the tasks line by line.
     *
     * @return String of current tasks in the tasks list separated by line breaks.
     */
    @Override
    public String toString() {
        String result = "";
        int count = 1;
        for (Task record : taskList) {
            result += count + ". " + record.toString() + "\n";
            count++;
        }
        return result;
    }
}
