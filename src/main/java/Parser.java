import yale.task.TaskList;

public class Parser {
    public Parser() {
    }

    public void performAction(String command, TaskList list) {
        if (command.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (command.equals("list")) {
            list.listFeature(command, list);
        } else if (command.contains("delete")) {
            list.deleteFeature(command, list);
        } else if (command.contains("mark") || command.contains("unmark")) {
            list.markFeature(command, list);
        }  else if (command.contains("todo")) {
            list.todoFeature(command, list);
        } else if (command.contains("deadline")) {
            list.deadlineFeature(command, list);
        } else if (command.contains("event")) {
            list.eventFeature(command, list);
        } else {
            System.out.println("Error: " + command
                    + " is not a valid command. Please try again.");
        }
    }
}
