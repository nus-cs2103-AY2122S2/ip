package duke;

import java.time.LocalDate;
import java.util.ArrayList;

public class Parser {

    private boolean isByeCommand;

    public Parser(){
        isByeCommand = false;
    }

    public String parse(String userInput) {

        // Split user input into individual words
        String[] split = userInput.split(" ");
        String outputMessage = "";
        ArrayList<Task> toDoList = TaskList.getToDoList();
        switch (split[0]) {
        case "bye":
            outputMessage = "goodbye!\n";
            isByeCommand = true;
            break;
        case "list":
            for (int i = 0; i < toDoList.size(); i++) {
                Task task = toDoList.get(i);
                outputMessage += String.format("%d. " + task + "\n", i + 1);
            }
            break;
        case "help":
            outputMessage = "try the following commands:\n"
                    + "   list (list out all tasks in your todo list)\n"
                    + "   todo <task> (add a basic task to your todo list)\n"
                    + "   deadline <task> /by <yyyy-mm-dd> (add a task with a deadline to your todo list)\n"
                    + "   event <task> /at <yyyy-mm-dd> (add a new event with its corresponding date to your todo list)\n"
                    + "   mark <task index> (mark that specific task as completed)\n"
                    + "   unmark <task index> (remove mark from specific task)\n"
                    + "   delete <task index> (remove task from todo list)\n"
                    + "   bye (close application)\n";
            break;
        case "unmark":
            try {
                int item = Integer.parseInt(split[1]);
                try {
                    Task t = toDoList.get(item - 1);
                    t.unmark();
                    outputMessage = String.format("Boo! more work to do: %s\n", t.getName());
                } catch (IndexOutOfBoundsException e) {
                    outputMessage = "the index you have entered does not exist!\n";
                }
            } catch (IndexOutOfBoundsException e) {
                outputMessage = ("unmark description cannot be empty!\n");
            } catch (NumberFormatException e) {
                outputMessage = "unmark description must be an integer!\n";
            }
            break;
        case "mark":
            try {
                int item = Integer.parseInt(split[1]);
                try {
                    Task t = toDoList.get(item - 1);
                    t.mark();
                    outputMessage = String.format("great job! I've marked this task as done: %s\n", t.getName());
                } catch (IndexOutOfBoundsException e) {
                    outputMessage = "the index you have entered does not exist!\n";
                }
            } catch (IndexOutOfBoundsException e) {
                outputMessage = "mark description cannot be empty!\n";
            } catch (NumberFormatException e) {
                outputMessage = "mark description must be an integer!\n";
            }
            break;
        case "todo":
            try {
                // Check if description is empty
                String check = split[1];
                // Take the substring of user input after todo
                String name = userInput.substring(5);
                ToDo t = new ToDo(name);
                toDoList.add(t);
                outputMessage = String.format("task added:\n%s\n", t);
                outputMessage += String.format("you now have %d tasks\n", toDoList.size());
            } catch (IndexOutOfBoundsException e) {
                outputMessage = ("the description of a todo cannot be empty!\n");
            }
            break;
        case "deadline":
            try {
                // Check if description is empty
                String check = split[1];
                try {
                    // Take the substring of user input after deadline
                    String item = userInput.substring(9);
                    // Divide the substring into task name and deadline
                    String[] divide = item.split("/");
                    String name = divide[0];
                    String dueDate = divide[1];
                    Deadline d = new Deadline(name, LocalDate.parse(dueDate.substring(3)));
                    toDoList.add(d);
                    outputMessage = String.format("task added:\n%s\n", d);
                    outputMessage += String.format("you now have %d tasks\n", toDoList.size());
                } catch (IndexOutOfBoundsException e) {
                    outputMessage = "deadline description must contain a date!\n";
                }

            } catch (IndexOutOfBoundsException e) {
                outputMessage = "deadline description cannot be empty!\n";
            }
            break;
        case "event":
            try {
                // Check if description is empty
                String check = split[1];
                try {
                    // Take the substring of user input after event
                    String item = userInput.substring(6);
                    // Divide the substring into task name and date
                    String[] divide = item.split("/");
                    String name = divide[0];
                    String time = divide[1];
                    Event e = new Event(name, LocalDate.parse(time.substring(3)));
                    toDoList.add(e);
                    outputMessage = String.format("task added:\n%s\n", e);
                    outputMessage += String.format("you now have %d tasks\n", toDoList.size());
                } catch (IndexOutOfBoundsException e) {
                    outputMessage = "event description must contain a date!\n";
                }
            } catch (IndexOutOfBoundsException e) {
                outputMessage = "event description cannot be empty!\n";
            }
            break;
        case "delete":
            try {
                // Checks if description is empty
                int toDelete = Integer.parseInt(split[1]);
                try {
                    Task t = toDoList.get(toDelete - 1);
                    toDoList.remove(toDelete - 1);
                    outputMessage = String.format("task removed:\n%s\n", t.toString());
                    outputMessage += String.format("you now have %d tasks\n", toDoList.size());
                } catch (IndexOutOfBoundsException e) {
                    outputMessage = "the index you have entered does not exist!\n";
                }
            } catch (IndexOutOfBoundsException e) {
                outputMessage = "delete description cannot be empty!\n";
            } catch (NumberFormatException e) {
                outputMessage = "delete description must be an integer!\n";
            }
            break;
        default:
            // If user input does not match any commands
            outputMessage = "invalid command! try 'help' for list of commands\n";
        }

        return outputMessage;
    }

    public boolean checkByeCommand() {
        return isByeCommand;
    }
}
