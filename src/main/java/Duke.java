import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
//    private ArrayList<String> tasks;
//    public Duke() {
//        String start =
//                "________________________________\n"
//                        + "Hello! I am Duke.\n"
//                        + "Your Personal Assistant.\n"
//                        + "What can I do for you?\n"
//                        + "________________________________";
//        this.tasks = new ArrayList<>();
//        System.out.println(start);
//    }
//
//    public static void add(String task) {
//        tasks.add(task);
//        System.out.println("________________________________");
//        System.out.println("Added to your tasks: " + task);
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Control control = new Control();
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                control.bye();
                break;
            }else if (command.equals("list")) {
                control.list();
            } else {
                control.add(command);
            }
            System.out.println("________________________________");
        }
    }
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
}
