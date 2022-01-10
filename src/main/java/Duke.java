import java.util.Scanner;

public class Duke {
    public static void greetings() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();

        while(!input.toLowerCase().equals("bye")) {
            System.out.println(input);
            input = scanner.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = "\n" +
                "   ____                  _                           \n" +
                "  / ___|   ___    _ __  | |_    __ _   _ __     __ _ \n" +
                " | |      / _ \\  | '__| | __|  / _` | | '_ \\   / _` |\n" +
                " | |___  | (_) | | |    | |_  | (_| | | | | | | (_| |\n" +
                "  \\____|  \\___/  |_|     \\__|  \\__,_| |_| |_|  \\__,_|\n" +
                "                                                     \n";
//                " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greetings();
    }
}
