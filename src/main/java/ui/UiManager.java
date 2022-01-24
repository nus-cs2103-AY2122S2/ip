package ui;

import tasks.TaskManager;
import exceptions.TaskIndexException;
import tasks.Task;

public class UiManager {
    private final String LINE = "-------------------------------------------";

    public UiManager() {

    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String getLine() {
        return this.LINE;
    }

    public void welcome() {
        this.showLine();
        System.out.println("Hello there, I'm Larry!");
        this.showLine();
    }

    public void exit() {
        this.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        this.showLine();
    }

    public void printAdd(Task t, int i) {
        this.showLine();
        System.out.println("Got it, I added:\n"+ t);
        System.out.printf("Now you have %d item(s) in your list\n", i);
        this.showLine();
    }

    public void printMark(Task t) {
        this.showLine();
        System.out.println("Done? Checked it off for you:");
        System.out.println(t);
        this.showLine();
    }

    public void printUnmark(Task t) {
        this.showLine();
        System.out.println("Not done? Let me put it back for you:");
        System.out.println(t);
        this.showLine();
    }

    public void printDelete(String s, int i) {
        this.showLine();
        System.out.println("I removed this task for you:");
        System.out.println(s);
        System.out.printf("Now you have %d items in your list\n", i);
        this.showLine();
    }

    public void printList(TaskManager tm) {
        System.out.println(tm);
    }

    public void errorMessage(String s) {
        this.showLine();
        System.out.println(s);
        this.showLine();
    }

    public String[] parseCommand(String s) throws TaskIndexException {
        s = s.stripLeading();
        s = s.stripTrailing();
        String[] spliced = s.split("\\s+", 2);
        if (spliced.length == 1 &&
                ((spliced[0].equals("todo")) ||
                        (spliced[0].equals("event")) ||
                        (spliced[0].equals("delete")) ||
                        (spliced[0].equals("mark")) ||
                        (spliced[0].equals("unmark")) ||
                        (spliced[0].equals("deadline")))) {
            throw new TaskIndexException("'" +spliced[0] + "'");
        }
        return spliced;
    }
}
