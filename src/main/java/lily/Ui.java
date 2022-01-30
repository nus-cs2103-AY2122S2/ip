package lily;

import lily.task.Task;

import java.util.Scanner;

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

    /**
     * Creates a new UI object which handles interactions with the user.
     * 
     * @param loadSuccess Whether a save file was loaded.
     */
    public Ui(boolean loadSuccess) {
        userInput = new Scanner(System.in);
        couldLoad = loadSuccess;
    }

    /**
     * Prints the welcome message to the command line.
     * 
     * @param tl The task list to be shown to returning users.
     */
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
                    + tl.getTasks();
        } else {
            showError(INDENT + "oh bother, a new one");
            welcomeMessage += INDENT + "hey." + LS
                    + INDENT + "i don't really wanna track your tasks," + LS
                    + INDENT + "but i guess i have no choice (っ◞‸◟c)" + LS
                    + INDENT + "need help with something?" + LS
                    + LS
                    + INDENT + "Things you can type" + LS
                    + getCommands();
        }
        prettyPrint(welcomeMessage);
    }

    /**
     * Shows an error within the styled boxes.
     * 
     * @param msg The error message to be shown.
     */
    public void showError(String msg) {
        System.err.println(LS + INDENT + DIVIDER
                + INDENT + msg + LS
                + INDENT + DIVIDER);
    }
                            
    /**
     * Shows a message within the styled boxes.
     * 
     * @param s The message to be printed.
     */
    private void prettyPrint(String s) {
        System.out.println(LS + INDENT + DIVIDER
                + INDENT + s + LS
                + INDENT + DIVIDER);
    }

    /**
     * Lists the actions users can take to interact with Lily.
     * 
     * @return A string with indents in front of actions users can take.
     */
    public String getCommands() {
        return INDENT + "> todo: record a task which has no date" + LS
                + INDENT + "> event: note an event with its date after /at" + LS
                + INDENT + "> deadline: note something with its date after /by" + LS
                + INDENT + "> list: show what you have to do" + LS
                + INDENT + "> mark: indicate which numbered task you completed" + LS
                + INDENT + "> unmark: indicate which task you havent completed" + LS
                + INDENT + "> bye: stop talking with Lily";
    }

    /**
     * Reads user input from the command line.
     * 
     * @return The user's input as a string.
     */
    public String readCommand() {
        return userInput.nextLine();
    }

    /**
     * Prints the exit message and closes the scanner.
     */
    public void closeUi() {
        prettyPrint("finally. what took you so long? (´-ω-`)" + LS + LS
                + INDENT + "oh, if your list had stuff, i've saved it" + LS
                + INDENT + "i'll bring it up when you bug me again. bye");
        userInput.close();
    }

    /**
     * Displays the items in the TaskList to the user.
     * 
     * @param tl The TaskList to be shown.
     */
    public void showList(TaskList tl) {
        if (tl.isEmpty()) {
            showError("there's nothing in the list bro");
        } else {
            prettyPrint("you told me you had to" + LS + tl.getTasks());
        }
    }

    /**
     * Shows the user that the Task has been marked.
     * 
     * @param t The Task that was marked.
     * @param idx The position of the Task in the TaskList.
     */
    public void showMarked(Task t, int idx) {
        prettyPrint("oh. you've finished it. okay" + LS
                + INDENT + idx + "." + t.toString());
    }

    /**
     * Shows the user that the Task has been unmarked.
     * 
     * @param t The Task that was unmarked.
     * @param idx The position of the Task in the TaskList.
     */
    public void showUnmarked(Task t, int idx) {
        prettyPrint("hey, you gotta get it done later, okay?" + LS
                + INDENT + idx + "." + t.toString());
    }

    /**
     * Shows that the task the user typed was added to the TaskList.
     * 
     * @param t The task that was added.
     * @param size The size of the TaskList after adding.
     */
    public void showTaskAdded(Task t, int size) {
        String plural = size == 1 ? " task " : " tasks ";
        prettyPrint("i've dumped this into your list:" + LS
                + INDENT + t.toString() + LS
                + INDENT + "so now you have " + size + plural 
                + "happening. hope you're happy");
    }

    /**
     * Shows that the user successfully deleted from the TaskList.
     * 
     * @param t The Task that was removed.
     * @param tl The TaskList after the Task was removed. 
     */
    public void showTaskRemoved(Task t, TaskList tl) {
        prettyPrint("hmph. then why did you make me track your" + LS
                + INDENT + INDENT + t + LS
                + LS
                + INDENT + "anyway, now you're left with" + LS
                + INDENT + tl.getTasks());
    }   

    /**
     * Tell the user that Lily did not understand the command.
     * 
     * @param sentence The user's input.
     */
    public void showInvalidCommand(String sentence) {
        showError("sorry i don't understand what you meant by" + LS + LS
                + INDENT + sentence + LS + LS
                + INDENT + "you can try these instead:" + LS 
                + getCommands());

    }
}