import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    private static TaskList al;
    public static void main(String[] args) {
        greet();
        al = new TaskList();
        Scanner sc = new Scanner(System.in);
        String userIn = "";
        String out;
        do {
            if (userIn.length() > 0) {
                out = processUserInput(userIn);
                speech(out);
            }
            userIn = sc.nextLine();
        } while (!userIn.equals("bye"));
        speech("Good bye! Hope to see you again!");
    }

    /**
     * Greets the user.
     */
    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("Tell me about your upcoming activities!");
        System.out.println("Input \"help\" for instructions.");
    }

    /**
     * Helper text on the special keywords to instruct Duke.
     *
     * @return instruction for user
     */
    private static String help() {
        return "\"list\": to display your activities.\n" +
                "\"bye\": to end our session.\n" +
                "\"mark [i]\" to mark the i-th task as done.\n" +
                "\"unmark [i]\" to unmark the i-th task as done.";
    }

    /**
     * Processes user's input and executes the instruction.
     *
     * @param userIn user's input
     * @return response string to user
     */
    private static String processUserInput(String userIn) {
        if (userIn.equals("list")) {
            return al.toString();
        } else if (userIn.equals("help")) {
            return help();
        } else if (Pattern.matches("^mark\\s\\d+", userIn)) {
            return "This activity is marked as done: \n"
                    + al.markDone(Integer.parseInt(userIn
                    .replaceAll("[^\\d.]", "")) - 1)
                    .toString();
        }else if (Pattern.matches("^unmark\\s\\d+", userIn)) {
            return "This activity is unmarked as done: \n" +
                    al.markUndone(Integer.parseInt(userIn
                    .replaceAll("[^\\d.]", "")) - 1)
                    .toString();
        } else if (Pattern.matches("^todo\\s(.*?)", userIn)) {
            return al.addToDo(userIn.replace("todo ", ""));
        } else if (Pattern.matches("^deadline\\s(.*s?)\\s/by\\s(.*s?)", userIn)) {
            String[] split = userIn
                    .replace("deadline ", "")
                    .split("\\s/by\\s", 2);
            return al.addDeadline(split[0], split[1]);
        } else if (Pattern.matches("^event\\s(.*s?)\\s/at\\s(.*s?)", userIn)) {
            String[] split = userIn
                    .replace("event ", "")
                    .split("\\s/at\\s", 2);
            return al.addEvent(split[0], split[1]);
        } else {
            return "Oops! Your instructions were unclear!";
        }
    }

    /**
     * Prints Duke's formatted speech.
     *
     * @param text Duke's speech
     */
    private static void speech(String text) {
        System.out.println("____________________________");
        System.out.println(text);
        System.out.println("____________________________");
    }
}
