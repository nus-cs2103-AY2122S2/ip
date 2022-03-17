package pyke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pyke.exception.EmptyDescriptionException;
import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

class AddTodoCommandTest {
    private final TaskList mockTaskList = new TaskList();
    private final Ui mockUi = new Ui();
    private final Storage mockStorage = new Storage("test", "MOCK");

    @Test
    void execute_emptyTaskName_throwsEmptyDescriptionException() {
        AddTodoCommand cmd = new AddTodoCommand("");
        assertThrows(EmptyDescriptionException.class, () -> cmd.execute(mockTaskList, mockUi, mockStorage));
    }
}