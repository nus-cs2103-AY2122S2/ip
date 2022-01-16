import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int size = 0;

    public TaskList(int limit) {
        taskList = new ArrayList<>(limit);
    }

    @Override
    public String toString() {
        String message = "\tDear Master, ";
        if (size == 0) {
            message = message + "you have not added anything";
        } else {
            message = message + "here is a list of things you have added:\n";
            for (int i = 0; i < size; i++) {
                message = message + String.format("\t%2d. %s\n", i + 1, getTask(i));
            }
        }

        return message;
    }

    public int getSize() {
        return size;
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void addTask(String item) {
        taskList.add(size, new Task(item));
        size++;

        System.out.println(String.format("\tUnderstood, I have added \"%s\" to the list", item));
    }

    public void markTask(int index) {
        Task currentTask = getTask(index);
        currentTask.markDone();
        System.out.println("\tYes Master, I have marked this task as done:");
        System.out.println(String.format("\t    %s", currentTask));
    }

    public void unmarkTask(int index) {
        Task currentTask = getTask(index);
        currentTask.markNotDone();
        System.out.println("\tYes Master, I have marked this task as not done yet:");
        System.out.println(String.format("\t    %s", currentTask));
    }
}
