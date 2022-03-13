package tesseract.command;

import tesseract.main.Date;
import tesseract.main.Storage;
import tesseract.main.TaskList;
import tesseract.main.TessUi;
import tesseract.main.TesseractException;


/**
 * Represent a command.
 *
 * @author Fan Jue
 * @version 0.1.0
 * @since 0.1.0
 */
public class Command {
    private static final String BY = "/by";
    private static final String AT = "/at";
    private static final String DATE = "date";
    private static final String BYE_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String TODO_COMMAND = "todo";
    private static final String DDL_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String FILTER_COMMAND = "filter";
    private static final String FIND_COMMAND = "find";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String DELETE_COMMAND = "delete";
    private static final String SCHEDULE_COMMAND = "schedule";
    private static final String ARCHIVE_COMMAND = "archive";
    private static final String INVALID_COMMAND = "You have entered an invalid command leh~";
    private static final String INCOMPLETE_COMMAND = "Which task you want to update again?";
    private static final String INVALID_INDEX = "You need to enter a valid list number mah~";
    private static final String INCOMPLETE_FILTER = "What do you want to filter by again?";
    private static final String TOO_MUCH_FIND_KEYWORD = "You can only find by one keyword siah~";
    private static final String INCOMPLETE_FIND = "You need to key in your search keyword mah";
    private static final String INCOMPLETE_EVENT = "Nah you need to provide me "
        + "with the details of this event *_*";
    private static final String EVENT_MISSING_TIMING = "When is the timing for your event again?";
    private static final String INCOMPLETE_DEADLINE = "Sorry I can't create "
        + "deadline without its details )-:";
    private static final String DEADLINE_MISSING_TIMING = "When do you need to do this by again?";
    private static final String INCOMPLETE_TODO = "I cannot create todo"
            + " if you don't tell me what it's about eh :-(";
    private static final String INCOMPLETE_SCHEDULE = "Pls key in the date you want to view your schedule~";
    private static final String DUMMY_INVALID_COMMAND = "Hey bro, not sure if this command is valid eh #_#";

    /** The specific command keyword */
    protected final String cmdWord;


    Command(String keyword) {
        this.cmdWord = keyword;
    }

    /**
     * Execute the command on the system.
     *
     * @param taskList The list of all current tasks.
     * @param ui       The user interface.
     * @param storage  The memory storage.
     */
    public String execute(TaskList taskList, TessUi ui, Storage storage) {
        return ui.admitBug();
    }

    /**
     * Process the input to check if it is a valid command.
     *
     * @param fullCmd    The line of input.
     * @param numOfTasks The current number of tasks in list.
     * @throws TesseractException If the input is not a valid command.
     */
    public static void process(String fullCmd, int numOfTasks) throws TesseractException {
        String[] cmdArr = fullCmd.split(" ");
        int cmdLen = cmdArr.length;
        switch (cmdArr[0]) {
        case BYE_COMMAND:
        case LIST_COMMAND:
            if (cmdLen > 1) {
                throw new TesseractException(INVALID_COMMAND);
            }
            break;
        case DELETE_COMMAND:
        case MARK_COMMAND:
        case UNMARK_COMMAND:
        case ARCHIVE_COMMAND:
            if (cmdLen == 1) {
                throw new TesseractException(INCOMPLETE_COMMAND);
            } else if (cmdLen > 2 || !Command.isInteger(cmdArr[1])
                    || Integer.parseInt(cmdArr[1]) > numOfTasks
                    || Integer.parseInt(cmdArr[1]) < 1) {
                throw new TesseractException(INVALID_INDEX);
            }
            break;
        case FILTER_COMMAND:
            if (cmdLen < 4 || !fullCmd.contains(BY)) {
                throw new TesseractException(INCOMPLETE_FILTER);
            } else if (cmdArr[2].equals(DATE)) {
                Date.checkValidTime(cmdArr[3]);
            }
            break;
        case FIND_COMMAND:
            if (cmdLen > 2) {
                throw new TesseractException(TOO_MUCH_FIND_KEYWORD);
            } else if (cmdLen < 2) {
                throw new TesseractException(INCOMPLETE_FIND);
            }
            break;
        case EVENT_COMMAND:
            if (cmdLen == 1) {
                throw new TesseractException(INCOMPLETE_EVENT);
            } else if (!fullCmd.contains(AT)) {
                throw new TesseractException(EVENT_MISSING_TIMING);
            }
            Date.checkValidTime(cmdArr[cmdLen - 1]);
            break;
        case DDL_COMMAND:
            if (cmdLen == 1) {
                throw new TesseractException(INCOMPLETE_DEADLINE);
            } else if (!fullCmd.contains(BY)) {
                throw new TesseractException(DEADLINE_MISSING_TIMING);
            }
            Date.checkValidTime(cmdArr[cmdLen - 1]);
            break;
        case TODO_COMMAND:
            if (cmdLen == 1) {
                throw new TesseractException(INCOMPLETE_TODO);
            }
            break;
        case SCHEDULE_COMMAND:
            if (cmdLen == 1) {
                throw new TesseractException(INCOMPLETE_SCHEDULE);
            } else if (cmdLen > 2) {
                throw new TesseractException(INVALID_COMMAND);
            }
            Date.checkValidTime(cmdArr[cmdLen - 1]);
            break;
        default:
            throw new TesseractException(DUMMY_INVALID_COMMAND);
        }

    }

    /**
     * Return a command generated from the input line.
     *
     * @param fullCmd The line of input.
     * @return A command to be executed.
     */
    public static Command generate(String fullCmd) {
        String[] cmdArr = fullCmd.split(" ");
        switch (cmdArr[0]) {
        case BYE_COMMAND:
            return new ExitCommand(cmdArr);
        case LIST_COMMAND:
            return new ListCommand(cmdArr);
        case FILTER_COMMAND:
            return new FilterCommand(cmdArr);
        case FIND_COMMAND:
            return new FindCommand(cmdArr);
        case DELETE_COMMAND:
            return new DeleteCommand(cmdArr);
        case MARK_COMMAND:
            return new MarkCommand(cmdArr);
        case UNMARK_COMMAND:
            return new UnmarkCommand(cmdArr);
        case SCHEDULE_COMMAND:
            return new ScheduleCommand(cmdArr);
        case ARCHIVE_COMMAND:
            return new ArchiveCommand(cmdArr);
        case TODO_COMMAND:
        case DDL_COMMAND:
        case EVENT_COMMAND:
            return new CreateTaskCommand(cmdArr);
        default:
            // dummy command
            return new Command("");
        }
    }

    /**
     * Check if the input string can be converted to an integer.
     *
     * @param str The input string to be tested.
     * @return True if the input string represents an integer, false otherwise.
     */
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Check if the program is terminated.
     *
     * @return False for all commands except ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
