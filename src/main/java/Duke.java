import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Duke duke = new Duke();
        duke.echo(logo + "\nHello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String input = scanner.nextLine();

            duke.echo(input);

            if (input.equals("bye")) {
                break;
            }
        }
    }

    public void echo(String userInput) {
        String hLine = "____________________________________________________________\n";

        if (userInput.equals("bye")) {
            System.out.println(hLine + "Bye. Hope to see you again soon!\n" + hLine);
        } else {
            System.out.println(hLine + userInput + "\n" + hLine);
        }
    }
}
