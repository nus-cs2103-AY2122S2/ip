import java.time.LocalDateTime;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public String addTodo(String description) {
        ToDo toDo = new ToDo(description);
        this.taskList.add(toDo);
        return toDo.toString();
    }

    public String addEvent(String description, LocalDateTime eventDate) {
        Event event = new Event(description, eventDate);
        this.taskList.add(event);
        return event.toString();
    }

    public String addDeadline(String description, LocalDateTime dueDate) {
        Deadline deadline = new Deadline(description, dueDate);
        this.taskList.add(deadline);
        return deadline.toString();
    }

    // Return the relevant Task in string form
    public String markTaskAsDone(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1); // assume valid taskNumber
        task.markAsDone();
        return task.toString();
    }

    // Return the relevant Task in string form
    public String markTaskAsNotDone(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1); // assume valid taskNumber
        task.markAsNotDone();
        return task.toString();
    }

    // Return the deleted Task in String form. Assume valid inputs.
    public String deleteTask(int taskNumber) {
        Task task = this.taskList.get(taskNumber - 1);
        this.taskList.remove(taskNumber - 1);
        return task.toString();
    }

    public int getNumberOfTasks() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        if (this.taskList.size() == 0) {
            return "Empty list!";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= this.taskList.size(); i++) {
            output.append(String.format("%d. %s\n", i, this.taskList.get(i - 1)));
        }
        return output.toString().strip();
    }
}
