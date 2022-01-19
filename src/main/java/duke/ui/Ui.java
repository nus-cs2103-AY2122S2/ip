package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class Ui {
    private enum Dialogue {
        GREETING, FAREWELL, LIST, MARK, UNMARKED, ADDED, DELETE, NUMLEFT, LINE, LOADERROR
    }
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    // read user command
    public String readCommand() {
        String next = in.nextLine();
        return next;
    }

    public void showLoadingError() {
        speak(Dialogue.LOADERROR);
    }

    public void fareWell() {
        speak(Dialogue.FAREWELL);
    }

    public void showWelcome() {
        speak(Dialogue.GREETING);
    }

    public void showLine() {
        speak(Dialogue.LINE);
    }

    public void printList(TaskList tasks) {
        List<Task> t = tasks.getObjectives();
        speak(Dialogue.LIST);
        for (int i = 0; i < t.size(); i++) {
            System.out.print(i+1);
            System.out.print(".");
            System.out.println(t.get(i));
        }
    }

    public void addList(TaskList tasks) {
        speak(Dialogue.ADDED);
        Integer size = tasks.getSize();
        System.out.println(tasks.getTask(size-1));
        speak(Dialogue.NUMLEFT, size);
    }

    public void markList(TaskList tasks, boolean mark, int index) {
        if (mark) {
            speak(Dialogue.MARK);
            System.out.println(tasks.getTask(index));
        } else {
            speak(Dialogue.UNMARKED);
            System.out.println(tasks.getTask(index));
        }
    }

    public void deleteList(TaskList tasks, int index) {
        speak(Dialogue.DELETE);
        System.out.println(tasks.getTask(index));
    }

    public void showError(String e) {
        System.out.println(e);
    }

    public void print(Object o) {
        System.out.println(o);
    }

    public static void speak(Dialogue option, Integer num) {
        String reply;
        switch (option) {
            case NUMLEFT: reply =  "Now you have " + num.toString() + " task in the list\n";
                break;
            default: reply = "";
        }
        System.out.print(reply);
    }

    public static void speak(Dialogue option) {
        String reply;
        switch (option) {
            case GREETING:
                String logo = " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
                reply = "Hello from\n" + logo +"\n";
                reply +=  "Hello! I'm Duke.\nWhat can I do for you?\n";
                break;
            case LOADERROR: reply = "There is no previously saved file to be loaded.\n";
                break;
            case MARK: reply = "Nice! I've marked this task as done:\n";
                break;
            case LINE: reply = "______________________________________\n";
                break;
            case UNMARKED: reply = "OK, I've marked this task as not done yet:\n";
                break;
            case DELETE: reply = "Noted. I've removed this task:\n";
                break;
            case ADDED: reply = "Got it. I've added this task:\n";
                break;
            case LIST: reply = "Here are the tasks in your list:\n";
                break;
            case FAREWELL: reply = "Bye. Hope to see you again soon!\n";
                break;
            default: reply = "Are you finally done?";
                break;
        }
        System.out.print(reply);
    }
}
