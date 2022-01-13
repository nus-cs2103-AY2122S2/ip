import java.io.BufferedReader;
import java.util.*;
import java.io.IOException;
import java.io.InputStreamReader;

public class Duke {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String cmd;
        List<Task> toDoList = new ArrayList<>();
        String tabbedLine = "\t----------------------------------------------";
        String welcome = tabbedLine + "\n\tHello from Burp\n\tWhat can I do for you?\n" + tabbedLine;
        System.out.println(welcome);
        while (!(cmd = br.readLine()).equals("bye")) {
            String[] cmd_split = cmd.split(" ");
            switch (cmd_split[0]) {
                case "list":
                    // then list out the items
                    System.out.println(tabbedLine);
                    for (int i = 0; i < toDoList.size(); i++) {
                        System.out.println(i+1 + "." + toDoList.get(i).toString());
                    }
                    System.out.println(tabbedLine);
                    break;
                case "mark":
                    int numberToMark = Integer.parseInt(cmd_split[1])-1; // convert to 0-based indexing
                    if (toDoList.size() == 0 || numberToMark >= toDoList.size()) {
                        System.out.println("Error. Task does not exist");
                        break;
                    }
                    Task taskToMark = toDoList.get(numberToMark);
                    taskToMark.mark();
                    System.out.println(tabbedLine + "\nNice! I've marked this task as done:");
                    System.out.println(taskToMark);
                    System.out.println(tabbedLine);
                    break;
                case "unmark":
                    int numberToUnmark = Integer.parseInt(cmd_split[1])-1; // convert to 0-based indexing
                    if (toDoList.size() == 0 || numberToUnmark >= toDoList.size()) {
                        System.out.println("Error. Task does not exist");
                        break;
                    }
                    Task taskToUnmark = toDoList.get(numberToUnmark);
                    taskToUnmark.unmark();
                    System.out.println(tabbedLine + "\nOK, I've marked this task as not done yet:");
                    System.out.println(taskToUnmark);
                    System.out.println(tabbedLine);
                    break;
                default:
                    // new Task(String cmd, boolean markStatus)
                    toDoList.add(new Task(cmd, false));
                    System.out.println(tabbedLine + "\n\tadded: " + cmd + "\n" + tabbedLine);
                    break;
            }
        }
        System.out.println(tabbedLine + "\n\tBye. Hope to see you again soon!\n" + tabbedLine);
    }
}
