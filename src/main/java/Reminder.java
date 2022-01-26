import java.util.ArrayList;
import java.util.List;

public class Reminder {
    protected List<Task> reminders = new ArrayList<>();

    public void add(Task task, boolean printMessage) {
        reminders.add(task);
        if (printMessage) {
            System.out.println("\tGot it. I've added this task:");
            printTaskAndSize(task);
        }
    }

    public void delete(int position, boolean printMessage) throws InvalidTaskIndexException {
        if (position > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            Task task = reminders.remove(--position);
            if(printMessage) {
                System.out.println("\tNoted. I've removed this task:");
                printTaskAndSize(task);
            }
        }
    }

    public int getSize() {
        return reminders.size();
    }

    public List<Task> getReminders() {
        return this.reminders;
    }

    public void list() {
        System.out.println("\tHere are the tasks in your list:");
        if (reminders.size() == 0) {
            System.out.println("\tYou have no task in your list.");
        } else {
            for (int i = 0; i < reminders.size(); i++) {
                Task task = reminders.get(i);
                System.out.println("\t" + (i + 1) + ". " + task);
            }
        }
    }

    public void mark(int position) throws InvalidTaskIndexException {
        if (position > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            reminders.get(--position).setDone();
        }
    }

    public void unmark(int position) throws InvalidTaskIndexException {
        if (position > this.getSize()) {
            throw new InvalidTaskIndexException();
        } else {
            reminders.get(--position).setUndone();
        }
    }

    public void printTaskAndSize(Task task) {
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + reminders.size() + " tasks in the list.");
    }
}