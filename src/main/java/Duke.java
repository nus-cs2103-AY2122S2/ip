import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Returns a styled message with front and back styling
     *
     * @param msg the message to be styled
     * @return    the styled message
     */
    public static String formatMsg(String msg) {
        String startFormat = "####################\n";
        String endFormat = "####################";
        return startFormat + msg + endFormat;
    }

    /**
     * Returns a styled welcome message upon launch
     */
    public static void displayWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcomeMsg = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(formatMsg(welcomeMsg));
    }

    /**
     * Returns a styled exit message on exit
     */
    public static void displayExitMsg() {
        System.out.println(formatMsg("Bye. Hope to see you again soon!\n"));
    }

    /**
     * Runs Level 1 version of the app, Greet/Echo/Exit
     */
    public static void levelOne() {
        displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                displayExitMsg();
                return;
            } else {
                System.out.println(formatMsg(command));
            }
        }
    }

    /**
     * Returns string of stored data as an indexed list
     *
     * @param data arrayList of string data
     * @return     data as a list in a single string
     */
    public static String renderList(ArrayList<String> data) {
        String renderStr = "";

        for (int i = 0; i < data.size(); i++) {
            String dataStr = String.format("%d. ", i+1)
                    + data.get(i)
                    + " \n";
            renderStr += dataStr;
        }

        return renderStr;
    }

    /**
     * Returns string of stored data as an indexed list
     *
     * @param data arrayList of Task data
     * @return     data as a list in a single string
     */
    public static String renderTaskList(ArrayList<Task> data) {
        String renderStr = "";

        for (int i = 0; i < data.size(); i++) {
            String dataStr = String.format("%d. ", i+1)
                    + data.get(i)
                    + " \n";
            renderStr += dataStr;
        }

        return renderStr;
    }

    /**
     * Runs Level 2 version of the app, Mark as done
     */
    public static void levelTwo() {
        displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                displayExitMsg();
                return;
            }

            if (input.equals("list")) {
                System.out.println(formatMsg(renderList(data)));
            } else {
                data.add(input);
                String output = " added: " + input + "\n";
                System.out.println(formatMsg(output));
            }
        }
    }

    public static void displayMarkMsg(String task) {
        String markMsg = "Nice! I've marked this task as done:\n"
                + task + "\n";
        System.out.println(formatMsg(markMsg));
    }

    public static void displayUnmarkMsg(String task) {
        String unmarkMsg = "Nice! I've marked this task as NOT done:\n"
                + task + "\n";
        System.out.println(formatMsg(unmarkMsg));
    }

    /**
     * Runs Level 3 version of the app, Mark as done
     */
    public static void levelThree() {
        displayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> data = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                displayExitMsg();
                return;
            }

            if (input.equals("list")) {
                System.out.println(formatMsg(renderTaskList(data)));
                continue;
            }

            if (input.contains("mark")) {
                int itemIndex = Integer.parseInt(input.split(" ")[1]);

                if (itemIndex <= data.size()) {
                    Task selectedTask = data.get(itemIndex - 1);
                    if (input.contains("unmark")) {
                        selectedTask.markAsIncomplete();
                        displayUnmarkMsg(selectedTask.toString());
                    } else {
                        selectedTask.markAsComplete();
                        displayMarkMsg(selectedTask.toString());
                    }
                }
                continue;
            }

            Task newTask = new Task(input);
            data.add(newTask);
            String output = " added: " + input + "\n";
            System.out.println(formatMsg(output));
        }
    }

    public static void main(String[] args) {
        levelThree();
    }
}
