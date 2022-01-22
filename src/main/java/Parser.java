import java.util.ArrayList;

public class Parser {

    public static void parse(String input, TaskList taskList) {
        switch(input) {
            case("list"):
                Printer.list(taskList);
                break;
            default:
                Printer.add(input);
                taskList.add(input);
        }
    }
}
