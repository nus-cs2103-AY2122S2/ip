import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {
    private static TaskList tl;
    public static void main(String[] args) {
        greet();
        tl = new TaskList();
        Scanner sc = new Scanner(System.in);
        String userIn = "";
        String out;
        do {
            if (userIn.length() > 0) {
                try {
                    out = processUserInput(userIn);
                    speech(out);
                } catch (DukeException ex) {
                    speech(ex.getMessage());
                }
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
     * @throws DukeException if user input is invalid
     */
    private static String processUserInput(String userIn) throws DukeException {
        if (userIn.equals("list")) {
            return tl.toString();
        } else if (userIn.equals("help")) {
            return help();
        } else if (Pattern.matches("^mark\\s\\d+", userIn)) {
            return "This activity is marked as done: \n"
                    + tl.markDone(Integer.parseInt(userIn
                    .replaceAll("[^\\d.]", "")) - 1)
                    .toString();
        } else if (Pattern.matches("^unmark\\s\\d+", userIn)) {
            return "This activity is unmarked as done: \n" +
                    tl.markUndone(Integer.parseInt(userIn
                    .replaceAll("[^\\d.]", "")) - 1)
                    .toString();
        } else if (Pattern.matches("^delete\\s\\d+", userIn)) {
            return tl.delete(Integer.parseInt(userIn
                    .replaceAll("[^\\d.]", "")) - 1);
        } else if (Pattern.matches("^todo\\s(.*?)", userIn)) {
            String s = userIn.replace("todo ", "").trim();
            if (s.length() == 0)
                throw new DukeException("Todo task requires a task name!");
            else return tl.addToDo(s);
        } else if (Pattern.matches("^deadline\\s(.*s?)\\s/by\\s(.*s?)", userIn)) {
            String[] split = userIn
                    .replace("deadline ", "")
                    .split("\\s/by\\s", 2);
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            if (split[0].length() == 0 && split[1].length() == 0)
                throw new DukeException("Deadline task requires a task name and a date!");
            else if (split[0].length() == 0)
                throw new DukeException("Deadline task requires a task name!");
            else if (split[1].length() == 0)
                throw new DukeException("Deadline task requires a date!");
            else return tl.addDeadline(split[0], split[1]);
        } else if (Pattern.matches("^event\\s(.*s?)\\s/at\\s(.*s?)", userIn)) {
            String[] split = userIn
                    .replace("event ", "")
                    .split("\\s/at\\s", 2);
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            if (split[0].length() == 0 && split[1].length() == 0)
                throw new DukeException("Event task requires a task name and a date!");
            else if (split[0].length() == 0)
                throw new DukeException("Event task requires a task name!");
            else if (split[1].length() == 0)
                throw new DukeException("Event task requires a date!");
            else return tl.addEvent(split[0], split[1]);
        } else {
            throw new DukeException("Oops! Your instructions were unclear!");
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
