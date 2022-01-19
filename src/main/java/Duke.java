import java.util.Scanner;

public class Duke {
    static Scanner sc = new Scanner(System.in);
    static String line = "\n_______________________^_^__________________________________\n";

    public static void echo() {
        String input;
        while(true) {
            input = sc.next();
            if (input.equals("bye")) {
                System.out.println(line + "Bye. Hope to see you again soon!\n" + line);
                break;
            } else {
                System.out.println(line + input + line);
            }
        }
    }

    public static void main(String[] args) {



        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(line + "Hello! I'm Duke\n" +
                "What can I do for you?\n" + line);

        Duke.echo();//level-1


    }

}
