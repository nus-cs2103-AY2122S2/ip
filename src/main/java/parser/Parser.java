package parser;

import exception.DukeException;
import notes.NoteList;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Interface that deals with making sense of the user commands.
 */
public class Parser {

    private TaskList tasks;
    private NoteList notes;

    /**
     * Parser class constructor
     * @param tasks maintains the tasks added to the task list.
     * @param notes maintains the notes added to the note list.
     */
    public Parser(TaskList tasks, NoteList notes) {
        this.tasks = tasks;
        this.notes = notes;
    }

    /**
     * Divides note and task commands entered by the user.
     * @param inputStringsArray string array containing the user command.
     * @param storage Storage object that deals with storage updates.
     * @throws DukeException If user input message does not make sense.
     */
    public String userCommand(String[] inputStringsArray, Storage storage) throws DukeException {
        switch (inputStringsArray[0]) {
        case "task":
            return taskParser(inputStringsArray, storage);
        case "note":
            return noteParser(inputStringsArray, storage);
        case "help":
            return Ui.userHelp();
        default:
            throw new DukeException("OOPS! I'm sorry, but I don't know what that command means.\n"
                    + "Enter 'help' if you need it!");
        }
    }

    /**
     * Handles method calls based on task related user commands.
     * @param inputStringsArray string array containing user instructions.
     * @param storage Storage object that deals with storage updates.
     * @throws DukeException If user input message does not make sense.
     */
    public String taskParser(String[] inputStringsArray, Storage storage) throws DukeException {
        String taskCommand;
        try {
            taskCommand = inputStringsArray[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Enter a task command...");
        }
        switch (taskCommand) {
        case "list":
            return tasks.displayList();
        case "mark":
            String marked = tasks.mark(inputStringsArray);
            storage.updateTaskData(tasks);
            return marked;
        case "unmark":
            String unmarked = tasks.unmark(inputStringsArray);
            storage.updateTaskData(tasks);
            return unmarked;
        case "new":
            String added = tasks.addToList(inputStringsArray);
            storage.updateTaskData(tasks);
            return added;
        case "delete":
            String deleted = tasks.delete(inputStringsArray);
            storage.updateTaskData(tasks);
            return deleted;
        case "find":
            return tasks.find(inputStringsArray);
        default:
            throw new DukeException("Not sure what that task command means... Enter 'help' if you need it!");
        }
    }

    /**
     * Handles method calls based on note related user commands
     * @param inputStringsArray string array containing user instructions.
     * @param storage Storage object that deals with storage updates.
     * @throws DukeException If user input message does not make sense.
     */
    public String noteParser(String[] inputStringsArray, Storage storage) throws DukeException {
        String taskCommand;
        try {
            taskCommand = inputStringsArray[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Enter a note command...");
        }
        switch (taskCommand) {
        case "list":
            return notes.displayList();
        case "new":
            String added = notes.addToList(inputStringsArray);
            storage.updateNoteData(notes);
            return added;
        case "delete":
            String deleted = notes.delete(inputStringsArray);
            storage.updateNoteData(notes);
            return deleted;
        case "find":
            return notes.find(inputStringsArray);
        default:
            throw new DukeException("Not sure what that note command means...\n"
                    + "Enter 'help if you need it!");
        }
    }
}
