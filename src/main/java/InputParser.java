import java.util.ArrayList;

public class InputParser {
    public int run(String ss, Printer p, ArrayList<String> arr) {
        if(ss.equals("bye")) {
            p.print(" Bye. Hope to see you again soon!");
            return -1;
        } else if (ss.equals("list")) {
            p.print(arr);
        } else {
            arr.add(ss);
            p.print(String.format("added: %s", ss));
        }
        return 0;
    }
}
