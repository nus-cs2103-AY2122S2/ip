package command;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import funbox.exception.FunBoxExceptions;
import funbox.util.Parser;
import funbox.util.Storage;
import funbox.util.Ui;
import funbox.command.ToDoCommand;
import funbox.util.TaskList;
import funbox.task.ToDo;
import funbox.task.Task;

public class ToDoCommandTest {
    @Test
    public void execute_createTodo_correctInput() throws FunBoxExceptions {
        ToDoCommand toDoCommand = new ToDoCommand("Hello World");
        Ui uiStub = new Ui();
        TaskList taskListStub = new TaskList();
        Parser parserStub = new Parser();
        Storage storageStub = new Storage(uiStub, parserStub);
        toDoCommand.execute(taskListStub, uiStub, storageStub);
        Task testResult = taskListStub.getTaskList().get(0);
        assertEquals(testResult.toString(),
                new ToDo("Hello World", "todo").toString());

    }

    @Test
    public void execute_whenFunBoxExceptionThrow_wrongInput() {
        ToDoCommand toDoCommand = new ToDoCommand("");
        Ui uiStub = new Ui();
        TaskList taskListStub = new TaskList();
        Parser parserStub = new Parser();
        Storage storageStub = new Storage(uiStub, parserStub);
        assertThrows(FunBoxExceptions.class, () -> {
            toDoCommand.execute(taskListStub, uiStub, storageStub);
        });
    }

    @Test
    public void isExit_isFalse_correctInput() throws FunBoxExceptions {
        ToDoCommand toDoCommand = new ToDoCommand("Hello World");
        assertEquals(toDoCommand.isExit(), false);
    }
}
