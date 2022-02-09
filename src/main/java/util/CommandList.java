
package util;

public class CommandList {

    private final String[] commands;

    /**
     * initialises a list of commands for the user
     */
    public CommandList() {
        commands = new String[]{"list - list out all your tasks",
            "todo - add a Todo task",
            "deadline - add a Deadline task in the form of task /by when",
            "event - add an Event task in the form of task /at when",
            "mark - mark task as done",
            "unmark - mark task as undone",
            "delete - delete task",
            "find - find task in task list"};

    }

    public String[] getCommands() {
        return commands;
    }






}