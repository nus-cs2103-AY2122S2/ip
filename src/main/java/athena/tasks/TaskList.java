package athena.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Encapsulates a task list that keeps track of multiple tasks and supports
 * operations on them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;
    private boolean wasModified;

    public TaskList() {
        tasks = new ArrayList<>();
        wasModified = false;
    }

    // Known bug - code will break if user input includes |
    public TaskList(List<String> taskListSaveFormat) {
        this();

        // Create the appropriate task based on each line of the save file and add to taskList
        for (int i = 0; i < taskListSaveFormat.size(); i++) {
            // Extract parameters of tasks from the save format
            String[] taskParams = taskListSaveFormat.get(i).split("\\|");
            String taskIcon = taskParams[0];
            boolean isTaskDone = taskParams[1].equals("1");
            String taskName = taskParams[2];
            String taskDateTime = "";
            if (taskParams.length >= 4) {
                taskDateTime = taskParams[3];
            }

            // Add the task with corresponding parameters to the task list.
            switch (taskIcon) {
            case "T":
                tasks.add(new Todo(taskName));
                break;
            case "E":
                tasks.add(new Event(taskName, taskDateTime));
                break;
            case "D":
                tasks.add(new Deadline(taskName, taskDateTime));
                break;
            default:
                break;
            }
            if (isTaskDone) {
                setTaskAsDone(i + 1);
            }
        }
    }

    public void setNotModified() {
        wasModified = false;
    }

    public boolean wasModified() {
        return wasModified;
    }

    // Returns the task number of the added task
    public int addTodo(String description) {
        tasks.add(new Todo(description));
        wasModified = true;
        return getNumberOfTasks();
    }

    public int addEvent(String description, LocalDateTime eventDate) {
        tasks.add(new Event(description, eventDate));
        wasModified = true;
        return getNumberOfTasks();
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber >= 1 && taskNumber <= getNumberOfTasks());
    }

    public int addDeadline(String description, LocalDateTime dueDate) {
        tasks.add(new Deadline(description, dueDate));
        wasModified = true;
        return getNumberOfTasks();
    }

    public void setTaskAsDone(int taskNumber) {
        Task task = tasks.get(taskNumber - 1); // assume valid taskNumber
        task.setDone();
        wasModified = true;
    }

    public void setTaskAsNotDone(int taskNumber) {
        Task task = tasks.get(taskNumber - 1); // assume valid taskNumber
        task.setNotDone();
        wasModified = true;
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
        wasModified = true;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    // Assume valid taskNumber
    public String getTaskString(int taskNumber) {
        return tasks.get(taskNumber - 1).toString();
    }

    public List<Integer> getTaskNumbersContainingPhrase(String searchPhrase) {
        ArrayList<Integer> taskNumbers = new ArrayList<>();
        for (int i = 1; i <= getNumberOfTasks(); i++) {
            Task task = tasks.get(i - 1);
            if (task.toString().contains(searchPhrase)) {
                taskNumbers.add(i);
            }
        }
        return taskNumbers;
    }

    public ArrayList<String> getSaveFormat() {
        ArrayList<String> output = new ArrayList<>();
        for (Task task : tasks) {
            output.add(task.getSaveFormat());
        }
        return output;
    }

    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "Empty list!";
        }
        StringBuilder output = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            output.append(String.format("%d. %s\n", i, tasks.get(i - 1)));
        }
        return output.toString().strip();
    }
}
