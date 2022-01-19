package baron.tasks;

import baron.exceptions.BaronException;

import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public int getTaskCount() {
        return this.taskList.size();
    }

    public String addTask(Task newTask) {
        this.taskList.add(newTask);
        return newTask.toString();
    }

    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    public boolean markTask(int index) throws BaronException {
        if (this.isEmpty()) {
            throw new BaronException("The task list is empty.");
        } else if (index > 0 && index <= this.getTaskCount()) {
            if (this.taskList.get(index - 1).mark()) {
                return true;
            } else {
                throw new BaronException("The task is already marked.");
            }
        } else {
            throw new BaronException("The task index is invalid, only accepts 1 to "
                    + this.getTaskCount() + ".");
        }
    }

    public boolean unmarkTask(int index) throws BaronException {
        if (this.isEmpty()) {
            throw new BaronException("The task list is empty.");
        } else if (index > 0 && index <= this.getTaskCount()) {
            if (this.taskList.get(index - 1).unmark()) {
                return true;
            } else {
                throw new BaronException("The task is not marked.");
            }
        } else {
            throw new BaronException("The task index is invalid, only accepts 1 to "
                    + this.getTaskCount() + ".");
        }
    }

    @Override
    public String toString() {
        String output = "";
        for (int i = 0; i < this.getTaskCount(); i++) {
            output += (i + 1) + "." + this.taskList.get(i);

            if (i != this.getTaskCount() - 1) {
                output += "\n";
            }
        }

        return output;
    }
}
