package duke;

import duke.common.Constant;
import duke.task.Task;

import java.util.regex.Pattern;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * Parser class handles and process user's inputs with commands.
 */
public class Parser {

    private TaskList taskList;
    private Ui ui;
    private boolean isExit;

    /**
     * Creates an instance of Parser.
     *
     * @param taskList taskList to get information of tasks.
     * @param ui       to interact with the users after processing the input.
     */
    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
        this.isExit = false;
    }

    public boolean commandIsExit() {
        return isExit;
    }

    /**
     * Handles main logic of processing user inputs based on commands entered.
     *
     * @return response after processing the input.
     */
    public String parseInput(String input) throws DukeException {
        String output;
        try {
            String[] commandLine = parseCommandLine(input);
            Command command = Command.getCommand(commandLine[0]);

            switch (command) {
            case BYE:
                parseBye(commandLine);
                output = ui.endMessage();
                break;
            case LIST:
                validateList(commandLine);
                output = ui.listMessage();
                break;
            case MARK:
                parseMark(commandLine);
                output = ui.markMessage(Integer.parseInt(commandLine[1]));
                break;
            case UNMARK:
                parseUnmark(commandLine);
                output = ui.unMarkMessage(Integer.parseInt(commandLine[1]));
                break;
            case ADD:
                parseAdd(commandLine);
                output = ui.addTaskMessage();
                break;
            case DELETE:
                String toDelete = parseDelete(commandLine);
                output = ui.deleteMessage(toDelete);
                break;
            case FIND:
                String found = parseFind(commandLine);
                output = ui.findMessage(found);
                break;
            default:
                throw new DukeException.DukeInvalidCommandException();
            }
            taskList.save();
            return output;
        } catch (DukeException e) {
            throw e;
        }
    }

    private String parseFind(String[] commandLine) throws DukeException {
        if (commandLine.length == 1) {
            throw new DukeException.DukeNoDescriptionFoundException();
        }
        return taskList.find(commandLine[1]);
    }

    private void parseAdd(String[] commandLine) throws DukeException {
        String commandType = commandLine[0];
        if (commandLine.length == 1) {
            if ((commandType.equals("todo")) || commandType.equals("deadline")
                    || commandType.equals("event")) {
                throw new DukeException.DukeNoDescriptionFoundException();
            }
        }

        String commandInfo = commandLine[1];
        if (commandLine.length == 2 && !commandType.equals("todo")) {
            Task.Type taskType = null;
            String separator = "";
            if (commandType.equals("deadline")) {
                taskType = Task.Type.DEADLINE;
                separator = "/by";
            }
            if (commandType.equals("event")) {
                taskType = Task.Type.EVENT;
                separator = "/at";
            }
            if (!commandInfo.contains(separator)) {
                throw new DukeException.DukeInvalidCommandException();
            }
            if (commandInfo.substring(0, commandInfo.indexOf(separator)).length() == 0) {
                throw new DukeException.DukeNoDescriptionFoundException();
            }
            if (commandInfo.substring(commandInfo.indexOf(separator) + 3).length() == 0) {
                throw new DukeException.DukeTimeNotFoundException();
            }
            String[] essentialInfo = commandInfo.split(separator);
            taskList.addTask(essentialInfo[0], parseGetTime(essentialInfo[1].trim()), taskType);
            return;
        }
        taskList.addTask(commandInfo, Task.Type.TODO);
    }

    private LocalDateTime parseGetTime(String time) throws DukeException {
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime future = LocalDateTime.parse(time, Constant.IN_TIME_FORMATTER);

            if (!now.isBefore(future)) {
                throw new DukeException.DukePastTimeException();
            }
            return future;
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeDateTimeFormatException();
        }
    }

    private void validateList(String[] commandLine) throws DukeException {
        if (commandLine.length != 1) {
            throw new DukeException.DukeInvalidCommandException();
        }

        if (taskList.size() == 0) {
            throw new DukeException.DukeEmptyTaskException();
        }
    }

    private void parseBye(String[] commandLine) throws DukeException {
        if (commandLine.length != 1) {
            throw new DukeException.DukeInvalidCommandException();
        }
        isExit = true;
    }

    private void parseMark(String[] commandLine) throws DukeException {
        if (commandLine.length == 1) {
            throw new DukeException.DukeTaskNotFoundException();
        }
        taskList.markTask(stringToIndex(commandLine[1]));
    }

    private void parseUnmark(String[] commandLine) throws DukeException {
        if (commandLine.length == 1) {
            throw new DukeException.DukeTaskNotFoundException();
        }
        taskList.unmarkTask(stringToIndex(commandLine[1]));
    }

    private String parseDelete(String[] commandLine) throws DukeException {
        if (commandLine.length == 1) {
            throw new DukeException.DukeTaskNotFoundException();
        }
        String toDelete = taskList.getTaskDescription(stringToIndex(commandLine[1]));
        taskList.delete(stringToIndex(commandLine[1]));
        return toDelete;
    }

    private int stringToIndex(String n) throws DukeException {
        if (!Pattern.matches("^\\d*[1-9]\\d*$", n)) {
            throw new DukeException.DukeNotAValidNumberException();
        }
        int idx = Integer.parseInt(n);
        if (taskList.size() == 0) {
            throw new DukeException.DukeEmptyTaskException();
        }
        if (idx > taskList.size()) {
            throw new DukeException.DukeTaskNotFoundException();
        }
        return idx;
    }

    private String[] parseCommandLine(String input) throws DukeException {
        String[] commandLine = input.trim().split("\\s+", 2);
        String commandType = commandLine[0];
        if (!Command.isValid(commandType)) {
            throw new DukeException.DukeInvalidCommandException();
        }
        return commandLine;
    }
}
