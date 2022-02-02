import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Task. Contains a Task constructor, two methods to mark and unmark tasks, toString() method as well as a isMark() method to check if Task is marked
 */
public class Task {
    private boolean mark;
    public String name;

    /**
     * Constructor
     * @param name name of the task
     */
    public Task (String name) {
        this.name = name;
        this.mark = false;
    }

    /**
     * markTask as done
     */
    public void markTask () {
        String markedMessage = "Nice! I've marked this task as done:\n";
        this.mark = true;
        System.out.println(markedMessage + "  " + this);
    }

    /**
     * unmarkTask
     */
    public void unmarkTask() {
        String unmarkedMessage = "OK, I've marked this task as not done yet:\n";
        this.mark = false;
        System.out.println(unmarkedMessage + "  " + this);
    }

    /**
     *
     * @return boolean on whether task is marked
     */
    public boolean isMark() {
        return this.mark;
    }

    /**
     * @override
     * @return String version of task, with marked and name. E.g. [X] Task vs [✓] Task
     */
    public String toString() {
        if (this.mark) {
            String marked = "[✓] ";
            return marked + this.name;
        } else {
            String unmarked = "[X] ";
            return unmarked + this.name;
        }
    }
}