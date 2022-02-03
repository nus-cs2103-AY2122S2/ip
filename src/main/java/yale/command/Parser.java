package yale.command;

import yale.task.TaskList;

/**
 * Class to deal with making sense of
 * user command.
 */
public class Parser {
    /**
     * Constructor method
     */
    public Parser() {
    }

    /**
     * Carries out the different feature
     * operations depending on the user input.
     * @param command User input of type String.
     * @param list List of Task objects.
     */
    public String performAction(String command, TaskList list) {
        if (command.equals("bye")) {
            return "Bye. Hope to see you again soon!";
        } else if (command.equals("list")) {
            return list.listFeature(command, list);
        } else if (command.contains("delete")) {
            return list.deleteFeature(command, list);
        } else if (command.contains("mark") || command.contains("unmark")) {
            return list.markFeature(command, list);
        } else if (command.contains("todo")) {
            return list.todoFeature(command, list);
        } else if (command.contains("deadline")) {
            return list.deadlineFeature(command, list);
        } else if (command.contains("event")) {
            return list.eventFeature(command, list);
        } else if (command.contains("find")) {
            return list.findFeature(command, list);
        } else {
            return "Error: " + command
                    + " is not a valid command. Please try again.";
        }
    }
}
