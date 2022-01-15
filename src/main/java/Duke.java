import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Enkel";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String frame = "_____________________________________________";
        System.out.println(frame + "\nHello! I\'m " + name + "\nWhat can I do for you?\n" + frame + "\n");

        Scanner sc = new Scanner(System.in);
        String command;
        List<String> tasks = new ArrayList<String>();
        while (true) {
            command = sc.nextLine();
            if (command.equals("bye")) {
                System.out.println(frame + "\nBye. Hope to see you again soon!\n" + frame + "\n");
                break;
            } else if (command.equals("list")) {
                System.out.println(frame);
                if (tasks.size() == 0) {
                    System.out.println("There are no tasks in your list~");
                }
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i+1) + ". " + tasks.get(i));
                }
                System.out.println(frame + "\n");
            } else {
                tasks.add(command);
                System.out.println(frame + "\nadded: " + command + "\n" + frame + "\n");
            }
        }
    }
}
