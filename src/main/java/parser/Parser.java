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

    TaskList tasks;
    NoteList notes;

    public Parser(TaskList tasks, NoteList notes) {
        this.tasks = tasks;
        this.notes = notes;
    }

    /**
     * Calls appropriate TaskList methods for each command and updates storage.
     * @param inputStringsArray string array containing user instructions.
     * @param storage Storage object that deals with storage updation.
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
        case "bye":
            System.exit(0);
        default:
            throw new DukeException("OOPS! I'm sorry, but I don't know what that command means.\n" +
                    "Enter 'help' if you need it!");
        }
    }

    public String taskParser(String[] inputStringsArray, Storage storage) throws DukeException {
        switch (inputStringsArray[1]) {
        case "list":
            return tasks.displayList();
        case "mark":
            String marked = tasks.mark(inputStringsArray[2]);
            storage.updateTaskData(tasks);
            return marked;
        case "unmark":
            String unmarked = tasks.unmark(inputStringsArray[2]);
            storage.updateTaskData(tasks);
            return unmarked;
        case "new":
            String added = tasks.addToList(inputStringsArray);;
            storage.updateTaskData(tasks);
            return added;
        case "delete":
            String deleted = tasks.delete(inputStringsArray[2]);
            storage.updateTaskData(tasks);
            return deleted;
        case "find":
            return tasks.find(inputStringsArray[2]);
        default:
            throw new DukeException("Not sure what that task command means...\n" +
                    "Enter 'help' if you need it!");
        }
    }

    public String noteParser(String[] inputStringsArray, Storage storage) throws DukeException {
        switch (inputStringsArray[1]) {
        case "list":
            return notes.displayList();
        case "new":
            String added = notes.addToList(inputStringsArray);
            storage.updateNoteData(notes);
            return added;
        case "delete":
            String deleted = notes.delete(inputStringsArray[2]);
            storage.updateNoteData(notes);
            return deleted;
        case "find":
            return notes.find(inputStringsArray[2]);
        default:
            throw new DukeException("Not sure what that note command means...\n" +
                    "Enter 'help if you need it!");
        }
    }

}
