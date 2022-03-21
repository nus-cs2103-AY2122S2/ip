package duke;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a Parser
 */
public class Parser {
    Parser() {}

    /**
     *
     * @param input The command to carry out
     * @return A String that describes the command
     */
    public String parse(String input) {
        String dash = "____________________________________________________________";
        String[] inputArr = input.split(" ");
        String output = "";
        String command = inputArr[0];
        ArrayList<Task> taskList = TaskBank.getBank();

        switch(command) {
        case "list":
            output = Action.showList(taskList);
            break;
        case "bye":
            output = Action.bye();
            break;
        case "mark":
            try {
                int num = Integer.valueOf(inputArr[1]) - 1;
                taskList.get(num).markAsDone();
                output = String.format("Nice! I've marked this task as done: %s\n", taskList.get(num));
            } catch (NumberFormatException error) {
                output = "The index you have entered is not a number.\n";
            } catch (ArrayIndexOutOfBoundsException error) {
                output = "The index you have entered is out of range.\n";
            }
            break;
        case "unmark":
            try {
                int num = Integer.valueOf(inputArr[1]) - 1;
                taskList.get(num).markAsNotDone();
                output = String.format("OK, I've marked this task as not done yet: %s\n", taskList.get(num));
            } catch (NumberFormatException error) {
                output = "The index you have entered is not a number.\n";
            } catch (ArrayIndexOutOfBoundsException error) {
                output = "The index you have entered is out of range.\n";
            }
            break;
        case "todo":
            String remainingWordsTodo = "";
            for (int i = 1; i < inputArr.length; i++) {
                remainingWordsTodo = remainingWordsTodo + " " + inputArr[i];
            }
            Todo td = new Todo(remainingWordsTodo);
            taskList.add(td);
            output = String.format("Got it. I've added this task:\n %s\n", td);
            String noOfTaskTodo = String.format("Now you have %d tasks in the list.\n", taskList.size());
            output = output + noOfTaskTodo;
            break;

        case "deadline":
            try {
                String remainingWordsDeadline = "";
                String deadline = "";
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/by")) {
                        deadline = inputArr[i + 1];
                        break;
                    } else {
                        remainingWordsDeadline = remainingWordsDeadline + " " + inputArr[i];
                    }
                }
                Deadline dl = new Deadline(remainingWordsDeadline, deadline);
                taskList.add(dl);
                output = String.format("Got it. I've added this task:\n %s\n", dl);
                String noOfTaskDeadline = String.format("Now you have %d tasks in the list.\n", taskList.size());
                output = output + noOfTaskDeadline;
            } catch (DateTimeParseException error) {
                System.out.println(error);
                output = "Please follow this format:\ndeadline <task description> /by YYYY-MM-DD\nUse 'help' for help";
            } catch (Exception error) {
                output = "Please follow this format:\ndeadline <task description> /by YYYY-MM-DD\nUse 'help' for help";
            }
            break;
        case "event":
            try {
                String remainingWordsEvent = "";
                String dayAndTime = "";
                for (int i = 1; i < inputArr.length; i++) {
                    if (inputArr[i].equals("/at")) {
                        dayAndTime = inputArr[i + 1];
                        break;
                    } else {
                        remainingWordsEvent = remainingWordsEvent + " " + inputArr[i];
                    }
                }
                Event e = new Event(remainingWordsEvent, dayAndTime);
                taskList.add(e);
                output = String.format("Got it. I've added this task:\n %s\n", e);
                String noOfTaskEvent = String.format("Now you have %d tasks in the list.\n", taskList.size());
                output = output + noOfTaskEvent;
            } catch (DateTimeParseException error) {
                output = "Please follow this format:\nevent <task description> /at YYYY-MM-DD\nUse 'help' for help";
            } catch (Exception error) {
                output = "Please follow this format:\ndeadline <task description> /by YYYY-MM-DD\nUse 'help' for help";
            }
            break;
        case "delete":
            int num = Integer.valueOf(inputArr[1]) - 1;
            try {
                Task d = taskList.get(num);
                taskList.remove(num);
                output = String.format("Noted. I've removed this task:\n %s\n", d);
                String noOfTaskDelete = String.format("Now you have %d tasks in the list.\n", taskList.size());
                output = output + noOfTaskDelete;
            } catch (NumberFormatException error) {
                output = "The index you have entered is not a number.\n";
            } catch (ArrayIndexOutOfBoundsException error) {
                output = "The index you have entered is out of range.\n";
            } catch (Exception error) {
                output = "Use 'help' for help\n";
            }
            break;
        case "help":
            output = "Please visit PinkPanda User Guide for help.\nhttps://simjm.github.io/ip/";
            break;
        case "find":
            try {
                output = Action.find(inputArr[1]);
            } catch (NumberFormatException error) {
                output = "The index you have entered is not a number.\n";
            } catch (ArrayIndexOutOfBoundsException error) {
                output = "The index you have entered is out of range.\n";
            } catch (Exception error) {
                output = "Use 'help' for help\n";
            }
            if (output.isBlank()) {
                output = "No results found\n";
            }
            break;
        case "update":
            int numToUpdate = Integer.valueOf(inputArr[1]) - 1;
            String remainingWordsUpdate = "";
            try {
                for (int i = 2; i < inputArr.length; i++) {
                    remainingWordsUpdate = remainingWordsUpdate + " " + inputArr[i];
                }
                TaskBank.updateTask(numToUpdate, remainingWordsUpdate);
                Task d = taskList.get(numToUpdate);
                output = String.format("Noted. I've updated this task:\n %s\n", d);
            } catch (NumberFormatException error) {
                output = "The index you have entered is not a number.\n";
            } catch (ArrayIndexOutOfBoundsException error) {
                output = "The index you have entered is out of range.\n";
            } catch (Exception error) {
                output = "Use 'help' for help\n";
            }
            break;
        default:
            output = "invalid command! Use 'help' for help";
        }
        return output;
    }
}
