import java.util.ArrayList;

public class Parser {

    public static void parse(String input, TaskList taskList) {
        String[] array = input.split(" ");
        String execute = array[0];
        switch(execute) {
            case("list"):
                Printer.list(taskList);
                break;
            case("mark"):
                int numToMark = Integer.parseInt(array[1]);
                if (numToMark > taskList.getTaskListSize() || numToMark < 1) {
                    throw new InvalidInputException("\n" + Printer.BLANK_LINE
                        + "    Please select a valid task number to mark!\n" + Printer.BLANK_LINE);
                }
                Printer.mark(numToMark, taskList);
                break;
            case("unmark"):
                int numToUnmark = Integer.parseInt(array[1]);
                if (numToUnmark > taskList.getTaskListSize() || numToUnmark < 1) {
                    throw new InvalidInputException("\n" + Printer.BLANK_LINE
                            + "    Please select a valid task number to unmark!\n" + Printer.BLANK_LINE);
                }
                Printer.unmark(numToUnmark, taskList);
                break;
            case("todo"):
                String todoString = input.substring(input.indexOf(' ')).trim();
                if (todoString.equals("")) {
                    throw new EmptyDescriptionException("\n" + Printer.BLANK_LINE
                            + "    The description of a todo cannot be empty.:-(\n" + Printer.BLANK_LINE);
                }
                Todo newTodo = new Todo(todoString);
                taskList.add(newTodo);
                Printer.todo(todoString, taskList);
                break;
            case("deadline"):
                if (!input.contains("/by")) {
                    throw new InvalidInputException("\n" + Printer.BLANK_LINE
                            + "    Please input the 'deadline' command in the following format:\n"
                            + "    deadline <description> /by <time>\n" + Printer.BLANK_LINE);
                }
                String[] deadlineString = input.substring(input.indexOf(' ')).split("/by");
                if (deadlineString.length < 2) {
                    throw new InvalidInputException("\n" + Printer.BLANK_LINE
                            + "    Please input the 'deadline' command in the following format:\n"
                            + "    deadline <description> /by <time>\n" + Printer.BLANK_LINE);
                }
                String deadlineName = deadlineString[0];
                if (deadlineName.equals(" ")) {
                    throw new EmptyDescriptionException("\n" + Printer.BLANK_LINE
                            + "    Deadline name description cannot be empty!\n" + Printer.BLANK_LINE);
                }
                String deadlineTime = deadlineString[1];
                if (deadlineTime.equals(" ")) {
                    throw new EmptyDescriptionException("\n" + Printer.BLANK_LINE
                            + "    Deadline time cannot be empty!\n" + Printer.BLANK_LINE);
                }
                Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
                taskList.add(newDeadline);
                Printer.deadline(deadlineName, deadlineTime, taskList);
                break;
            case("event"):
                if (!input.contains("/at")) {
                    throw new InvalidInputException("\n" + Printer.BLANK_LINE
                            + "    Please input the 'event' command in the following format:\n"
                            + "    event <description> /by <time>\n" + Printer.BLANK_LINE);
                }
                String[] eventString = input.substring(input.indexOf(' ')).split("/at");
                if (eventString.length < 2) {
                    throw new InvalidInputException("\n" + Printer.BLANK_LINE
                            + "    Please input the 'event' command in the following format:\n"
                            + "    event <description> /by <time>\n" + Printer.BLANK_LINE);
                }
                String eventName = eventString[0];
                if (eventName.equals(" ")) {
                    throw new EmptyDescriptionException("\n" + Printer.BLANK_LINE
                            + "    Event name description cannot be empty!\n" + Printer.BLANK_LINE);
                }
                String eventTime = eventString[1];
                if (eventTime.equals(" ")) {
                    throw new EmptyDescriptionException("\n" + Printer.BLANK_LINE
                            + "    Event time cannot be empty!\n" + Printer.BLANK_LINE);
                }
                Event newEvent = new Event(eventName, eventTime);
                taskList.add(newEvent);
                Printer.event(eventName, eventTime, taskList);
                break;
            case("delete"):
                int numToDelete = Integer.parseInt(array[1]);
                if (numToDelete > taskList.getTaskListSize() || numToDelete < 0) {
                    throw new InvalidInputException("\n" + Printer.BLANK_LINE
                            + "    Please select a valid task number to delete!\n" + Printer.BLANK_LINE);
                }
                Printer.delete(numToDelete, taskList);
                break;
            default:
                throw new UnknownCommandException("\n" + Printer.BLANK_LINE
                        + "    I'm sorry, but I don't know what that means :-(\n" + Printer.BLANK_LINE);
        }
    }
}
