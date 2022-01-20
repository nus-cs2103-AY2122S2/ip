import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        // KattSystem.out System.out = new KattSystem.out(System.in, System.out);

        // String logo = " ____ _ \n"
        // + "| _ \\ _ _| | _____ \n"
        // + "| | | | | | | |/ / _ \\\n"
        // + "| |_| | |_| | < __/\n"
        // + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        
        String greeting = "Hello! I'm Kizer\nWhat can I do for you?";
        String bar = "____________________________________________________________";
        System.out.println(bar);
        System.out.println(greeting);
        System.out.println(bar);

        while (true) {
            // String input = io.getWord();
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(bar);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(bar);
                sc.close();
                // System.out.close();
                break;
            } else {
                System.out.println(bar);
                System.out.println(input);
                System.out.println(bar);
            }

        }

    }
}
