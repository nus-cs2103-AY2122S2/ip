package duke.util;

import duke.command.ByeCommand;
import duke.command.DeleteCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;

/**
 * Utility class that provides interactivity with the user.
 */
public class Ui {

    private static final String EXIT_MESSAGE = "Fair winds adventurer, till we meet next time yarr. Bye for now.\n";
    private static final String HELP = "Typing 'help me!' will show ye all commands available, yarr.\n";
    private static final String HELP_MESSAGE =
            "These are the list of commands available and format to type them matey:\n";
    private static final String NEW_LINE = "\n";
    private static final String REQUEST_NEXT_COMMAND = "Aye, Aye. Your next command:";
    private static final String REQUEST_NEXT_COMMAND_ANGRY =
            "Aye Aye, better get it right this time. Your next command:";
    private static final String TASK_CALL = "Avast ye Matey. Here goes your task list:\n";
    private static final String TASK_COMPLETED = "Task completed, good job matey!\n";
    private static final String TASK_DELETED = "Alright matey, task has been deleted good on ya.\n";
    private static final String TASK_MATCH = "Avast ye Matey. Here goes your matching tasks in your task list:\n";
    private static final String TASK_UNCHECKED = "Alright matey, hurry up and finish up this task arrr:\n";
    private static final String WELCOME_MESSAGE = "Ahoy! Welcome aboard adventurer, Cap'n Dave at your service.\n";
    private static final String WELCOME_QUESTION = "What can I do for ya?\n";

    /**
     * Prints a message to inform user that task has been deleted
     */
    public String deleteTask() {
        return TASK_DELETED;
    }

    /**
     * Prints a message to inform user that task has been marked as done
     */
    public String markAsDone() {
        return TASK_COMPLETED;
    }

    /**
     * Prints a message to inform user that task has been marked as undone
     */
    public String markAsUndone() {
        return TASK_UNCHECKED;
    }

    /**
     * Prints a message to inform user of the list of outstanding tasks
     */
    public String printTaskList() {
        return TASK_CALL;
    }

    /**
     * Prints a message to request for user to input next command
     */
    public String requestNextCommand() {
        return NEW_LINE + REQUEST_NEXT_COMMAND;
    }

    /**
     * Prints a message to bid goodbye to the user
     */
    public String showExitMessage() {
        return EXIT_MESSAGE;
    }

    /**
     * Prints a message to show a list of commands available
     * and the format in which the user should type it in
     */
    public String showHelpMessage() {
        StringBuilder commandsAndFormatting = new StringBuilder();
        commandsAndFormatting.append("Command: Format\n")
                .append(ByeCommand.COMMAND_WORD).append(": ").append(ByeCommand.COMMAND_EXAMPLE).append("\n")
                .append("deadline").append(": ").append("deadline 2103T project /by 2022-04-30 2359hrs").append("\n")
                .append(DeleteCommand.COMMAND_WORD).append(": ").append(DeleteCommand.COMMAND_EXAMPLE).append("\n")
                .append("event").append(": ").append("event countdown party /at 2021-12-31 10pm-2am").append("\n")
                .append(FindCommand.COMMAND_WORD).append(": ").append(FindCommand.COMMAND_EXAMPLE).append("\n")
                .append(ListCommand.COMMAND_WORD).append(": ").append(ListCommand.COMMAND_EXAMPLE).append("\n")
                .append(MarkCommand.COMMAND_WORD).append(": ").append(MarkCommand.COMMAND_EXAMPLE).append("\n")
                .append("todo").append(": ").append("todo Add new tasks to list").append("\n")
                .append(UnmarkCommand.COMMAND_WORD).append(": ").append(UnmarkCommand.COMMAND_EXAMPLE).append("\n");
        return HELP_MESSAGE + NEW_LINE + commandsAndFormatting;
    }

    /**
     * Prints a message to inform user of the list of tasks that matches the keyword
     */
    public String showMatchingTasks() {
        return TASK_MATCH;
    }

    /**
     * Prints a message to welcome the user
     */
    public String showWelcome() {
        return WELCOME_MESSAGE + HELP + WELCOME_QUESTION;
    }

    /**
     * Prints a message to inform user of their input error
     */
    public String showError(String errorMessage) {
        return errorMessage + REQUEST_NEXT_COMMAND_ANGRY;
    }
}
