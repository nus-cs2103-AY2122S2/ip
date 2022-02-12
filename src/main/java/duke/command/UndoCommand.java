package duke.command;

import duke.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;
import java.util.EmptyStackException;

/**
 * Represents a Command object that will undo the previous command.
 */
public class UndoCommand extends Command {

    /**
     * Undoes the previous command.
     *
     * @param stg   The storage object to use file writing methods.
     * @param ui    The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @return Message on the outcome of the command.
     * @throws DukeException If any error related to the Duke app occurs.
     * @throws IOException   If an I/O error occurs.
     */
    @Override
    public String execute(Storage stg, Ui ui, TaskList tasks) throws DukeException, IOException {
        try {
            String command = stg.popFromMemory();
            if (command.startsWith("add")) {
                undoAddCommand(stg, ui, tasks);
            } else if (command.startsWith("mark")) {
                undoMarkCommand(stg, ui, tasks, command);
            } else if (command.startsWith("unmark")) {
                undoUnmarkCommand(stg, ui, tasks, command);
            } else {
                undoDeleteCommand(stg, ui, tasks, command);
            }
            return "Got it! Undo previous command!";
        } catch (EmptyStackException e) {
            return "Oops! No more commands to undo!";
        }
    }

    /**
     * Undoes an add command.
     *
     * @param stg   The storage object to use file writing methods.
     * @param ui    The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     */
    public void undoAddCommand(Storage stg, Ui ui, TaskList tasks)
            throws IOException {
        int lastIndex = tasks.getCount() - 1;
        Task task = tasks.get(lastIndex);
        String oldDelete = task.formatText();
        tasks.deleteTask(lastIndex);
        stg.editData(oldDelete, " ");
    }

    /**
     * Undoes a mark command.
     *
     * @param stg   The storage object to use file writing methods.
     * @param ui    The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @param command The string representation of the command to be undone.
     * @throws IOException   If an I/O error occurs.
     */
    public void undoMarkCommand(Storage stg, Ui ui, TaskList tasks, String command)
            throws IOException {
        int index = Parser.getIndex(command);
        Task task = tasks.get(index - 1);
        String oldMark = task.formatText();
        task.unmarkTask();
        String replaceMark = task.formatText();
        stg.editData(oldMark, replaceMark);
    }

    /**
     * Undoes an unmark command.
     *
     * @param stg   The storage object to use file writing methods.
     * @param ui    The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @param command The string representation of the command to be undone.
     * @throws IOException   If an I/O error occurs.
     */
    public void undoUnmarkCommand(Storage stg, Ui ui, TaskList tasks, String command)
            throws IOException {
        int index = Parser.getIndex(command);
        Task task = tasks.get(index - 1);
        String oldMark = task.formatText();
        task.markTask();
        String replaceMark = task.formatText();
        stg.editData(oldMark, replaceMark);
    }

    /**
     * Undoes a delete command.
     *
     * @param stg   The storage object to use file writing methods.
     * @param ui    The ui object to handle I/O requests.
     * @param tasks The task list which holds all tasks available.
     * @param command The string representation of the command to be undone.
     * @throws IOException   If an I/O error occurs.
     */
    public void undoDeleteCommand(Storage stg, Ui ui, TaskList tasks, String command)
            throws IOException {
        //format of string is delete (deleted index) -(task)
        //for eg. delete 5 -T|X|read book
        int indexOfSeparator = command.indexOf('-');
        String firstHalf = command.substring(0, indexOfSeparator - 1);
        int index = Parser.getIndex(firstHalf); //position to add task to
        String secondHalf = command.substring(indexOfSeparator + 1);
        Task taskToAdd = Parser.textToTask(secondHalf);
        tasks.addTask(taskToAdd, index - 1);
        stg.insertData(secondHalf, index);
    }

    /**
     * Returns boolean value to state if the app should exit its run.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
