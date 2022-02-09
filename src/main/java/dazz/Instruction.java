package dazz;

public enum Instruction {
    BYE("bye: Terminates the program."),
    DEADLINE("deadline description /by dd/mm/yyyy HHmm: Adds a task that is a deadline with "
            + "description and date time into your task list"),
    DELETE("delete index: Deletes your task at a particular index from your task list"),
    EVENT("event description /at dd/mm/yyyy HHmm: Adds a task that is an event with"
            + "description and date time into your task list"),
    FIND("find keyword: Finds all the task that contains the keyword in your task list"),
    HELP("help: Shows you the list of command available and how to use those command"),
    LIST("list: Lists out all the tasks in your task list"),
    MARK("mark index: Marks your task at a particular index in your task list"),
    TODO("todo description: Adds a task with description into your task list"),
    UNMARK("unmark index: Unmarks your task at a particular index in your task list");

    private final String instructionMessage;

    Instruction(String s) {
        instructionMessage = s;
    }

    public String get() {
        return instructionMessage;
    }
}
