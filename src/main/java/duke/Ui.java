package duke;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import duke.command.AddCommand;
import duke.command.Command;
import duke.task.Task;

/**
 * An instance of UI.
 */
public class Ui {
    /**
     * Padding string used to format the bot's responses
     */
    public static final String STR_PADDING = "    ";

    /**
     * Close string.
     *
     * @return A string to acknowledge the program is closing.
     */
    public String close() {
        return "Pleasure to be of service, see you soon! Shutting down in 3 seconds.";
    }

    /**
     * Display the contents of TaskList.
     *
     * @param tasks the TaskList
     * @return the contents of TaskList in string format.
     */
    public String showList(TaskList tasks) {
        StringBuilder sb = new StringBuilder(Ui.STR_PADDING + "Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format(Ui.STR_PADDING + "  %d. " + tasks.get(i), i + 1));
            if (i != tasks.size() - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Shows whether the keyword has been found in the list of Task.
     *
     * @param findString the keyword that the user wants to find
     * @param filteredTask the taskList that has been filtered to search for the keyword
     * @return the contents of filteredTask that matches the keyword, if any.
     */
    public String showFind(String findString, List<Task> filteredTask) {
        if (filteredTask.isEmpty()) {
            return String.format("I could not find any tasks that contains \"%s\"", findString);
        } else {
            StringBuilder sb = new StringBuilder(Ui.STR_PADDING + "You've searched for: \"" + findString + "\"\n");
            sb.append(Ui.STR_PADDING + "Here are the matching tasks in your list:");

            for (int i = 0; i < filteredTask.size(); i++) {
                sb.append(String.format("\n" + Ui.STR_PADDING + "  %d. " + filteredTask.get(i), i + 1));
            }
            return sb.toString();
        }
    }
    /**
     * Show the input task getting marked or unmarked based on its status.
     *
     * @param task the task
     * @return the string
     */
    public String showToggleTask(Task task) {
        StringBuilder sb = new StringBuilder();
        String markedString = "Nice! I've marked this task: ";
        String unmarkedString = "OK, I've unmarked this task: ";
        String outString = task.getIsMarked() ? markedString : unmarkedString;

        sb.append(STR_PADDING).append(outString).append("\n");
        sb.append(STR_PADDING).append("   ").append(task);
        return sb.toString();
    }

    /**
     * Show that the input task has been added to the list.
     *
     * @param listSize the list size
     * @param task     the task
     * @return A string to acknowledge that a task has been added to the list.
     */
    public String showAdditionOrDeletion(Command command, int listSize, Task task) {
        StringBuilder sb = new StringBuilder();
        String taskString = (listSize == 1)
                ? "task"
                : "tasks";
        String ack = (command instanceof AddCommand)
                ? "Got it, I've added this task: "
                : "Noted, I've removed the following task: ";
        String size = String.format("Now you have %d %s in the list", listSize, taskString);

        sb.append(STR_PADDING).append(ack).append("\n");
        sb.append(STR_PADDING).append("   ").append(task).append("\n");
        sb.append(STR_PADDING).append(size);

        return sb.toString();
    }

    /**
     * Show an error message containing the input parameter.
     *
     * @param errorMessage the error message
     * @return the error message in a specific format.
     */
    public String showError(String errorMessage) {
        return String.format("Error: %s", errorMessage);
    }

    /**
     * Shows a list of dated-events that occur on the specified date.
     *
     * @param dateCheck the date that the user wants to see
     * @param filteredTask the taskList that has been filtered to search for the keyword
     * @return the contents of filteredTask that matches the keyword, if any.
     */
    public String showSchedule(Date dateCheck, List<Task> filteredTask) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        if (filteredTask.isEmpty()) {
            return String.format("I could not find any tasks that occurs on \"%s\"", sdf.format(dateCheck));
        } else {
            StringBuilder sb = new StringBuilder(Ui.STR_PADDING);
            sb.append(String.format("You've searched for task(s) that occur on: \"%s\"\n", sdf.format(dateCheck)));
            sb.append(Ui.STR_PADDING + "Here are the matching tasks in your list:");
            for (int i = 0; i < filteredTask.size(); i++) {
                sb.append(String.format("\n" + Ui.STR_PADDING + "  %d. " + filteredTask.get(i), i + 1));
            }
            return sb.toString();
        }
    }

    /**
     * Shows a list of commands that the user can use.
     * @return list of commands that the user can use
     */
    public String showHelp() {
        return "Here are the available commands -- \n"
                + showHelpAdd()
                + showHelpClear()
                + showHelpDelete()
                + showHelpExit()
                + showHelpFind()
                + showHelpList()
                + showHelpToggle()
                + showHelpView();
    }

    private String showHelpAdd() {
        return "Add command: \n"
                + "  todo <description>\n"
                + "  event <description> /at <dd/MM/yyyy hh:mm>\n"
                + "  deadline <description> /by <dd/MM/yyyy hh:mm>\n"
                + "E.g: event meet John at the Cafe /at 06/03/2022 16:00\n\n";
    }

    private String showHelpClear() {
        return "Clear command: [WARNING] This will remove all your tasks!!\n"
                + "  clear\n\n";
    }

    private String showHelpDelete() {
        return "Delete command: [NOTE] you can see the entry number using `list`\n"
                + "  delete <entry number>\n\n";
    }

    private String showHelpExit() {
        return "Exit command: [NOTE] Close using this to ensure your data is saved!!\n"
                + "  exit\n\n";
    }

    private String showHelpFind() {
        return "Find command:\n"
                + "  find <keyword>\n"
                + "E.g: find book -- this would give you all the task that contains "
                + "\t\"book\" such as \"books\" or \"booking\"\n\n";
    }

    private String showHelpList() {
        return "List command: [NOTE] the entry numbers here are used in mark/unmark/delete\n"
                + "  list\n\n";
    }

    private String showHelpToggle() {
        return "Mark/Unmark command: \n"
                + "  mark <entry number>\n"
                + "  unmark <entry number>\n\n";
    }

    private String showHelpView() {
        return "View command: \n"
                + "  view <dd/mm/yyyy>\n"
                + "E.g: view 06/06/2022\n";
    }
}
