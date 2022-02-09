package mnsky.core;

import java.util.ArrayList;

import mnsky.exceptions.MnskyException;
import mnsky.task.Deadline;
import mnsky.task.Event;
import mnsky.task.Task;

public class Mnsky {
    private static final String[] writeCommands = {"mark", "unmark", "todo", "event", "deadline", "delete",
        "redo", "undo"};

    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * Creates a UI object.
     */
    public Mnsky() {
        ui = new Ui();
        storage = new Storage("data/MnskyData.txt");
        taskList = new TaskList(ui, storage);
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
        if (isDirectWriteCommand(parsedInput.get(0))) {
            taskList.addToUndoHistory();
        }

        ArrayList<String> responses = new ArrayList<>();

        switch (parsedInput.get(0)) {
        case "mark":
            Task markedTask = taskList.mark(parsedInput.get(1));
            responses.add(ui.printTask(markedTask));
            break;
        case "unmark":
            Task unmarkedTask = taskList.unmark(parsedInput.get(1));
            responses.add(ui.printTask(unmarkedTask));
            break;
        case "todo":
            Task task = taskList.addTask(parsedInput.get(1));
            responses.add(ui.printAddedTask(task));
            break;
        case "event":
            Event event = taskList.addEvent(parsedInput.get(1), parsedInput.get(2));
            responses.add(ui.printAddedTask(event));
            break;
        case "deadline":
            Deadline deadline = taskList.addDeadline(parsedInput.get(1), parsedInput.get(2));
            responses.add(ui.printAddedTask(deadline));
            break;
        case "delete":
            Task deleted = taskList.delete(parsedInput.get(1));
            responses.add(ui.printDeletedTask(deleted));
            break;
        case "undo":
            taskList.undo();
            responses.addAll(ui.printListStrings(taskList.getListStrings()));
            break;
        case "redo":
            taskList.redo();
            responses.addAll(ui.printListStrings(taskList.getListStrings()));
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

            switch (parsedInput.get(0)) {
            case "hi":
                responses.add(ui.printGreeting());
                break;
            case "bye":
                responses.add("bye");
                break;
            case "list":
                responses.addAll(ui.printListStrings(taskList.getListStrings()));
                break;
            case "find":
                responses.addAll(ui.printListStrings(taskList.find(parsedInput.get(1))));
                break;
            default:
                if (isWriteCommand(parsedInput.get(0))) {
                    responses.addAll(handleWriteCommand(parsedInput));
                } else {
                    System.out.println(parsedInput.get(0));
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
