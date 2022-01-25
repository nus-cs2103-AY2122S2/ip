package duke;

import duke.common.Const;
import duke.task.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class Parser {

    private TaskList taskList;
    private Ui ui;
    private boolean isExit;

    public Parser(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
        this.isExit = false;
    }

    public boolean commandIsExit() {
        return isExit;
    }

    public void parseInput(String input) throws DukeException {
        try {
            String commandLine[] = cleanInput(input);
            Command c = Command.getCommand(commandLine[0]);
            if (c == null) throw new DukeException.DukeInvalidCommandException();

            // main
            switch (c) {
            case BYE:
                isExit = true;
                taskList.save();
                ui.endMessage();
                break;
            case LIST:
                ui.listMessage();
                break;
            case MARK:
                taskList.markTask(Integer.parseInt(commandLine[1]));
                ui.markMessage(Integer.parseInt(commandLine[1]));
                break;
            case UNMARK:
                taskList.unmarkTask(Integer.parseInt(commandLine[1]));
                ui.unMarkMessage(Integer.parseInt(commandLine[1]));
                break;
            case TODO:
                taskList.addTask(commandLine[1], Task.Type.TODO);
                ui.addTaskMessage();
                break;
            case DEADLINE:
                LocalDateTime dateTime = LocalDateTime.parse(commandLine[2], Const.IN_TIME_FORMATTER);
                taskList.addTask(commandLine[1], dateTime, Task.Type.DEADLINE);
                ui.addTaskMessage();
                break;
            case EVENT:
                LocalDateTime dateTime2 = LocalDateTime.parse(commandLine[2], Const.IN_TIME_FORMATTER);
                taskList.addTask(commandLine[1], dateTime2, Task.Type.EVENT);
                ui.addTaskMessage();
                break;
            case DELETE:
                String toDelete = taskList.getTaskDescription(Integer.parseInt(commandLine[1]));
                taskList.delete(Integer.parseInt(commandLine[1]));
                ui.deleteMessage(toDelete);
                break;
            }
        } catch (DukeException e) {
            throw e;
        }
    }

    private String[] cleanInput(String input) throws DukeException {
        String[] commandLine = input.trim().split("\\s+", 2);
        String commandType = commandLine[0];
        boolean commandWithNo = commandType.equals("mark") || commandType.equals("unmark") ||
                commandType.equals("delete");

        // check if command exist.
        if (!Command.isValid(commandType)) throw new DukeException.DukeInvalidCommandException();

        // commands that requires a description except list.
        if (commandLine.length == 1) {
            if ((commandType.equals("todo")) || commandType.equals("deadline") ||
                    commandType.equals("event")) {
                throw new DukeException.DukeNoDescriptionFoundException();
            }
            if (commandWithNo) {
                throw new DukeException.DukeTaskNotFoundException();
            }
            // check if notebook is empty
            if (commandType.equals("list") & taskList.size() == 0) {
                throw new DukeException.DukeEmptyTaskException();
            }
        }

        if (commandLine.length == 2 && !commandType.equals("todo")) {
            String commandInfo = commandLine[1];
            String[] essentialInfo = new String[0];
            if (commandType.equals("bye") || commandType.equals("list")) {
                throw new DukeException.DukeInvalidCommandException();
            }

            if (commandWithNo) {
                // check if task number is valid/
                if (!Pattern.matches("^\\d*[1-9]\\d*$", commandInfo)) {
                    throw new DukeException.DukeNotAValidNumberException();
                }
                int idx = Integer.parseInt(commandInfo);
                if (taskList.size() == 0) {
                    throw new DukeException.DukeEmptyTaskException();
                }
                if (idx > taskList.size()) {
                    throw new DukeException.DukeTaskNotFoundException();
                }
                return commandLine;
            }

            if (commandType.equals("deadline")) {
                if (!commandInfo.contains("/by")) throw new DukeException.DukeInvalidCommandException();
                // check if there is a description
                if (commandInfo.substring(0, commandInfo.indexOf("/by")).length() == 0) {
                    throw new DukeException.DukeNoDescriptionFoundException();
                }
                // check if there is a time
                if (commandInfo.substring(commandInfo.indexOf("/by") + 3).length() == 0) {
                    throw new DukeException.DukeTimeNotFoundException();
                }
                essentialInfo = commandInfo.split("/by");
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
            }

            try {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime future = LocalDateTime.parse(essentialInfo[1].trim(), Const.IN_TIME_FORMATTER);

                if (!now.isBefore(future)) {
                    throw new DukeException.DukePastTimeException();
                }
            } catch (DateTimeParseException e) {
                throw new DukeException.DukeDateTimeFormatException();
            }

            return new String[]{commandType, essentialInfo[0].trim(), essentialInfo[1].trim()};
        }
        return commandLine;
    }
}
