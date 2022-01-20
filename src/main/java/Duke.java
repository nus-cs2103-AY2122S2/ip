import java.util.Scanner;

public class Duke {
    public Duke() {
        String start =
                "________________________________\n"
                        + "Hello! I am Duke.\n"
                        + "Your Personal Assistant.\n"
                        + "What can I do for you?\n"
                        + "________________________________";
        System.out.println(start);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                String bye = "GoodBye! I hope to see you again!";
                System.out.println(bye);
                break;
            } else {
                System.out.println(command);
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
