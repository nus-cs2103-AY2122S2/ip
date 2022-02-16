package dazz;

public enum Instruction {
    BYE("bye\nDescription: Terminates the program."),
    ALIAS("alias <command> /as <description>\nDescription: Creates your own alias for that particular command"),
    DEADLINE("deadline <description> /by <dd/mm/yyyy HHmm>\nDescription: Adds a task that is a deadline with "
            + "description and date time into your task list"),
    DELETE("delete <index>\nDescription: Deletes your task at a particular index from your task list"),
    EVENT("event <description> /at <dd/mm/yyyy HHmm>\nDescription: Adds a task that is an event with"
            + "description and date time into your task list"),
    FIND("find <keyword>\nDescription: Finds all the task that contains the keyword in your task list"),
    HELP("help\nDescription: Shows you the list of command available and how to use those command"),
    LIST("list\nDescription: Lists out all the tasks in your task list"),
    MARK("mark <index>\nDescription: Marks your task at a particular index in your task list"),
    TODO("todo description\nDescription: Adds a task with description into your task list"),
    UNMARK("unmark <index>\nDescription: Unmarks your task at a particular index in your task list");

    private final String instructionMessage;

    Instruction(String s) {
        instructionMessage = s;
    }

    public String get() {
        return instructionMessage;
    }
}
