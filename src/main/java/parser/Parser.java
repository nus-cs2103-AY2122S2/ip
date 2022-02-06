package parser;

import exception.DukeException;
import storage.Storage;
import task.TaskList;

/**
 * Interface that deals with making sense of the user commands.
 */
public class Parser {

    TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Calls appropriate TaskList methods for each command and updates storage.
     * @param inputStringsArray string array containing user instructions.
     * @param storage Storage object that deals with storage updation.
     * @throws DukeException If user input message does not make sense.
     */
    public String userCommand(String[] inputStringsArray, Storage storage) throws DukeException {
        switch (inputStringsArray[0]) {
            case "list":
                return tasks.displayList();
            case "mark":
                String marked = tasks.mark(inputStringsArray[1]);
                storage.updateData(tasks);
                return marked;
            case "unmark":
                String unmarked = tasks.unmark(inputStringsArray[1]);
                storage.updateData(tasks);
                return unmarked;
            case "todo":
            case "deadline":
            case "event":
                String added = tasks.addToList(inputStringsArray);
                storage.updateData(tasks);
                return added;
            case "delete":
                String deleted = tasks.delete(inputStringsArray[1]);
                storage.updateData(tasks);
                return deleted;
            case "find":
                return tasks.find(inputStringsArray[1]);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means O.o");
        }
    }
}
