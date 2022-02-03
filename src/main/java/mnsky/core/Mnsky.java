package mnsky.core;

import java.util.ArrayList;

import mnsky.exceptions.MnskyException;
import mnsky.task.Deadline;
import mnsky.task.Event;
import mnsky.task.Task;

public class Mnsky {
    private static final String[] writeCommands = {"mark", "unmark", "task", "event", "deadline", "delete"};

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
     * Returns whether or not command passed in the argument is a command that writes to the task list.
     * @param searchedCommand The command to be checked.
     * @return True if the command writes to the task list, or false otherwise.
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
     * Parses and processes the input to get Mnsky's responses for the user.
     * @return Mnsky's responses to the input
     */
    public ArrayList<String> getResponses(String input) {
        ArrayList<String> responses = new ArrayList<>();

        try {
            ArrayList<String> parsedInput = Parser.parseInput(input);
            switch (parsedInput.get(0)) {
            case "bye":
                responses.add("bye");
                break;
            case "list":
                responses = ui.printListStrings(taskList.getListStrings());
                break;
            case "mark":
                Task markedTask = taskList.mark(parsedInput.get(1));
                responses.add(ui.printTask(markedTask));
                break;
            case "unmark":
                Task unmarkedTask = taskList.unmark(parsedInput.get(1));
                responses.add(ui.printTask(unmarkedTask));
                break;
            case "task":
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
            case "find":
                responses = ui.printListStrings(taskList.find(parsedInput.get(1)));
                break;
            default:
                responses.add("...");
            }

            if (isWriteCommand(parsedInput.get(0))) {
                storage.writeToDataFile(taskList);
            }
        } catch (MnskyException e) {
            responses.add(ui.printException(e));
        }

        responses.add("I can help!");
        return responses;
    }
}
