package seedu.duke;

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
        for (String t : taskList) {
            String tType = t.substring(0, 7);
            boolean tIsDone = false;
            if (tType.charAt(4) == 'X') {
                tIsDone = true;
            }
            switch (tType.charAt(1)) {
            case 'T':
                String tTodo = t.substring(7);
                this.taskList.add(new ToDo(tTodo, tIsDone));
                break;
            case 'E':
                String[] tEvent = t.substring(7).split(" - at: ");
                this.taskList.add(new Event(tEvent[0], tIsDone,
                        LocalDate.parse(tEvent[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                break;
            case 'D':
                String[] tDeadline = t.substring(7).split(" - by: ");
                this.taskList.add(new Deadline(tDeadline[0], tIsDone,
                        LocalDate.parse(tDeadline[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
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
        this.taskList.add(t);
    }

    /**
     * Deletes an existing task from the task list.
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
        return taskList.get(taskId);
    }

    /**
     * Finds and returns tasks with the specified keyword as a new task list object.
     *
     * @param keyword Keyword to be found in the tasks' descriptions.
     * @return Returns a new task list object with the specified keyword.
     */
    public TaskList findTasks(String keyword) throws DukeException {
        ArrayList<String> result = new ArrayList<String>();
        for (Task t : taskList) {
            if (t.toString().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(t.toString());
            }
        }
        return new TaskList(result);
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
