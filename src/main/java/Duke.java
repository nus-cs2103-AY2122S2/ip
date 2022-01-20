import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        new Duke().run();
    }

    public void run() {
        greet();

        Scanner sc = new Scanner(System.in);
        String commandLine = "";

        while (!commandLine.equals("bye")) {
            System.out.print("Enter command: \t");
            commandLine = sc.nextLine().trim();

            if ("bye".equals(commandLine)) {
                exit();
            } else {
                echo("\t" + commandLine);
            }
        }
    }

    public void greet() {
        String logo = "\t" + " ____        _        " + System.lineSeparator()
                + "\t" + "|  _ \\ _   _| | _____" + System.lineSeparator()
                + "\t" + "| | | | | | | |/ / _" + System.lineSeparator()
                + "\t" + "| |_| | |_| |   <  __/" + System.lineSeparator()
                + "\t" + "|____/ \\__,_|_|\\_\\___|" + System.lineSeparator();

        String welcomeMessage = "\t" + "Hello! I'm Duke, your Personal Assistant ChatBot."
                /* + System.lineSeparator()
                + logo */
                + System.lineSeparator()
                + "\t" + "What can I do for you?";

        printResponse(welcomeMessage);
    }

    public void exit() {
        String exitMessage = "\t" + "Bye. Hope to see you again soon!";
        printResponse(exitMessage);
    }

    public void echo(String message) {
        printResponse(message);
    }

    public void printResponse(String message) {
        printLine();
        System.out.println(message);
        printLine();
    }

    public void printLine() {
        String horizontalLine = "\t" + "________________________________________________________";
        System.out.println(horizontalLine);
    }
}
