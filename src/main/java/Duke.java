import java.util.*;

public class Duke {

    static Scanner scanner = new Scanner(System.in);
    static final String welcome_message = "Ello, my name is Ekud, your personal task tracking bot.\n" +
            "Enter \"commands\" for list of commands that I can handle";

    public static void main(String[] args) throws DukeException {

        System.out.println(welcome_message);

        while (scanner.hasNextLine()) {

            String input = scanner.nextLine().trim();
            String[] input_split = input.split(" ");

            try {
                TaskFunctions.checkInputValidity(input);

                switch (input_split[0]) {
                case "commands":
                    TaskFunctions.getCommands();
                    break;
                case "delete":
                    int delete_position = Integer.parseInt(input_split[1]);
                    TaskFunctions.deleteTask(delete_position);
                    break;
                case "todo":
                    String todo_description = input.split("todo ", 2)[1];
                    TaskFunctions.addToList(new ToDo(todo_description));
                    break;
                case "deadline":
                    String deadline_description = input.split("deadline ", 2)[1].split("/by ")[0];
                    String deadline_date = input.split("deadline ", 2)[1].split("/by ")[1];
                    TaskFunctions.addToList(new Deadline(deadline_description, deadline_date));
                    break;
                case "event":
                    String event_description = input.split("event ", 2)[1].split("/at ")[0];
                    String event_date = input.split("event ", 2)[1].split("/at ")[1];
                    TaskFunctions.addToList(new Deadline(event_description, event_date));
                    break;
                case "mark":
                    TaskFunctions.markTask(Integer.parseInt(input_split[1]));
                    break;
                case "unmark":
                    TaskFunctions.unmarkTask(Integer.parseInt(input_split[1]));
                    break;
                case "list":
                    TaskFunctions.showTaskList();
                    break;
                case "bye":
                    System.out.println("Goodbye sir" +
                            "\nQuote of the day: Success starts with SU!");
                    System.exit(0);
                default:
                    DukeException dukeException = new DukeException("I'm so very sorry," +
                            " please make sure you enter a valid Ekud command");
                    System.err.println(dukeException);
                }
            } catch (DukeException err) {
                System.err.println(err);
            }
        }
    }
}
