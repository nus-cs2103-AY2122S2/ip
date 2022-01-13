import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int itemNumber = 1; // start from 1
        String cmd;
        List<String> toDoList = new ArrayList<String>();
        String tabbedLine = "\t----------------------------------------------";
        String welcome = tabbedLine + "\n\tHello from Burp\n\tWhat can I do for you?\n" + tabbedLine;
        System.out.println(welcome);
        while (!(cmd = br.readLine()).equals("bye")) {
            switch (cmd) {
                case "list":
                    // then list out the items
                    System.out.println(tabbedLine);
                    toDoList.forEach(item -> System.out.println(item));
                    System.out.println(tabbedLine);
                    break;
                default:
                    toDoList.add(itemNumber + ": " + cmd);
                    itemNumber++;
                    System.out.println(tabbedLine + "\n\tadded: " + cmd + "\n" + tabbedLine);
                    break;
            }
        }
        System.out.println(tabbedLine + "\n\tBye. Hope to see you again soon!\n" + tabbedLine);
    }
}
