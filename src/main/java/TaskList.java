import java.time.LocalDateTime;
import java.util.ArrayList;
import java.lang.StringBuilder;
import java.util.List;

public class TaskList {
    private ArrayList<Task> taskList;
    private boolean wasModified;

    public TaskList() {
        taskList = new ArrayList<>();
        wasModified = false;
    }

    // Known bug - code will break if user input includes |
    public TaskList(List<String> saveTextFormat) {
        this();
        for (int i = 0; i < saveTextFormat.size(); i++) {
            String taskSaveString = saveTextFormat.get(i);
            String[] taskParams = taskSaveString.split("\\|");
            switch (taskParams[0]) {
            case "T":
                taskList.add(new ToDo(taskParams[2]));
                break;
            case "E":
                taskList.add(new Event(taskParams[2], taskParams[3]));
                break;
            case "D":
                taskList.add(new Deadline(taskParams[2], taskParams[3]));
                break;
            default:
                break;
            }
            if (taskParams[1].equals("1")) {
                markTaskAsDone(i + 1);
            }
        }
    }

    public void resetLastModified() {
        wasModified = false;
    }

    public boolean wasModified() {
        return wasModified;
    }

    public String addTodo(String description) {
        ToDo toDo = new ToDo(description);
        taskList.add(toDo);
        wasModified = true;
        return toDo.toString();
    }

    public String addEvent(String description, LocalDateTime eventDate) {
        Event event = new Event(description, eventDate);
        taskList.add(event);
        wasModified = true;
        return event.toString();
    }

    public String addDeadline(String description, LocalDateTime dueDate) {
        Deadline deadline = new Deadline(description, dueDate);
        taskList.add(deadline);
        wasModified = true;
        return deadline.toString();
    }

    // Return the relevant Task in string form
    public String markTaskAsDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1); // assume valid taskNumber
        task.markAsDone();
        wasModified = true;
        return task.toString();
    }

    // Return the relevant Task in string form
    public String markTaskAsNotDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1); // assume valid taskNumber
        task.markAsNotDone();
        wasModified = true;
        return task.toString();
    }

    // Return the deleted Task in String form. Assume valid inputs.
    public String deleteTask(int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        wasModified = true;
        return task.toString();
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    public ArrayList<String> getSaveRepresentation() {
        ArrayList<String> output = new ArrayList<>();
        for (Task task : taskList) {
            output.add(task.getSaveString());
        }
        return output;
    }

    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "Empty list!";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            output.append(String.format("%d. %s\n", i, taskList.get(i - 1)));
        }
        return output.toString().strip();
    }
}
