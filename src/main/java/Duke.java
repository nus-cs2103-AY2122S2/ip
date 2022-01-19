import java.util.Scanner;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Duke {
    private boolean isRunning;
    private ArrayList<Task> userTexts;

    public static void main(String[] args) {
        Duke duke = new Duke();

        duke.startGreeting();
        duke.runDuke();
    }

    public Duke() {
        this.userTexts = new ArrayList<Task>();
        this.isRunning = true;
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
        StringTokenizer st = new StringTokenizer(commands, " ");
        String firstCommand = st.nextToken();

        // quit application
        if (firstCommand.equals("bye")) {
            printDukeResponse("See ya!");
            this.isRunning = false;
            return true;
        }

        // print task list
        if (firstCommand.equals("list")) {
            printDukeResponse(getListStr(userTexts));
            return true;
        }

        // mark task
        if (firstCommand.equals("mark") || firstCommand.equals("unmark")) {
            boolean markTask = firstCommand.equals("mark");
            int taskIndex = Integer.parseInt(st.nextToken()) - 1;

            if (taskIndex >= userTexts.size()) {
                printDukeResponse("Yo yo yo, this task don't exist.");
                return true;
            }

            Task task = userTexts.get(taskIndex);
            task.setIsDone(markTask);
            String cmdDescription = markTask ? "Nice I've marked this task as done: \n"
                    : "Alright, I've marked the task as not completed yet: \n ";

            printDukeResponse(cmdDescription + task.toString());
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
