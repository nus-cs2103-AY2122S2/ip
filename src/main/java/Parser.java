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
            default:
                Printer.add(input);
                taskList.add(new Task(input));
        }
    }
}
