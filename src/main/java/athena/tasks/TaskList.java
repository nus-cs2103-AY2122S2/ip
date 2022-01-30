package athena.tasks;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private ArrayList<Task> taskList;
    private boolean wasModified;

    public TaskList() {
        taskList = new ArrayList<>();
        wasModified = false;
    }

    // Known bug - code will break if user input includes |
    public TaskList(List<String> taskListSaveFormat) {
        this();

        // Create the appropriate task based on each line of the save file and add to taskList
        for (int i = 0; i < taskListSaveFormat.size(); i++) {
            // Extract parameters of tasks from the save format
            String taskSaveFormat = taskListSaveFormat.get(i);
            String[] taskParams = taskSaveFormat.split("\\|");
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
                taskList.add(new Todo(taskName));
                break;
            case "E":
                taskList.add(new Event(taskName, taskDateTime));
                break;
            case "D":
                taskList.add(new Deadline(taskName, taskDateTime));
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
        taskList.add(new Todo(description));
        wasModified = true;
        return getNumberOfTasks();
    }

    public int addEvent(String description, LocalDateTime eventDate) {
        taskList.add(new Event(description, eventDate));
        wasModified = true;
        return getNumberOfTasks();
    }

    public boolean isValidTaskNumber(int taskNumber) {
        return (taskNumber >= 1 && taskNumber <= getNumberOfTasks());
    }

    public int addDeadline(String description, LocalDateTime dueDate) {
        taskList.add(new Deadline(description, dueDate));
        wasModified = true;
        return getNumberOfTasks();
    }

    public void setTaskAsDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1); // assume valid taskNumber
        task.setDone();
        wasModified = true;
    }

    public void setTaskAsNotDone(int taskNumber) {
        Task task = taskList.get(taskNumber - 1); // assume valid taskNumber
        task.setNotDone();
        wasModified = true;
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
        wasModified = true;
    }

    public int getNumberOfTasks() {
        return taskList.size();
    }

    // Assume valid taskNumber
    public String getTaskString(int taskNumber) {
        return taskList.get(taskNumber - 1).toString();
    }

    public List<Integer> getTaskNumbersContainingPhrase(String searchPhrase) {
        ArrayList<Integer> taskNumbers = new ArrayList<>();
        for (int i = 1; i <= getNumberOfTasks(); i++) {
            Task task = taskList.get(i - 1);
            if (task.toString().contains(searchPhrase)) {
                taskNumbers.add(i);
            }
        }
        return taskNumbers;
    }

    public ArrayList<String> getSaveFormat() {
        ArrayList<String> output = new ArrayList<>();
        for (Task task : taskList) {
            output.add(task.getSaveFormat());
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
