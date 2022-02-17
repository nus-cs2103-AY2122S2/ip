package mnsky.core;

import java.util.ArrayList;

import mnsky.exceptions.MnskyException;
import mnsky.task.Deadline;
import mnsky.task.Event;
import mnsky.task.Task;

public class Mnsky {
    private static final int CMD_INDEX = 0;
    private static final int NAME_INDEX = 1;
    private static final int IDX_INDEX = 1;
    private static final int PARAMETER_INDEX = 2;
    private static final String[] writeCommands = {"mark", "unmark", "todo", "event", "deadline", "delete",
        "redo", "undo"};

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Creates a UI object.
     */
    public Mnsky() {
        storage = new Storage("data/MnskyData.txt");
        taskList = new TaskList(storage);
        ui = new Ui();
    }

    /**
     * Returns whether or not command passed in the argument is a command that changes the state of the task list.
     * @param searchedCommand The command to be checked.
     * @return True if the command changes the state of the task list, or false otherwise.
     */
    private boolean isWriteCommand(String searchedCommand) {
        for (String command : writeCommands) {
            if (command.equals(searchedCommand)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns whether or not the command passed in the argument is a command that directly changes the state of the
     * task list (e.g. not an undo or redo)
     * @param searchedCommand The command to be checked.
     * @return True if the command directly changes the state of the task list, or false otherwise.
     */
    private boolean isDirectWriteCommand(String searchedCommand) {
        if (searchedCommand.equals("undo") || searchedCommand.equals("redo")) {
            return false;
        }

        return isWriteCommand(searchedCommand);
    }

    /**
     * Handles commands that change the state of the task list.
     * @return Mnsky's responses to the input
     */
    public ArrayList<String> handleWriteCommand(ArrayList<String> parsedInput) throws MnskyException {
        if (isDirectWriteCommand(parsedInput.get(CMD_INDEX))) {
            taskList.addToUndoHistory();
        }

        ArrayList<String> responses = new ArrayList<>();

        switch (parsedInput.get(CMD_INDEX)) {
        case "mark":
            Task markedTask = taskList.mark(parsedInput.get(IDX_INDEX));
            responses.add(ui.printTask(markedTask));
            break;
        case "unmark":
            Task unmarkedTask = taskList.unmark(parsedInput.get(IDX_INDEX));
            responses.add(ui.printTask(unmarkedTask));
            break;
        case "todo":
            Task task = taskList.addTask(parsedInput.get(NAME_INDEX));
            responses.add(ui.printAddedTask(task));
            break;
        case "event":
            Event event = taskList.addEvent(parsedInput.get(NAME_INDEX), parsedInput.get(PARAMETER_INDEX));
            responses.add(ui.printAddedTask(event));
            break;
        case "deadline":
            Deadline deadline = taskList.addDeadline(parsedInput.get(NAME_INDEX), parsedInput.get(PARAMETER_INDEX));
            responses.add(ui.printAddedTask(deadline));
            break;
        case "delete":
            Task deleted = taskList.delete(parsedInput.get(IDX_INDEX));
            responses.add(ui.printDeletedTask(deleted));
            break;
        case "undo":
            taskList.undo();
            responses.addAll(taskList.getListStrings());
            break;
        case "redo":
            taskList.redo();
            responses.addAll(taskList.getListStrings());
            break;
        default:
            throw new MnskyException("[MNSKY had trouble interpreting the command.]");
        }

        storage.writeToDataFile(taskList);
        return responses;
    }

    /**
     * Parses and processes the input to get Mnsky's responses for the user.
     * @return Mnsky's responses to the input
     */
    public ArrayList<String> getResponses(String input) {
        ArrayList<String> responses = new ArrayList<>();

        try {
            ArrayList<String> parsedInput = Parser.parseInput(input);
            assert parsedInput.size() >= 1;

            switch (parsedInput.get(CMD_INDEX)) {
            case "hi":
                responses.add(ui.printGreeting());
                break;
            case "bye":
                responses.add("bye");
                break;
            case "list":
                responses.addAll(taskList.getListStrings());
                break;
            case "find":
                responses.addAll((taskList.find(parsedInput.get(IDX_INDEX))));
                break;
            default:
                if (isWriteCommand(parsedInput.get(CMD_INDEX))) {
                    responses.addAll(handleWriteCommand(parsedInput));
                } else {
                    System.out.println(parsedInput.get(CMD_INDEX));
                    responses.add("...");
                }
            }
        } catch (MnskyException e) {
            responses.add(ui.printException(e));
        }

        responses.add("I can help!");
        return responses;
    }
}
