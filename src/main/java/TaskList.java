import java.util.ArrayList;
import java.util.List;

public class TaskList {
    protected List<Task> taskList = new ArrayList<>();

    public void add(Task task, boolean printMessage) {
        taskList.add(task);
        if (printMessage) {
            System.out.println("\tGot it. I've added this task:");
            printTaskAndSize(task);
        }
    }

    public void delete(int position, boolean printMessage) throws InvalidTaskIndexException {
        if (position > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = taskList.remove(--position);
            if(printMessage) {
                System.out.println("\tNoted. I've removed this task:");
                printTaskAndSize(task);
            }
        }
    }

    public int getSize() {
        return taskList.size();
    }

    public List<Task> getReminders() {
        return this.taskList;
    }

    public void list() {
        System.out.println("\tHere are the tasks in your list:");
        if (taskList.size() == 0) {
            System.out.println("\tYou have no task in your list.");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println("\t" + (i + 1) + ". " + task);
            }
        }
    }

    public void mark(int position) throws InvalidTaskIndexException {
        if (position > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            taskList.get(--position).setDone();
        }
    }

    public void unmark(int position) throws InvalidTaskIndexException {
        if (position > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            taskList.get(--position).setUndone();
        }
    }

    public void printTaskAndSize(Task task) {
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + taskList.size() + " tasks in the list.");
    }
}