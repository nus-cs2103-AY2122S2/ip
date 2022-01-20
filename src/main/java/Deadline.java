package main.java;

/**
 * Deadline is a type of Task that contains addition.
 */

public class Deadline extends Task{
    String type = "D";
    String info;

    /**
     * Constructor for deadline.
     * @param name name of task.
     * @param info additional info of the task.
     */

    Deadline(String name, String info) {
        super(name);
        this.info = info;
    }

    /**
     * @return String representation of the task.
     */

    @Override
    String display() {
        return "[" +this.type + "] " + "[" +this.done + "] " + this.name + " (by " + info + ")";
    }
}
