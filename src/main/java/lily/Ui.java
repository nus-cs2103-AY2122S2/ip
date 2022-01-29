package lily;
import lily.task.Deadline;
import lily.task.Event;
import lily.task.LilyException;
import lily.task.Task;
import lily.task.Todo;
import lily.TaskList;

import java.util.LinkedList;
import java.util.Scanner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * Deals with all the interactions with the user.
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Ui {
    private static final String LS = System.lineSeparator();
    private static final String INDENT = "    ";
    private static final String DIVIDER = 
            "▼＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝▼" + LS;
    private static Scanner userInput;
    private boolean couldLoad;

    public Ui() {
        userInput = new Scanner(System.in);
        couldLoad = true;
    }

    public void showWelcome(TaskList tl) {
        String logo = 
                LS + "    ██╗     ██╗██╗     ██╗   ██╗" +
                LS + "    ██║     ██║██║     ╚██╗ ██╔╝" +
                LS + "    ██║     ██║██║      ╚████╔╝ " +
                LS + "    ██║     ██║██║       ╚██╔╝  " +
                LS + "    ███████╗██║███████╗   ██║   " +
                LS + "    ╚══════╝╚═╝╚══════╝   ╚═╝   " + LS + LS;
        String welcomeMessage = logo;

        if (couldLoad) {
            welcomeMessage += INDENT + "sup, welcome back" + LS
                    + INDENT + "here's your list from the last time" + LS
                    + LS
                    + tl.printTasks();
        } else {
            showError(INDENT + "oh bother, a new one");
            welcomeMessage += INDENT + "hey." + LS
                    + INDENT + "i don't really wanna track your tasks," + LS
                    + INDENT + "but i guess i have no choice (っ◞‸◟c)" + LS
                    + INDENT + "need help with something?" + LS
                    + LS
                    + INDENT + "Things you can type" + LS
                    + showCommands();
        }
        prettyPrint(welcomeMessage);
    }

    public void showError(String msg) {
        System.err.println(LS + INDENT + DIVIDER
                + INDENT + msg + LS
                + INDENT + DIVIDER);
    }

    public void showLoadingError() {
        couldLoad = false;
    }

    public String readCommand() {
        Scanner userInput = new Scanner(System.in);
        return userInput.nextLine();
    }

    public void closeUi() {
        prettyPrint("finally. what took you so long? (´-ω-`)" + LS
                + INDENT + "oh, if your list had stuff, i've saved it" + LS
                + INDENT + "i'll bring it up for you next time. bye");
        userInput.close();
    }

    public void showList(TaskList tl) {
        if (tl.isEmpty()) {
            showError("there's nothing in the list bro");
        } else {
            prettyPrint("you told me you had to" + LS + tl.printTasks());
        }
    }

    public void showMarked(Task t, int idx) {
        prettyPrint("oh. you've finished it. okay" + LS
                + INDENT + (idx + 1) + "." + t.toString());
    }

    public void showUnmarked(Task t, int idx) {
        prettyPrint("hey, you gotta get it done later, okay?" + LS
                + INDENT + (idx + 1) + "." + t.toString());
    }

    public String showCommands() {
        return INDENT + "> todo: record a task which has no date" + LS
                + INDENT + "> event: note an event with its date after /at" + LS
                + INDENT + "> deadline: note something with its date after /by" + LS
                + INDENT + "> list: show what you have to do" + LS
                + INDENT + "> mark: indicate which numbered task you completed" + LS
                + INDENT + "> unmark: indicate which task you havent completed" + LS
                + INDENT + "> bye: stop talking with Lily";
    }

    public void showTaskAdded(Task t, TaskList tl) {
        String plural = tl.size() == 1 ? " task " : " tasks ";
        prettyPrint("i've dumped this into your list:" + LS
                + INDENT + t.toString() + LS
                + INDENT + "so now you have " + tl.size() + plural + "happening. hope you're happy");
    }

    public void showTaskRemoved(Task t, TaskList tl) {
        prettyPrint("hmph. then why did you make me track your" + LS
                + INDENT + t + LS
                + INDENT + "anyway, now you're left with" + LS
                + INDENT + tl.printTasks());
    }   

    public void showInvalidCommand(String sentence) {
        errorPretty("sorry i don't understand what you meant by" + LS + LS
                + INDENT + sentence + LS + LS
                + INDENT + "you can try these instead:\n" + listCommands());

    }
                            
    private void prettyPrint(String s) {
        System.out.println(LS + INDENT + DIVIDER
                + INDENT + s + LS
                + INDENT + DIVIDER);
    }
}
