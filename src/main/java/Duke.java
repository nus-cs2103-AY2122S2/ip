import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);

        printDukeResponse("Sup! Name's Duke \nHow can I help you today?");

        boolean isRunning = true;
        while (isRunning) {
            String userResponse = scanner.nextLine();

            if (userResponse.equals("bye")) { // quit application
                printDukeResponse("See ya!");
                isRunning = false;
                continue;
            }

            printDukeResponse(userResponse); // just copy user response
        }
    }

    /* Print in the Duke response format */
    public static void printDukeResponse(String response) {
        System.out.println(
                "\n--------------------------------------------------------------------------------------------");
        System.out.println("Duke Speaking:\n");
        System.out.println(response);
        System.out.println(
                "--------------------------------------------------------------------------------------------\n");
    }
}
