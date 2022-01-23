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
                Printer.mark(numToMark, taskList);
                break;
            case("unmark"):
                int numToUnmark = Integer.parseInt(array[1]);
                Printer.unmark(numToUnmark, taskList);
                break;
            case("todo"):
                String todoString = input.substring(input.indexOf(' '));
                Todo newTodo = new Todo(todoString);
                taskList.add(newTodo);
                Printer.todo(todoString, taskList);
                break;
            case("deadline"):
                String[] deadlineString = input.substring(input.indexOf(' ')).split("/by");
                String deadlineName = deadlineString[0];
                String deadlineTime = deadlineString[1];
                Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
                taskList.add(newDeadline);
                Printer.deadline(deadlineName, deadlineTime, taskList);
                break;
            case("event"):
                String[] eventString = input.substring(input.indexOf(' ')).split("/at");
                String eventName = eventString[0];
                String eventTime = eventString[1];
                Event newEvent = new Event(eventName, eventTime);
                taskList.add(newEvent);
                Printer.event(eventName, eventTime, taskList);
                break;
            default:
                Printer.add(input);
                taskList.add(new Task(input));
        }
    }
}
