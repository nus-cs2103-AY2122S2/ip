import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Scanner sc = new Scanner(System.in);
        String[] lst = new String[100];
        int currIndex = 0;
        System.out.println("____________________________________________________________\nHello! I'm Duke\nWhat can I do for you?\n____________________________________________________________");
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i=0; i < currIndex; i++ ) {
                    System.out.println(String.valueOf(i+1) + ". " + lst[i]);
                }
                System.out.println("____________________________________________________________\n");
            }
            else {
                System.out.println("____________________________________________________________\n" +
                        "added: " + input + "\n" +
                        "____________________________________________________________");
                lst[currIndex] = input;
                currIndex += 1;
            }
            input = sc.nextLine();
        }
        System.out.println("____________________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________________");
    }
}
