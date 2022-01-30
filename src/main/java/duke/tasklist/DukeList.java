package duke.tasklist;

import duke.storage.Storage;
import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;

import java.time.LocalDate;
import java.util.List;

public class DukeList {

    private List<Task> a;
    private Storage storage;

    public DukeList(Storage s) {
        this.a = s.load();
        this.storage = s;
    }

    /**
     * Adds a task to the list, prints add message to the console.
     * @param t Task to be added
     */
    public void add(Task t) {
        a.add(t);
        storage.store(a);
        System.out.println("\nDuke: Got it. I've added this task:\n      "
                + t.show() + "\n      Now you have "
                + this.a.size() + " tasks in the list.\n");
    }


    /**
     * Mark specified task as done.
     * @param x
     */
    public void mark(int x) {
        try {
            a.get(x - 1).mark();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nDuke: Wrong index to mark! Use \"list\" to see the current tasks.\n");
        }
        storage.store(a);
    }

    /**
     * Unmark specified task.
     * @param x The task number
     */
    public void unmark(int x) {
        try {
            a.get(x - 1).unmark();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nDuke: Wrong index to unmark! Use \"list\" to see the current tasks.\n");
        }
        storage.store(a);
    }

    /**
     * Returns a String representation of the list, the indexes as well as the tasks, or NO TASK if empty.
     * @return String representation of list.
     */
    @Override
    public String toString(){
        if (a.isEmpty()) {
            return "NO TASKS\n";
        }
        String ans = "";
        int y = 1;
        for (Task x : a) {
            ans += y + ". " + x.show() + "\n";
            y++;
        }
        return ans;
    }



    /**
     * Deletes specified task from the list
     * @param x Index of task in list to delete
     */
    public void delete(int x) {
        try{
            Task t = a.get(x-1);
            a.remove(x-1);
            System.out.println("\nDuke: Noted. I've removed this task:\n      "
                    + t.show() + "\n      Now you have "
                    + this.a.size() + " tasks in the list.\n");
            storage.store(a);
        } catch( IndexOutOfBoundsException e) {
            System.out.println("\nDuke: Wrong index to delete! Use \"list\" to see the current tasks.\n");
        }
    }

    /**
     * Prints a list of tasks due on current date to the console
     */
    public void todayTask(){
        LocalDate cur = LocalDate.now();
        String day = "\nDuke: Here are the tasks due today\n";
        boolean b = true;
        for(Task t: a){
            if (t instanceof Events || t instanceof Deadlines) {
                if (t.getDate().isEqual(cur)) {
                    b = false;
                    day = day + "     " + t.show() + "\n";
                }
            }
        }
        if (b) {
            day += "      NO TASK DUE TODAY";
        }
        System.out.println(day);
    }

}
