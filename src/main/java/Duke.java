import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
import java.util.function.Consumer;

public class Duke {
    private static final String SEPARATOR = "\t------------------------------------";
    private static final String BOT_NAME = "Megumin";
    private static final String ERROR_INVALID_COMMAND = "I do not understand you!";
    private static final String ERROR_INVALID_SYNTAX = "There was a problem understanding you:";

    private static final String COMMAND_EXIT = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_CREATE_TODO = "todo";
    private static final String COMMAND_CREATE_DEADLINE = "deadline";
    private static final String COMMAND_CREATE_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_UPCOMING = "upcoming";
    private static final String COMMAND_BETWEEN = "between";
    private static final String COMMAND_SCHEDULE = "schedule";

    private static TaskList taskList;

    public static void main(String[] args) {
        init();
        greet();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String command = readInput(sc);
            if (command.equalsIgnoreCase(COMMAND_EXIT)) {
                break;
            }
            printBlock((linePrinter) -> {
                try {
                    parseCommand(command, linePrinter);
                } catch (DukeInvalidCommandException ex) {
                    linePrinter.print(ERROR_INVALID_COMMAND);
                } catch (DukeIllegalArgumentException ex) {
                    linePrinter.print(ERROR_INVALID_SYNTAX);
                    linePrinter.print(ex.getMessage());

                }
            });

            System.out.println();
        }
        printBlock((linePrinter) -> {
            linePrinter.print("Goodbye! Have a Nice Day.");
        });
    }

    private static void init() {
        try {
            taskList = Storage.load();
            taskList.registerListener(store -> {
                try {
                    Storage.save(store);
                } catch (DukeIOException ex) {
                    System.out.println("Warning: An error occurred while saving Task list");
                }
            });
        } catch (DukeIOException ex) {
            System.out.println("Cannot write to working directory.\n"
                    + "Please check that you have write to the directory permissions.\n"
                    + "Will not save any changes!");
        }
    }

    private static void greet() {
        printBlock((linePrinter) -> {
            linePrinter.print("Hi! I'm " + BOT_NAME);
            linePrinter.print("What do you need?");
        });
        System.out.println();
    }

    private static String readInput(Scanner sc) {
        System.out.println("Enter a Command or New Task:");

        return sc.nextLine();
    }

    private static void parseCommand(String command, IPrintable linePrinter)
            throws DukeInvalidCommandException,DukeIllegalArgumentException {
        final String[] commandParts = command.split(" ");
        final String commandLowerCase = commandParts[0].toLowerCase();
        final String args = command.substring(commandLowerCase.length()).trim();

        switch (commandLowerCase) {
        case COMMAND_LIST:
            linePrinter.print("This is your task list:");
            taskList.forEach((index, task) -> {
                // Note that index passed into this consumer is 0-based. Increment by 1 for readability
                linePrinter.print(String.format("%d. %s", index + 1, task.getReadableString()));
            });
            break;
        case COMMAND_MARK:
            // Fallthrough
        case COMMAND_UNMARK:
            parseMarkCommand(linePrinter, args, commandLowerCase.equals(COMMAND_MARK));
            break;
        case COMMAND_CREATE_TODO:
            parseCreateTodo(linePrinter, args);
            break;
        case COMMAND_CREATE_DEADLINE:
            parseCreateDeadline(linePrinter, args);
            break;
        case COMMAND_CREATE_EVENT:
            parseCreateEvent(linePrinter, args);
            break;
        case COMMAND_DELETE:
            parseDeleteEvent(linePrinter, args);
            break;
        case COMMAND_UPCOMING:
            parseUpcomingEvents(linePrinter, args);
            break;
        case COMMAND_SCHEDULE:
            parseSchedule(linePrinter, args);
            break;
        case COMMAND_BETWEEN:
            parseBetween(linePrinter, args);
            break;
        default:
            throw new DukeInvalidCommandException(String.format("No such command: %s", commandLowerCase));
        }
    }

    private static int parseTaskNumber(String args) throws DukeIllegalArgumentException {
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(args);
        } catch (NumberFormatException ex) {
            throw new DukeIllegalArgumentException("Task Number must be a number");
        }
        // Note that task storage uses 0-based index
        return taskIndex - 1;
    }

    private static Task parseSelectTask(String args) throws DukeIllegalArgumentException {
        int taskIndex = parseTaskNumber(args);
        Task task = taskList.getTaskByIndex(taskIndex);
        if (task == null) {
            throw new DukeIllegalArgumentException("No matching task with given number");
        }
        return task;
    }

    private static void parseMarkCommand(IPrintable linePrinter, String args, boolean newState)
            throws DukeIllegalArgumentException {
        Task task = parseSelectTask(args);

        if (task.isDone() == newState) {
            linePrinter.print(String.format("Task is already %s:", newState ? "done" : "not done"));
        } else if (newState) {
            task.markAsDone();
            taskList.notifyListeners();
            linePrinter.print("Great Job Finishing the task:");
        } else {
            task.unmarkAsDone();
            taskList.notifyListeners();
            linePrinter.print("Marking the task as not done yet:");
        }
        linePrinter.print(String.format("\t %s", task.getReadableString()));
    }

    private static void parseDeleteEvent(IPrintable linePrinter, String args)
            throws DukeIllegalArgumentException {
        int taskIndex = parseTaskNumber(args);
        Task deleted = taskList.deleteTask(taskIndex);

        if (deleted == null) {
            throw new DukeIllegalArgumentException("No matching task with given number");
        }

        linePrinter.print("Deleted the task:");
        linePrinter.print(String.format("\t %s", deleted.getReadableString()));
    }

    private static void parseCreateTodo(IPrintable linePrinter, String args)
            throws DukeIllegalArgumentException {
        // Syntax Check
        if (args.equals("")) {
            throw new DukeIllegalArgumentException("Task name cannot be empty");
        }

        final Task task = taskList.addTask(new Todo(args));
        linePrinter.print("Added the following Todo Task:");
        linePrinter.print(String.format("\t%s", task.getReadableString()));
        linePrinter.print(String.format("Now you have %d task(s) in the list", taskList.getTaskCount()));
    }

    private static void parseCreateDeadline(IPrintable linePrinter, String args)
            throws DukeIllegalArgumentException {
        // Syntax Check
        final String[] argParts = args.split(" /by ");
        if (argParts.length < 2) {
            throw new DukeIllegalArgumentException("Not in the format <Task name> /by <Date>");
        }

        final String taskDescription = argParts[0];
        final LocalDateTime taskBy = parseDateTime(argParts[1]);
        final Task task = taskList.addTask(new Deadline(taskDescription, taskBy));
        linePrinter.print("Added the following Deadline Task:");
        linePrinter.print(String.format("\t%s", task.getReadableString()));
        linePrinter.print(String.format("Now you have %d task(s) in the list", taskList.getTaskCount()));
    }

    private static void parseCreateEvent(IPrintable linePrinter, String args)
            throws DukeIllegalArgumentException {
        // Syntax Check
        final String[] argParts = args.split(" /at ");
        if (argParts.length < 2) {
            throw new DukeIllegalArgumentException("Not in the format <Task name> /at <Date>");
        }

        final String taskDescription = argParts[0];
        final LocalDateTime taskAt = parseDateTime(argParts[1]);
        final Task task = taskList.addTask(new Event(taskDescription, taskAt));
        linePrinter.print("Added the following Event Task:");
        linePrinter.print(String.format("\t%s", task.getReadableString()));
        linePrinter.print(String.format("Now you have %d task(s) in the list", taskList.getTaskCount()));
    }

    private static void parseUpcomingEvents(IPrintable linePrinter, String args)
            throws DukeIllegalArgumentException {
        int days = -1;
        try {
            days = Integer.parseInt(args);
        } catch (NumberFormatException ex) {
            throw new DukeIllegalArgumentException("Days must be a positive number");
        }

        if (days < 0) {
            throw new DukeIllegalArgumentException("Days must be a positive number");
        }

        LocalDateTime endTime = LocalDateTime.now().plus(days, ChronoUnit.DAYS);
        taskList.forEach((idx, task) -> {
            task.getDate().ifPresent(date -> {
                if (date.isBefore(endTime)) {
                    linePrinter.print(task.getReadableString());
                }
            });
        });
    }

    private static void parseSchedule(IPrintable linePrinter, String args)
            throws DukeIllegalArgumentException {
        LocalDateTime dayStart = parseDate(args);
        LocalDateTime dayEnd = dayStart.plus(1, ChronoUnit.DAYS);
        taskList.forEach((idx, task) -> {
            task.getDate().ifPresent(date -> {
                if (date.isBefore(dayEnd) && date.isAfter(dayStart)) {
                    linePrinter.print(task.getReadableString());
                }
            });
        });
    }

    private static void parseBetween(IPrintable linePrinter, String args)
            throws DukeIllegalArgumentException {
        final String[] argParts = args.split(" and ");
        if (argParts.length < 2) {
            throw new DukeIllegalArgumentException("Not in the format <date> and <date>");
        }
        LocalDateTime start = parseDateTime(argParts[0]);
        LocalDateTime end = parseDateTime(argParts[1]);
        taskList.forEach((idx, task) -> {
            task.getDate().ifPresent(date -> {
                if (date.isBefore(end) && date.isAfter(start)) {
                    linePrinter.print(task.getReadableString());
                }
            });
        });
    }

    private static LocalDateTime parseDateTime(String dateString) throws DukeIllegalArgumentException {
        return parseDateTime(dateString, "dd/MM/yyyy HH:mm");
    }

    private static LocalDateTime parseDate(String dateString) throws DukeIllegalArgumentException {
        return parseDateTime(dateString + " 00:00", "dd/MM/yyyy HH:mm");
    }

    private static LocalDateTime parseDateTime(String dateString, String pattern) throws DukeIllegalArgumentException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(dateString, formatter);
        } catch (DateTimeParseException ex) {
            throw new DukeIllegalArgumentException("Date not in the format " + pattern);
        }
    }

    private static void printBlock(Consumer<IPrintable> action) {
        System.out.println(SEPARATOR);
        action.accept((line) -> {
            System.out.println("\t" + line);
        });
        System.out.println(SEPARATOR);
    }
}
