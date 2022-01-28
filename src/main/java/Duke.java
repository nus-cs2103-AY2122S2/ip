import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, TODO, DEADLINE, EVENT, BANAN
    }

    public static void main(String[] args) {
        // Boot message
        String logo = "\n" +
                ".        :       ...     :::.    :::. :::  .   .,::::::\n" +
                ";;,.    ;;;   .;;;;;;;.  `;;;;,  `;;; ;;; .;;,.;;;;''''\n" +
                "[[[[, ,[[[[, ,[[     \\[[,  [[[[[. '[[ [[[[[/'   [[cccc\n" +
                "$$$$$$$$\"$$$ $$$,     $$$  $$$ \"Y$c$$_$$$$,     $$\"\"\"\"\n" +
                "888 Y88\" 888o\"888,_ _,88P  888    Y88\"888\"88o,  888oo,__\n" +
                "MMM  M'  \"MMM  \"YMMMMMP\"   MMM     YM MMM \"MMP\" \"\"\"\"YUMMM\n";
        String intro = "I MONKE.\n"
                + "WHAT WANT?\n";
        System.out.println(wrap(logo + "\n" + intro));

        // Setup scanner for user input
        Scanner sc = new Scanner(System.in);

        // Store items in agenda
        List<Task> tasks = new ArrayList<>();

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                String[] breakdown = input.split(" ", 2);
                Command command;
                try {
                    command = Command.valueOf(breakdown[0].toUpperCase());
                } catch (IllegalArgumentException e) {
                    throw new DukeException("INVALID COMMAND");
                }
                switch (command) {
                case BYE: {
                    break;
                }
                case LIST: {
                    StringBuilder result = new StringBuilder();
                    for (int i = 0; i < tasks.size(); i++) {
                        result.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
                    }
                    System.out.println(wrap("YOUR TASKS:\n" + result));
                    continue;
                }
                case MARK:
                case UNMARK:
                case DELETE: {
                    int index;
                    try {
                        index = Integer.parseInt(breakdown[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new DukeException("INVALID INDEX VALUE");
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("INVALID INDEX FORMAT");
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("TOO FEW ARGUMENTS SUPPLIED");
                    }
                    Task task = tasks.get(index);
                    if (command.equals(Command.MARK)) {
                        task.markAsDone();
                        System.out.println(wrap("TASK DONE:\n" + task + "\n"));
                    } else if (command.equals(Command.UNMARK)) {
                        task.markAsUndone();
                        System.out.println(wrap("TASK UNDONE:\n" + task + "\n"));
                    } else {
                        tasks.remove(index);
                        System.out.println(wrap("TASK REMOVED:\n"
                                + task + "\n"
                                + tasks.size() + " TASK(S) NOW.\n"));
                    }
                    continue;
                }
                case TODO:
                case DEADLINE:
                case EVENT: {
                    String options;
                    try {
                        options = breakdown[1];
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("TOO FEW ARGUMENTS SUPPLIED");
                    }
                    Task task;
                    if (command.equals(Command.TODO)) {
                        task = new Todo(options);
                    } else {
                        boolean isDeadline = command.equals(Command.DEADLINE);

                        // deconstruct command string
                        String[] splitCommand = options.split(isDeadline ? " /by " : " /at ");
                        if (splitCommand.length < 2) {
                            throw new DukeException("NO TIME SUPPLIED");
                        }

                        // retrieve description of task
                        String description = splitCommand[0];

                        // retrieve associated time of task
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                        LocalDateTime time;
                        try {
                            time = LocalDateTime.parse(splitCommand[1], formatter);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("INVALID TIME FORMAT (dd-MM-yyyy HH:mm)");
                        }

                        // instantiate deadline or event task accordingly
                        task = isDeadline
                                ? new Deadline(description, time)
                                : new Event(description, time);
                    }
                    tasks.add(task);

                    System.out.println(wrap("TASK ADDED:\n"
                            + task + "\n"
                            + tasks.size() + " TASK(S) NOW.\n"));
                    continue;
                }
                case BANAN:
                    System.out.println(wrap("OOH OOH AH AH!!! *screeches in delight*\n"));
                    continue;
                default:
                    throw new DukeException("INVALID COMMAND");
                }
                break;
            } catch (DukeException e) {
                e.printStackTrace();
            }
        }

        System.out.println(wrap("BYE!!!\n"));
        sc.close();
    }

    protected static String wrap(String text) {
        String line = "____________________________________________________________\n";
        return line + text + line;
    }
}
