import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final SimpleDateFormat taskFormat = new SimpleDateFormat("MMM dd yyyy");
    private static final SimpleDateFormat savedFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final ExitCommand EXIT_COMMAND = new ExitCommand();
    private  static final ListCommand LIST = new ListCommand();
    private  static final InvalidCommand INVALID_COMMAND = new InvalidCommand();

    public Parser(){}

    public static Task parseFile(String savedTask) {
        String[] splitStr = savedTask.split("\\|");
        Task parsedTask = null;
        int status = Integer.parseInt(splitStr[1]);
        String activity = splitStr[2];
        String timeOrPlace = null;
        switch(splitStr[0]) {
            case "T":
                parsedTask = new Todo(activity);
                break;
            case "E":
                timeOrPlace = splitStr[3];
                parsedTask = new Event(activity, timeOrPlace);
                break;
            case "D":
                String tmp = splitStr[3];
                try {
                    timeOrPlace = savedFormat.format(taskFormat.parse(tmp));
                } catch (ParseException e) {
                    System.exit(1);
                }
                parsedTask = new Deadline(activity, timeOrPlace);
                break;
            default:

        }
        parsedTask.updateStatus(status);
        return parsedTask;
    }

    public Command parseCommand(String command) {
        String[] splitStr = command.split(" ", 2);
        Command parsed = null;
        Task task = null;
        int index = -1;
        switch (splitStr[0]) {
            case "bye":
                parsed = EXIT_COMMAND;
                break;
            case "list":
                parsed = LIST;
                break;
            case "mark":
                try {
                    index = Integer.parseInt(splitStr[1]);
                    index--;
                } catch (NumberFormatException e) {
                    System.exit(1);
                }
                parsed = new MarkCommand(index);
                break;
            case "unmark":
                try {
                    index = Integer.parseInt(splitStr[1]);
                    index--;
                } catch (NumberFormatException e) {
                    System.exit(1);
                }
                parsed = new UnmarkCommand(index);
                break;
            case "todo":
                task = new Todo(splitStr[1]);
                parsed = new AddCommand(task);
                break;
            case "deadline":
                String[] tempStr = splitStr[1].split(" /by ");
                task = null;
                try {
                    task = new Deadline(tempStr[0], tempStr[1]);
                } catch (DateTimeParseException e) {
                    System.out.println("Invalid date format");
                }
                parsed = new AddCommand(task);
                break;
            case "event":
                try {
                    String[] tempStr2 = splitStr[1].split(" /at ");
                    task = new Event(tempStr2[0], tempStr2[1]);
                    parsed = new AddCommand(task);
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid event command format.");
                }
                break;
            case "delete":
                try {
                    index = Integer.parseInt(splitStr[1]);
                    index--;
                    parsed = new DeleteCommand(index);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid deletion index.");
                }
                break;
            default:
                parsed = INVALID_COMMAND;
                break;
        }
        return parsed;
    }

}
