package core.tasks;

import core.exceptions.EmptyArgumentException;
import core.exceptions.InvalidDeleteIndexException;
import core.exceptions.NoTaskToDeleteException;
import utilities.OutputFormatter;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    private TaskList() {
        this.taskList = new ArrayList<>();
    }

    private TaskList(ArrayList<Task> initialList) {
        this.taskList = new ArrayList<>();
        this.taskList.addAll(initialList);
    }

    public static TaskList getInstance() {
        return new TaskList();
    }

    public static TaskList getInstance(ArrayList<Task> initialList) {
        return new TaskList(initialList);
    }

    public ArrayList<Task> getAllTasks() {
        return this.taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTaskByIndex(int index) {
        return this.taskList.get(index);
    }

    public Task deleteTask(String input) throws InvalidDeleteIndexException, NoTaskToDeleteException, NumberFormatException {
        if (input.isBlank() || input.isEmpty()) {
            throw new InvalidDeleteIndexException();
        }

        int index = Integer.parseInt(input) - 1;

        if (Integer.parseInt(input) >= this.taskList.size()) {
            throw new NoTaskToDeleteException();
        }
        Task toDelete = getTaskByIndex(index);
        this.taskList.remove(toDelete);
        return toDelete;
    }

    public int getLength() {
        return this.taskList.size();
    }

    public Task getTaskByTaskId(int id) {
        for (Task task : taskList) {
            if (task.getTaskId() == id) {
                return task;
            }
        }
        return null;
    }

    public void completeTaskByIndex(int index) {
        this.taskList.get(index).complete();
    }

    public void completeTaskByTaskId(int taskId) {
        getTaskByTaskId(taskId).complete();
    }

    public String formattedOutput() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        if (this.taskList.size() == 0) {
            outputFormatter.append("The task list is empty!");
        } else {
            outputFormatter.appendAll("Here are the tasks in your list:", "\n");
            int count = 1;

            for (Task task : this.taskList) {
                outputFormatter.appendAll(count, ".", task.toString());

                if (count++ < taskList.size()) {
                    outputFormatter.append("\n");
                }
            }
        }
        return outputFormatter.getFormattedOutput();
    }

    public String exportFileOutput() {
        OutputFormatter outputFormatter = OutputFormatter.getInstance();

        for (Task t : this.taskList) {
            String[] arr;
            if (t instanceof ToDo) {
                arr = new String[] {t.getLabel(), t.status(), t.getTaskDescription()};
            } else {
                arr = new String[] {t.getLabel(), t.status(), t.getTaskDescription(), t.getPeripheralInfo()};
            }
            outputFormatter.appendAll(String.join(" | ", arr), "\n");
        }

        return outputFormatter.getFormattedOutput();
    }

    public String outputWithFoundString(String toBeFound) throws EmptyArgumentException {
        if (toBeFound.isBlank() || toBeFound.isEmpty()) {
            throw new EmptyArgumentException();
        }

        OutputFormatter outputFormatter = OutputFormatter.getInstance();
        if (this.taskList.size() == 0) {
            outputFormatter.append("The task list is empty!");
        } else {
            outputFormatter.appendAll("Here are the matching tasks in your list:", "\n");
            int count = 1;
            int runningLength = 1;

            for (Task task : this.taskList) {
                if (task.getTaskDescription().contains(toBeFound) || task.getTaskDescription().contains(toBeFound.toLowerCase())) {
                    outputFormatter.appendAll(count, ".", task.toString());
                    count++;
                    if (runningLength++ < this.taskList.size()) {
                        outputFormatter.append("\n");
                    }
                } else {
                    runningLength++;
                }
            }
        }
        return outputFormatter.getFormattedOutput();
    }

}
