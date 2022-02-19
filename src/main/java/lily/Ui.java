package lily;

import lily.task.Task;

import java.util.LinkedList;

/**
 * Deals with all the interactions with the user.
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Ui {
    private static final String LS = System.lineSeparator();
    private final boolean CAN_LOAD;
    private String output;

    /**
     * Creates a new UI object which handles interactions with the user.
     * 
     * @param loadSuccess Whether a save file was loaded.
     */
    public Ui(boolean loadSuccess) {
        CAN_LOAD = loadSuccess;
    }

    /**
     * Returns output of command.
     *
     * @return output of processing a command.
     */
    public String getOutput() {
        return output;
    }

    /**
     * Stores the error message into UI for displaying.
     *
     * @param msg the error message
     */
    public void showError(String msg) {
        output = msg;
    }

    /**
     * Prints the welcome message to the command line.
     * 
     * @param tl The task list to be shown to returning users.
     */
    public String showWelcome(TaskList tl) {
        String welcomeMessage;

        if (CAN_LOAD) {
            welcomeMessage = "sup, welcome back" + LS
                    + "here's your list from the last time" + LS
                    + LS
                    + listTasks(tl.getContents()) + LS
                    + LS
                    + "why is it so full of nonsense";
        } else {
            welcomeMessage = "oh bother, a new one" + LS 
                    + LS
                    + "hey." + LS
                    + "i don't really wanna track your tasks," + LS
                    + "but i guess i have no choice (っ◞‸◟c)" + LS
                    + "need help with something?" + LS
                    + LS
                    + "Things you can type:" + LS
                    + getCommands();
        }
        return welcomeMessage;
    }

    /**
     * Lists the tasks in the form of a String for printing.
     *
     * @return The tasks as Strings to be printed.
     */
    private String listTasks(LinkedList<Task> list) {
        String listMsg = "";
        for (int i = 1; i <= list.size(); i++) {
            // Only add LS for the last task
            String endingLs = (i == list.size() ? "" : LS);
            String currTask = list.get(i - 1).toString();
            listMsg += "    " + i + "." + currTask + endingLs; 
        }
        return listMsg;
    }

    /**
     * Lists the actions users can take to interact with Lily.
     *
     * @return A string with indents in front of actions users can take.
     */
    public String getCommands() {
        return "> todo: record a task which has no date" + LS
                + "> event: note an event with its date after /at" + LS
                + "> deadline: note something with its date after /by" + LS
                + "> list: show what you have to do" + LS
                + "> mark: indicate which numbered task you completed" + LS
                + "> unmark: indicate which task you havent completed" + LS
                + "> find: search your list for a single keyword" + LS
                + "> bye: stop talking with Lily";
    }

    /**
     * Prints the exit message and closes the scanner.
     * 
     * @deprecated
     */
    @Deprecated
    public void closeUi() {
        output = "if your list had stuff, i've saved it" + LS
                + "don't bug me again. bye (´-ω-`)";
    }

    /**
     * Displays the items in the TaskList to the user.
     *
     * @param tl The TaskList to be shown.
     */
    public void showList(TaskList tl) {
        if (tl.isEmpty()) {
            output = "there's nothing in the list bro";
        } else {
            output = "you told me you had to" + LS + listTasks(tl.getContents());
        }
    }

    /**
     * Shows the user that the Task has been marked.
     *
     * @param t The Task that was marked.
     * @param idx The position of the Task in the TaskList.
     */
    public void showMarked(Task t, int idx) {
        output = "oh. you've finished it. okay" + LS
                + idx + "." + t.toString();
    }

    /**
     * Shows the user that the Task has been unmarked.
     *
     * @param t The Task that was unmarked.
     * @param idx The position of the Task in the TaskList.
     */
    public void showUnmarked(Task t, int idx) {
        output = "hey, you gotta get it done later, okay?" + LS
                + idx + "." + t.toString();
    }

    /**
     * Shows that the task the user typed was added to the TaskList.
     *
     * @param t The task that was added.
     * @param size The size of the TaskList after adding.
     */
    public void showTaskAdded(Task t, int size) {
        String plural = size == 1 ? " task " : " tasks ";

        output = "i've dumped this into your list:" + LS
                + t.toString() + LS
                + "so now you have " + size + plural
                + "happening. hope you're happy";
    }

    /**
     * Shows that the user successfully deleted from the TaskList.
     * 
     * @param t The Task that was removed.
     * @param tl The TaskList after the Task was removed. 
     */
    public void showTaskRemoved(Task t, TaskList tl) {
        output = "hmph. then why did you make me track your" + LS
                + t + LS
                + LS
                + "anyway, now you're left with" + LS
                + listTasks(tl.getContents());
    }   

    /**
     * Shows the tasks which have descriptions matching the search term.
     * 
     * @param searchTerm What the user is searching for.
     * @param tl The TaskList to search inside.
     */
    public void showFind(String searchTerm, TaskList tl) {
        LinkedList<Task> taskLinkedList = tl.getContents();
        LinkedList<Task> matchedTasks = new LinkedList<>();

        for (int i = 0; i < tl.getSize(); i++) {
            Task currTask = taskLinkedList.get(i);
            String descToBeSearched = currTask.getDesc();

            if (descToBeSearched.contains(searchTerm)) {
                matchedTasks.add(currTask);
            }
        }

        if (matchedTasks.size() == 0) {
            output = LilyException.ERROR_404;
        } else {
            output = "ah. here are your tasks with \"" + searchTerm + "\" in them." + LS
                    + listTasks(matchedTasks);
        }
    }

    /**
     * Tell the user that Lily did not understand the command.
     * 
     * @param sentence The user's input.
     */
    public void showInvalidCommand(String sentence) {
        output = sentence + "?" + LS
                + LS
                + "bro i cannot understand you ( ︶︿︶)" + LS + LS
                + "i only know these hor:" + LS
                + getCommands();

    }
}