package pyke.command;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import pyke.exception.EmptyDescriptionException;
import pyke.exception.EmptyTimeException;
import pyke.ui.Ui;
import pyke.util.Storage;
import pyke.util.TaskList;

class AddDeadlineCommandTest {
    private final String mockTaskName = "TASK_NAME";
    private final String mockDdl = "2002-06-25";
    private final TaskList mockTaskList = new TaskList();
    private final Ui mockUi = new Ui();
    private final Storage mockStorage = new Storage("test", "MOCK");

    @Test
    void execute_emptyTaskName_throwsEmptyDescriptionException() {
        AddDeadlineCommand cmd = new AddDeadlineCommand("", mockDdl);
        assertThrows(EmptyDescriptionException.class, () -> cmd.execute(mockTaskList, mockUi, mockStorage));
    }

    @Test
    void execute_emptyDdl_throwsEmptyTimeException() {
        AddDeadlineCommand cmd = new AddDeadlineCommand(mockTaskName, "");
        assertThrows(EmptyTimeException.class, () -> cmd.execute(mockTaskList, mockUi, mockStorage));
    }
}
