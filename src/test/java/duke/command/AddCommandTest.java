package duke.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommandTest {
    @Test
    public void execute_addToDoCommand() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data", "test.txt");

        AddCommand addToDoCommand = new AddToDoCommand("Test ToDo Task");
        addToDoCommand.execute(taskList, ui, storage);

        Task toDoTask = taskList.getTask(0);
        assertEquals("[T] [   ] Test ToDo Task", toDoTask.toString());

        // Deletes the data file after JUnit test
        File dataFile = storage.getDataFile();
        assertTrue(dataFile.delete());
    }

    @Test
    public void execute_addDeadlineCommand() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data", "test.txt");

        AddCommand addDeadlineCommand = new AddDeadlineCommand("Test Deadline Task /by d/2022-01-30 t/2359");
        addDeadlineCommand.execute(taskList, ui, storage);

        Task deadlineTask = taskList.getTask(0);
        assertEquals("[D] [   ] Test Deadline Task (by: Jan 30 2022, 11:59 PM)", deadlineTask.toString());

        // Deletes the data file after JUnit test
        File dataFile = storage.getDataFile();
        assertTrue(dataFile.delete());
    }

    @Test
    public void execute_addEventCommand() throws DukeException, IOException {
        TaskList taskList = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data", "test.txt");

        AddCommand addEventCommand = new AddEventCommand("Test Event Task /at d/2022-01-31 t/1900-2200");
        addEventCommand.execute(taskList, ui, storage);

        Task eventTask = taskList.getTask(0);
        assertEquals("[E] [   ] Test Event Task (at: Jan 31 2022, 07:00 PM to 10:00 PM)", eventTask.toString());

        // Deletes the data file after JUnit test
        File dataFile = storage.getDataFile();
        assertTrue(dataFile.delete());
    }
}
