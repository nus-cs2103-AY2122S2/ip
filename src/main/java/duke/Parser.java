package duke;

import java.io.IOException;
import java.util.ArrayList;

public class Parser {

    public static void parse(String input, TaskList taskList) throws IOException {
        String[] array = input.split(" ");
        String execute = array[0];
        switch(execute) {
            case("list"):
                Ui.list(taskList);
                break;
            case("mark"):
                int numToMark = Integer.parseInt(array[1]);
                if (numToMark > taskList.getTaskListSize() || numToMark < 1) {
                    throw new InvalidInputException("\n" + Ui.BLANK_LINE
                            + "    Please select a valid task number to mark!\n" + Ui.BLANK_LINE);
                }
                if (numToMark > taskList.getTaskListSize() || numToMark < 1) {
                    throw new InvalidInputException("\n" + Ui.BLANK_LINE
                        + "    Please select a valid task number to mark!\n" + Ui.BLANK_LINE);
                }
                Ui.mark(numToMark, taskList);
                Storage.saveToFile(taskList);
                break;
            case("unmark"):
                int numToUnmark = Integer.parseInt(array[1]);
                if (numToUnmark > taskList.getTaskListSize() || numToUnmark < 1) {
                    throw new InvalidInputException("\n" + Ui.BLANK_LINE
                            + "    Please select a valid task number to unmark!\n" + Ui.BLANK_LINE);
                }
                if (numToUnmark > taskList.getTaskListSize() || numToUnmark < 1) {
                    throw new InvalidInputException("\n" + Ui.BLANK_LINE
                            + "    Please select a valid task number to unmark!\n" + Ui.BLANK_LINE);
                }
                Ui.unmark(numToUnmark, taskList);
                Storage.saveToFile(taskList);
                break;
            case("todo"):
                String todoString = input.substring(input.indexOf(' ')).trim();
                if (todoString.equals("")) {
                    throw new EmptyDescriptionException("\n" + Ui.BLANK_LINE
                            + "    The description of a todo cannot be empty.:-(\n" + Ui.BLANK_LINE);
                }
                Todo newTodo = new Todo(todoString);
                taskList.add(newTodo);
                Ui.todo(todoString, taskList);
                Storage.saveToFile(taskList);
                break;
            case("deadline"):
                if (!input.contains("/by")) {
                    throw new InvalidInputException("\n" + Ui.BLANK_LINE
                            + "    Please input the 'deadline' command in the following format:\n"
                            + "    deadline <description> /by <time>\n" + Ui.BLANK_LINE);
                }
                String[] deadlineString = input.substring(input.indexOf(' ')).split("/by");
                if (deadlineString.length < 2) {
                    throw new InvalidInputException("\n" + Ui.BLANK_LINE
                            + "    Please input the 'deadline' command in the following format:\n"
                            + "    deadline <description> /by <time>\n" + Ui.BLANK_LINE);
                }
                String deadlineName = deadlineString[0];
                if (deadlineName.equals(" ")) {
                    throw new EmptyDescriptionException("\n" + Ui.BLANK_LINE
                            + "    Deadline name description cannot be empty!\n" + Ui.BLANK_LINE);
                }
                String deadlineTime = deadlineString[1];
                if (deadlineTime.equals(" ")) {
                    throw new EmptyDescriptionException("\n" + Ui.BLANK_LINE
                            + "    Deadline time cannot be empty!\n" + Ui.BLANK_LINE);
                }
                Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
                taskList.add(newDeadline);
                Ui.deadline(deadlineName, deadlineTime, taskList);
                Storage.saveToFile(taskList);
                break;
            case("event"):
                if (!input.contains("/at")) {
                    throw new InvalidInputException("\n" + Ui.BLANK_LINE
                            + "    Please input the 'event' command in the following format:\n"
                            + "    event <description> /at <time>\n" + Ui.BLANK_LINE);
                }
                String[] eventString = input.substring(input.indexOf(' ')).split("/at");
                if (eventString.length < 2) {
                    throw new InvalidInputException("\n" + Ui.BLANK_LINE
                            + "    Please input the 'event' command in the following format:\n"
                            + "    event <description> /at <time>\n" + Ui.BLANK_LINE);
                }
                String eventName = eventString[0];
                if (eventName.equals(" ")) {
                    throw new EmptyDescriptionException("\n" + Ui.BLANK_LINE
                            + "    Event name description cannot be empty!\n" + Ui.BLANK_LINE);
                }
                String eventTime = eventString[1];
                if (eventTime.equals(" ")) {
                    throw new EmptyDescriptionException("\n" + Ui.BLANK_LINE
                            + "    Event time cannot be empty!\n" + Ui.BLANK_LINE);
                }
                Event newEvent = new Event(eventName, eventTime);
                taskList.add(newEvent);
                Ui.event(eventName, eventTime, taskList);
                Storage.saveToFile(taskList);
                break;
            case("delete"):
                int numToDelete = Integer.parseInt(array[1]);
                if (numToDelete > taskList.getTaskListSize() || numToDelete < 0) {
                    throw new InvalidInputException("\n" + Ui.BLANK_LINE
                            + "    Please select a valid task number to delete!\n" + Ui.BLANK_LINE);
                }
                if (numToDelete > taskList.getTaskListSize() || numToDelete < 0) {
                    throw new InvalidInputException("\n" + Ui.BLANK_LINE
                            + "    Please select a valid task number to delete!\n" + Ui.BLANK_LINE);
                }
                Ui.delete(numToDelete, taskList);
                Storage.saveToFile(taskList);
                break;
            default:
                throw new UnknownCommandException("\n" + Ui.BLANK_LINE
                        + "    I'm sorry, but I don't know what that means :-(\n" + Ui.BLANK_LINE);
        }
    }
}
