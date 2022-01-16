import java.util.Scanner;

public class Duke {
    private static final String INPUT_NAME = "You: ";
    private static final String OUTPUT_NAME = "Duke: ";

    public static void main(String[] args) {
        start();
        Scanner sc = new Scanner(System.in);
        String command;
        do {
            System.out.print(INPUT_NAME);
            command = sc.nextLine();

            // main
            String output = command.equals("bye")
                    ? "Why are you leaving me? :(" : command;
            System.out.println(output);
        } while (!command.equals("bye"));
        sc.close();
    }

    public static void start() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String msg = "Hello! I am Duke. \n"
                + "Your wish is my command.\n\n";
        System.out.print(logo + msg);
    }
}
