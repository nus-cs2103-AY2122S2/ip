import java.util.Scanner;

public class Duke {
    private static final String INPUT_NAME = "You: ";
    private static final String OUTPUT_NAME = "Duke: ";

    public static void main(String[] args) {
        start();
        Scanner sc = new Scanner(System.in);
        String input;
        Notebook notebook = new Notebook();

        do {
            System.out.print(INPUT_NAME);
            input = sc.nextLine();
            String output = OUTPUT_NAME;

            // main
            if (input.equals("list")) {
                output += "\n" + notebook;
            } else if (input.equals("bye")) {
                output += "don't leave me don't leave me.";
            } else {
                notebook.addTask(input);
                output += "added " + input;
            }
            System.out.println(output);
        } while (!input.equals("bye"));
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
