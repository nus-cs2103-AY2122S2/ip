import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList = new ArrayList<>();

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

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Todo addTodo(String name) throws DukeException {
        if (name.length() == 0 || name.isBlank()) {
            throw new DukeException("No name was provided for the todo item.");
        } else {
            Todo newTodo = new Todo(name);
            addTask(newTodo);
            return newTodo;
        }
    }

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

    public Task getTaskByNum(int i) throws DukeException {
        if (i > 0 & i <= this.taskList.size()) {
            return this.taskList.get(i - 1);
        } else {
            throw new DukeException("There is no task " + i + " in the list. Type 'list' to view your tasks.");
        }
    }

    public int getNumberOfTasks() {
        return this.taskList.size();
    }
}