
package util;

public class CommandList {

    private final String[] taskCommands;
    private final String[] contactCommands;

    /**
     * initialises a list of commands for the user
     */
    public CommandList() {
        taskCommands = new String[]{"list - list out all your tasks",
            "todo - add a Todo task",
            "deadline - add a Deadline task in the form of '{task} /by {datetime}'",
            "event - add an Event task in the form of '{task} /at {datetime}'",
            "mark - mark task as done",
            "unmark - mark task as undone",
            "delete - delete task",
            "find - find task in task list",
            "manage contacts - switch to manage contacts"};

        contactCommands = new String[]{"list - list out all your contacts",
            "add - add a contact in the form of '{name} {number}'",
            "update - update a contact in the form of '{name} {number}'",
            "delete - delete contact",
            "manage tasks - switch to manage tasks"};

    }

    public String[] getTaskCommands() {
        return taskCommands;
    }

    public String[] getContactCommands() {
        return contactCommands;
    }






}
