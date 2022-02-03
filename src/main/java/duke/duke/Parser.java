package duke.duke;
public class Parser {

    public Parser() {
    }

    public void parse(String commandString, TaskList tasks, Storage storage) {
        if (commandString.equals("bye")) {
            storage.exit(tasks.arr);
        } else if (commandString.equals("list")) {
            tasks.list(tasks.arr);
        } else if (commandString.contains("mark")) {
            tasks.mark(commandString, tasks.arr);
        } else if (commandString.contains("delete")) {
            tasks.delete(commandString, tasks.arr);
        } else if (commandString.contains("deadline")) {
            tasks.deadline(commandString, tasks.arr);
        } else if (commandString.contains("event")) {
            tasks.event(commandString, tasks.arr);
        } else if (commandString.contains("todo")) {
            tasks.toDo(commandString, tasks.arr);
        } else if (commandString.contains("find")) {
            tasks.find(commandString, tasks.arr);
        } else {
            System.out.println(commandString + " is not a valid command. Please try again.");
        }
    }
}
