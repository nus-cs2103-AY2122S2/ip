import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /**
     * Returns a styled message with front and back styling
     *
     * @param msg the message to be styled
     * @return    the styled message
     */
    public static String FormatMsg(String msg) {
        String startFormat = "####################\n";
        String endFormat = "####################";
        return startFormat + msg + endFormat;
    }

    /**
     * Returns a styled welcome message upon launch
     */
    public static void DisplayWelcomeMsg() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String welcomeMsg = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(FormatMsg(welcomeMsg));
    }

    /**
     * Returns a styled exit message on exit
     */
    public static void DisplayExitMsg() {
        System.out.println(FormatMsg("Bye. Hope to see you again soon!\n"));
    }

    /**
     * Runs Level 1 version of the app, Greet/Echo/Exit
     */
    public static void LevelOne() {
        DisplayWelcomeMsg();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.nextLine();
            if (command.equals("bye")) {
                DisplayExitMsg();
                return;
            } else {
                System.out.println(FormatMsg(command));
            }
        }
    }

    /**
     * Returns string of stored data as an indexed list
     *
     * @param data arrayList of string data
     * @return     data as a list in a single string
     */
    public static String RenderList(ArrayList<String> data) {
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
     * Runs Level 2 version of the app, Add/List
     */
    public static void LevelTwo() {
        DisplayWelcomeMsg();
        Scanner sc = new Scanner(System.in);
        ArrayList<String> data = new ArrayList<>();
        int size = 0;

        for (int i = 0; i < 100; i++) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                DisplayExitMsg();
                return;
            }

            if (input.equals("list")) {
                System.out.println(FormatMsg(RenderList(data)));
            } else {
                data.add(input);
                String output = " added: " + input + "\n";
                System.out.println(FormatMsg(output));
            }
        }
    }

    public static void main(String[] args) {
        LevelTwo();
    }
}
