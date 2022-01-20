import java.util.ArrayList;

/**
 * Responsible for adding, removing, and performing operations on tasks in taskList.
 */
public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Returns all tasks in taskList in the form of a number list.
     * @return Number list of tasks in taskList.
     */
    public String getTaskList() {
        if (taskList.isEmpty()) {
            return "";
        } else {
            StringBuilder strBuilder = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                String task = (i + 1 + ". " + taskList.get(i) + "\n");
                strBuilder.append(task);
            }
            return strBuilder.toString();
        }
    }

    /**
     * Adds the given Task object to taskList.
     * @param task Task to be added to taskList.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * If user input is formatted correctly, adds a Todo object to taskList and returns it.
     * @param name Name of the todo item to be added.
     * @return The task added to taskList.
     * @throws DukeException If the user did not provide a name for the task.
     */
    public Todo addTodo(String name) throws DukeException {
        if (name.length() == 0 || name.isBlank()) {
            throw new DukeException("No name was provided for the todo item.");
        } else {
            Todo newTodo = new Todo(name);
            addTask(newTodo);
            return newTodo;
        }
    }

    /**
     * If user input is formatted correctly, adds an Event object to taskList and returns it.
     * @param name Name of the event item to be added.
     * @param time Time at which the event is taking place.
     * @return The task added to taskList.
     * @throws DukeException If the user did not provide a name or time for the task.
     */
    public Event addEvent(String name, String time) throws DukeException {
        if (name.length() == 0 || name.isBlank()) {
            throw new DukeException("No name was provided for the event.");
        } else if (time.length() == 0 || time.isBlank()) {
            throw new DukeException("No time was provided for the event.");
        } else {
            Event newEvent = new Event(name, time);
            addTask(newEvent);
            return newEvent;
        }
    }

    /**
     * If user input is formatted correctly, adds an Deadline object to taskList and returns it.
     * @param name Name of the deadline item to be added.
     * @param time Time of the deadline for the item.
     * @return The task added to taskList.
     * @throws DukeException If the user did not provide a name or time for the task.
     */
    public Deadline addDeadline(String name, String time) throws DukeException {
        if (name.length() == 0 || name.isBlank()) {
            throw new DukeException("No name was provided for the deadline item.");
        } else if ( time.length() == 0 || time.isBlank()) {
            throw new DukeException("No time was provided for the deadline item.");
        } else {
            Deadline newDeadline = new Deadline(name, time);
            addTask(newDeadline);
            return newDeadline;
        }
    }

    /**
     * Returns the task corresponding to the given number in numbered list form if it exists.
     * @param i The number of the task to be returned.
     * @return The task corresponding to the parameter 'i'.
     * @throws DukeException If there is no task in taskList with the given number.
     */
    public Task getTaskByNum(int i) throws DukeException {
        if (i > 0 & i <= this.taskList.size()) {
            return this.taskList.get(i - 1);
        } else {
            throw new DukeException("There is no task " + i + " in the list. Type 'list' to view your tasks.");
        }
    }

    /**
     * Returns the number of tasks in taskList.
     * @return Number of tasks in taskList.
     */
    public int getNumberOfTasks() {
        return this.taskList.size();
    }
}