import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String line = "_______________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = line + "\nHello! I am YQ\n" + "What can I do for you?\n" + logo + line;
        System.out.println(intro);

        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(line + "\n" + input + "\n" + line);
            input = sc.nextLine();
        }
        System.out.println(line + "\n" + "Bye. Hope to see you again soon!" + "\n" + line);
    }
}
