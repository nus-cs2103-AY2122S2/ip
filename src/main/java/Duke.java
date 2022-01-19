import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Scanner scanner = new Scanner(System.in);
        ArrayList<String> userTexts = new ArrayList<String>();

        printDukeResponse("Sup! Name's Duke \nHow can I help you today?");

        boolean isRunning = true;
        while (isRunning) {
            String userResponse = scanner.nextLine();

            if (userResponse.equals("bye")) { // quit application
                printDukeResponse("See ya!");
                isRunning = false;
                continue;
            } else if (userResponse.equals("list")) {
                printDukeResponse(getListStr(userTexts)); // just copy user response
                continue;
            }

            userTexts.add(userResponse);
            printDukeResponse("added: " + userResponse);
        }

        scanner.close();
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

    public static String getListStr(ArrayList<? extends Object> list) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < list.size(); ++i) {
            sb.append(String.valueOf(i + 1)).append(". ").append(list.get(i).toString()).append("\n");
        }

        return sb.toString();
    }
}
