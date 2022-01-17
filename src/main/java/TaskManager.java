import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> taskList;

    TaskManager() {
        this.taskList = new ArrayList<>();
    }

    int countTasks() {
        return this.taskList.size();
    }

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= this.countTasks(); i++) {
            System.out.println(i + ". " + this.taskList.get(i - 1));
        }
    }

    public void mark(int index) {
        try {
            Task taskToMark = this.taskList.get(index);
            taskToMark.mark();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("    " + this.taskList.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }

    public void add(Task task) {
        this.taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + task);
        System.out.println(String.format("Now you have %d tasks in the list.", this.countTasks()));
    }

    public void unmark(int index) {
        try {
            Task taskToMark = this.taskList.get(index);
            taskToMark.unmark();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("    " + this.taskList.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }
}

