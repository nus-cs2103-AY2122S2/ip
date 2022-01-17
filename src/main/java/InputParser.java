import java.util.ArrayList;

public class InputParser {
    public int run(String ss, Printer p, ArrayList<Task> arr) {
        String[] args = ss.split("\\s+");
        String action = args[0];
        if(action.equals("bye")) {
            p.print(" Bye. Hope to see you again soon!");
            return -1;
        } else if (action.equals("list")) {
            p.print(arr);
        } else if (action.equals("mark")) {

        } else if (action.equals("unmark")) {

        } else {
            arr.add(new Task(ss));
            p.print(String.format("added: %s", ss));
        }
        return 0;
    }
}
