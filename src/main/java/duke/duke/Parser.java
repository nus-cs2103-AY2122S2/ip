package duke.duke;
public class Parser {

    public Parser() {
    }

    public String parse(String commandString, TaskList tasks, Storage storage) {
        if (commandString.equals("bye")) {
            return storage.exit(tasks.arr);
        } else if (commandString.equals("list")) {
            return tasks.list(tasks.arr);
        } else if (commandString.contains("mark")) {
            return tasks.mark(commandString, tasks.arr);
        } else if (commandString.contains("delete")) {
            return tasks.delete(commandString, tasks.arr);
        } else if (commandString.contains("deadline")) {
            return tasks.deadline(commandString, tasks.arr);
        } else if (commandString.contains("event")) {
            return tasks.event(commandString, tasks.arr);
        } else if (commandString.contains("todo")) {
            return tasks.toDo(commandString, tasks.arr);
        } else if (commandString.contains("find")) {
            return tasks.find(commandString, tasks.arr);
        } else {
            return commandString + " is not a valid command. Please try again.";
        }
    }
}
