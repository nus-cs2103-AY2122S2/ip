package baron.tasks;

import baron.exceptions.BaronException;
import baron.message.Message;
import baron.util.DateTimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;
    private ArrayList<Task> previousTaskList = null;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public TaskManager(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public boolean isEmpty() {
        return this.taskList.isEmpty();
    }

    public int getTaskCount() {
        return this.taskList.size();
    }

    public Task addTask(Task newTask) {
        this.taskList.add(newTask);
        return newTask;
    }

    public Task addTask(TaskType taskType, String commandArg) throws BaronException {
        Task newTask;
        if (taskType == TaskType.TODO) {
            newTask = new ToDo(commandArg);
        } else if (taskType == TaskType.DEADLINE) {
            String[] splitString = commandArg.split(" /by ", 2);
            if (splitString.length == 2) {
                LocalDateTime localDateTime = DateTimeUtil.getDateTime(splitString[1]);
                newTask = new Deadline(splitString[0], localDateTime);
            } else if (splitString.length == 1) {
                if (splitString[0].startsWith("/by")) {
                    throw new BaronException(Message.generateEmptyDescMessage(taskType));
                } else {
                    throw new BaronException("Please specify a deadline by the /by keyword.");
                }
            } else {
                throw new BaronException("Please specify only one deadline.");
            }
        } else {
            String[] splitString = commandArg.split(" /at ", 2);
            if (splitString.length == 2) {
                LocalDateTime localDateTime = DateTimeUtil.getDateTime(splitString[1]);
                newTask = new Event(splitString[0], localDateTime);
            } else if (splitString.length == 1) {
                if (splitString[0].startsWith("/at")) {
                    throw new BaronException(Message.generateEmptyDescMessage(taskType));
                }
                throw new BaronException("Please specify a date by the /at keyword.");
            } else {
                throw new BaronException("Please specify only one date.");
            }
        }

        this.taskList.add(newTask);
        this.previousTaskList = this.getAllTasks();
        return newTask;
    }

    public Task getTask(int index) {
        return this.taskList.get(index - 1);
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.taskList);
    }

    public boolean markTask(int index) throws BaronException {
        if (this.isEmpty()) {
            throw new BaronException(Message.MESSAGE_NO_TASK);
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
            throw new BaronException(Message.MESSAGE_NO_TASK);
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

    public Task deleteTask(int index) throws BaronException {
        if (this.isEmpty()) {
            throw new BaronException(Message.MESSAGE_NO_TASK);
        } else if (index > 0 && index <= this.getTaskCount()) {
            this.previousTaskList = this.getAllTasks();
            Task deletedTask = this.taskList.get(index - 1);
            this.taskList.remove(index - 1);
            return deletedTask;
        } else {
            throw new BaronException("The task index is invalid, only accepts 1 to "
                    + this.getTaskCount() + ".");
        }
    }

    public void revertChanges() {
        if (this.previousTaskList != null) {
            this.taskList = new ArrayList<>(this.previousTaskList);
            this.previousTaskList = null;
        }
    }

    public void commitChanges() {
        this.previousTaskList = null;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.getTaskCount(); i++) {
            output.append("  ").append(i + 1).append(".").append(this.taskList.get(i));

            if (i != this.getTaskCount() - 1) {
                output.append("\n");
            }
        }

        return output.toString();
    }
}
