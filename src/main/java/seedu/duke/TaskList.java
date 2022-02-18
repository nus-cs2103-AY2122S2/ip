package seedu.duke;

import seedu.exception.DukeException;
import seedu.task.Deadline;
import seedu.task.Event;
import seedu.task.Task;
import seedu.task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Contains the current tasks list at any point of time when the chat-bot is running.
 * Provides methods to modify the tasks list (i.e. add, delete tasks) and retrieve
 * information (i.e. size of the tasks list).
 */
public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<Task>();

    /**
     * Creates an instance of a task list object based on the current
     * existing list of tasks passed in (if not empty).
     *
     * @param taskList Current lists of tasks, each as a string in the array list.
     * @throws DukeException To catch any errors when creating the tasklist.
     */
    public TaskList(ArrayList<String> taskList) throws DukeException {
        assert taskList != null : "TaskList->TaskList: Tasks list cannot be null.";

        for (String task : taskList) {
            String taskType = task.substring(0, 7);
            boolean taskIsDone = false;
            String dateTime;
            if (taskType.charAt(4) == 'X') {
                taskIsDone = true;
            }
            switch (taskType.charAt(1)) {
            case 'T':
                String taskTodo = task.substring(7);
                this.taskList.add(new ToDo(taskTodo, taskIsDone));
                break;
            case 'E':
                String[] taskEvent = task.substring(7).split(" - at: ");
                dateTime = LocalDate.parse(taskEvent[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                this.taskList.add(new Event(taskEvent[0], taskIsDone, dateTime));
                break;
            case 'D':
                String[] taskDeadline = task.substring(7).split(" - by: ");
                dateTime = LocalDate.parse(taskDeadline[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                this.taskList.add(new Deadline(taskDeadline[0], taskIsDone, dateTime));
                break;
            }
        }
    }

    /**
     * Creates an instance of a tasklist object with no existing tasks.
     */
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    /**
     * Adds a new task to the task list.
     */
    public void add(Task t) {
        assert t != null : "TaskList->add: Task to be added cannot be null.";

        this.taskList.add(t);
    }

    /**
     * Deletes an existing task from the task list.
     */
    public void delete(int taskId) {
        assert taskId < taskList.size() : "TaskList->delete: Task ID to be deleted does not exist.";
        assert taskId >= 0 : "TaskList->delete: Task ID to be deleted is invalid.";

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
     * @return Task list as an array list of task objects..
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets a specific task in the current existing list based on the index.
     *
     * @return Specific task object in the tasks list.
     */
    public Task getTasks(int taskId) {
        assert taskId < taskList.size() : "TaskList->getTasks: Task ID to be retrieved does not exist.";
        assert taskId >= 0 : "TaskList->getTasks: Task ID to be retrieved is invalid.";

        return taskList.get(taskId);
    }

    /**
     * Finds and returns tasks with the specified keyword as a new task list object.
     *
     * @param keyword Keyword to be found in the tasks' descriptions.
     * @return Returns a new task list object with the specified keyword.
     */
    public TaskList findTasks(String keyword) throws DukeException {
        assert keyword != null : "TaskList->findTasks: Keyword to search for tasks cannot be null.";

        ArrayList<String> result = new ArrayList<String>();
        for (Task t : taskList) {
            if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(t.toString());
            }
        }
        return new TaskList(result);
    }

    /**
     *
     * @param dateTime Date which we want to search for any clashes in the tasks list.
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
