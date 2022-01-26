package duke;

import duke.common.Const;
import duke.task.Task;

import java.util.regex.Pattern;
import java.time.format.DateTimeParseException;
import java.time.LocalDateTime;

/**
 * Parser class handles and process user's inputs with commands
 */
public class Parser {

    private TaskList taskList;
    private Ui ui;
    private boolean isExit;

    /**
     * Create an instance of Parser
     *
     * @param taskList taskList to get information of tasks
     * @param ui       to interact with the users after processing the input
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
     * Main login in handling user input based on commands entered.
     */
    public void parseInput(String input) throws DukeException {
        try {
            String[] commandLine = parseCommandLine(input);
            Command c = Command.getCommand(commandLine[0]);
            if (c == null) {
                throw new DukeException.DukeInvalidCommandException();
            }

            switch (c) {
            case BYE:
                parseBye(commandLine);
                ui.endMessage();
                break;
            case LIST:
                parseList(commandLine);
                ui.listMessage();
                break;
            case MARK:
                parseMark(commandLine);
                ui.markMessage(Integer.parseInt(commandLine[1]));
                break;
            case UNMARK:
                parseUnMark(commandLine);
                ui.unMarkMessage(Integer.parseInt(commandLine[1]));
                break;
            case ADD:
                parseAdd(commandLine);
                ui.addTaskMessage();
                break;
            case DELETE:
                String toDelete = parseDelete(commandLine);
                ui.deleteMessage(toDelete);
                break;
            case FIND:
                String found = parseFind(commandLine);
                ui.findMessage(found);
                break;
            default:
                break;
            }
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

        if (commandLine.length == 2 && !commandType.equals("todo")) {
            String commandInfo = commandLine[1];
            String[] essentialInfo;

            if (commandType.equals("deadline")) {
                if (!commandInfo.contains("/by")) {
                    throw new DukeException.DukeInvalidCommandException();
                }
                // check if there is a description
                if (commandInfo.substring(0, commandInfo.indexOf("/by")).length() == 0) {
                    throw new DukeException.DukeNoDescriptionFoundException();
                }
                // check if there is a time
                if (commandInfo.substring(commandInfo.indexOf("/by") + 3).length() == 0) {
                    throw new DukeException.DukeTimeNotFoundException();
                }
                essentialInfo = commandInfo.split("/by");
                taskList.addTask(essentialInfo[0], parseGetTime(essentialInfo[1].trim()), Task.Type.DEADLINE);
                return;
            }


            if (commandType.equals("event")) {
                if (!commandInfo.contains("/at")) {
                    throw new DukeException.DukeInvalidCommandException();
                }
                // check if there is a description
                if (commandInfo.substring(0, commandInfo.indexOf("/at")).length() == 0) {
                    throw new DukeException.DukeNoDescriptionFoundException();
                }
                // check if there is a time
                if (commandInfo.substring(commandInfo.indexOf("/at") + 3).length() == 0) {
                    throw new DukeException.DukeTimeNotFoundException();
                }
                essentialInfo = commandInfo.split("/at");
                taskList.addTask(essentialInfo[0], parseGetTime(essentialInfo[1].trim()), Task.Type.EVENT);
                return;
            }
        }
        taskList.addTask(commandLine[1], Task.Type.TODO);
    }


    private LocalDateTime parseGetTime(String time) throws DukeException {
        try {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime future = LocalDateTime.parse(time, Const.IN_TIME_FORMATTER);

            if (!now.isBefore(future)) {
                throw new DukeException.DukePastTimeException();
            }
            return future;
        } catch (DateTimeParseException e) {
            throw new DukeException.DukeDateTimeFormatException();
        }
    }

    private void parseList(String[] commandLine) throws DukeException {
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
        taskList.save();
    }

    private void parseMark(String[] commandLine) throws DukeException {
        if (commandLine.length == 1) {
            throw new DukeException.DukeTaskNotFoundException();
        }
        taskList.markTask(stringToIndex(commandLine[1]));
    }

    private void parseUnMark(String[] commandLine) throws DukeException {
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
        // check if command exist.
        if (!Command.isValid(commandType)) {
            throw new DukeException.DukeInvalidCommandException();
        }
        return commandLine;
    }
}
