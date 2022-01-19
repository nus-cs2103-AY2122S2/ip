import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private boolean isRunning;
    private ArrayList<Task> userTexts;

    public Duke() {
        this.userTexts = new ArrayList<Task>();
        this.isRunning = true;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.startGreeting();
        duke.runDuke();
    }

    /* Initial greeting for Duke */
    public void startGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printDukeResponse("Sup! Name's Duke \nHow can I help you today?");
    }

    /* Run Duke default behavior */
    public void runDuke() {
        Scanner sc = new Scanner(System.in);

        while (this.isRunning) {
            String userResponse = sc.nextLine();

            if (commandsParsed(userResponse)) {
                continue;
            }

            this.userTexts.add(new Task(userResponse));
            printDukeResponse("added: " + userResponse);
        }

        sc.close();
    }

    /* Return true if command is successfully parsed */
    public boolean commandsParsed(String commands) {
        if (commands.equals("bye")) { // quit application
            printDukeResponse("See ya!");
            this.isRunning = false;
            return true;
        }

        if (commands.equals("list")) {
            printDukeResponse(getListStr(userTexts)); // just copy user response
            return true;
        }

        return false;
    }

    /* Print in the Duke response format */
    public void printDukeResponse(String response) {
        System.out.println(
                "\n--------------------------------------------------------------------------------------------");
        System.out.println("Duke Speaking:\n");
        System.out.println(response);
        System.out.println(
                "--------------------------------------------------------------------------------------------\n");
    }

    public String getListStr(ArrayList<? extends Object> list) {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < list.size(); ++i) {
            sb.append(String.valueOf(i + 1)).append(". ").append(list.get(i).toString()).append("\n");
        }

        return sb.toString();
    }
}
