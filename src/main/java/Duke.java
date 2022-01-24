import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Duke {

    static Scanner scanner = new Scanner(System.in);
    static final String welcome_message = "Ello, my name is Ekud, " +
            "your personal task tracking bot.\n" +
            "Enter \"commands\" for list of commands that I can handle";


    public static void main(String[] args) throws DukeException, IOException {

        System.out.println(welcome_message);

        // Error handling
        try {
            InputOutputFunctions.printFileContents();
        } catch (FileNotFoundException e) {
            System.out.println("File not found\n" +
                    "Do you want Ekud to create the file for you? [y/n]");
            String input = scanner.nextLine();
            while (!input.equals("n")) {
                if (input.equals("y")) {
                    InputOutputFunctions.createNewFolderAndTextFile();
                    System.out.println("Very well I have created the file as you wish");
                    break;
                } else {
                    System.out.println("Please only input either \"y\" or \"n\"");
                }
                input = scanner.nextLine();
            }
            if (input.equals("n")) {
                System.out.print("Very well, Ekud bids farewell to you");
                System.exit(0);
            }
        }

        System.out.println(DukeFunctions.setTaskList(
                InputOutputFunctions.loadFileContents()));

        while (scanner.hasNextLine()) {

            String input = scanner.nextLine().trim();
            String[] input_split = input.split(" ");

            try {
                DukeFunctions.checkInputValidity(input);

                switch (input_split[0]) {
                case "commands":
                    DukeFunctions.getCommands();
                    break;
                case "delete":
                    int delete_position = Integer.parseInt(input_split[1]);
                    DukeFunctions.deleteTask(delete_position);
                    break;
                case "todo":
                    String todo_description = input.split("todo ", 2)[1];
                    DukeFunctions.addToList(new ToDo(todo_description));
                    break;
                case "deadline":
                    String deadline_description = input.split("deadline ", 2)[1].split("/by ")[0];
                    String deadline_date = input.split("deadline ", 2)[1].split("/by ")[1];
                    DukeFunctions.addToList(new Deadline(deadline_description, deadline_date));
                    break;
                case "event":
                    String event_description = input.split("event ", 2)[1].split("/at ")[0];
                    String event_date = input.split("event ", 2)[1].split("/at ")[1];
                    DukeFunctions.addToList(new Event(event_description, event_date));
                    break;
                case "mark":
                    DukeFunctions.markTask(Integer.parseInt(input_split[1]));
                    break;
                case "unmark":
                    DukeFunctions.unmarkTask(Integer.parseInt(input_split[1]));
                    break;
                case "list":
                    DukeFunctions.showTaskList();
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
