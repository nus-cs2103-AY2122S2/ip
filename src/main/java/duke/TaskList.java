package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Contains the current tasks list at any point of time when the chat-bot is running.
 * Provides methods to modify the tasks list (i.e. add, delete tasks) and retrieve
 * information (i.e. size of the tasks list).
 */
public class TaskList {
    private ArrayList<Task> tasksList = new ArrayList<Task>();

    /**
     * Creates an instance of a tasklist object based on the current
     * existing list of tasks passed in (if not empty).
     *
     * @param tasksList Current lists of tasks, each as a string in the array list.
     * @throws DukeException To catch any errors when creating the tasklist.
     */
    public TaskList(ArrayList<String> tasksList) throws DukeException {
        for (String t : tasksList) {
            String tType = t.substring(0, 7);
            boolean tIsDone = false;
            if (tType.charAt(4) == 'X') {
                tIsDone = true;
            }
            switch (tType.charAt(1)) {
            case 'T':
                String tTodo = t.substring(7);
                this.tasksList.add(new Todo(tTodo, tIsDone));
                break;
            case 'E':
                String[] tEvent = t.substring(7).split(" - at: ");
                this.tasksList.add(new Event(tEvent[0], tIsDone,
                        LocalDate.parse(tEvent[1], DateTimeFormatter.ofPattern("MMM dd yyyy"))
                                .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))));
                break;
            case 'D':
                String[] tDeadline = t.substring(7).split(" - by: ");
                this.tasksList.add(new Deadline(tDeadline[0], tIsDone,
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
        this.tasksList = new ArrayList<Task>();
    }

    /**
     * Adds a new task to the task list.
     */
    public void add(Task t) {
        this.tasksList.add(t);
    }

    /**
     * Deletes an existing task from the task list.
     */
    public void delete(int taskId) {
        tasksList.remove(taskId);
    }

    /**
     * Gets the number of tasks in the current existing list.
     *
     * @return Number of tasks in the list as an integer.
     */
    public int size() {
        return tasksList.size();
    }

    /**
     * Gets and returns the current existing tasks list.
     *
     * @return Taskslist as an array list of task objects..
     */
    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Gets a specific task in the current existing list based on the index.
     *
     * @return Specific task object in the tasks list.
     */
    public Task getTask(int taskId) {
        return tasksList.get(taskId);
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
        result += "Here are the tasks in your list: \n";
        for (Task record : tasksList) {
            result += count + ". " + record.toString() + "\n";
            count++;
        }
        return result;
    }
}
