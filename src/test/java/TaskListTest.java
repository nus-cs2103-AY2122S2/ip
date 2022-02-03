import tsohg.TsohgException;
import tsohg.TaskList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void addTodo() {
        try {
            TaskList taskList = new TaskList(new StorageStub("data.txt"));
            taskList.addTodo("Item1");
            taskList.addTodo("Item2");
            assertEquals("1.[T][ ] Item1\n2.[T][ ] Item2\n", taskList.toString());
        } catch (TsohgException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void addDeadline() {
        try {
            TaskList taskList = new TaskList(new StorageStub("data.txt"));
            taskList.addDeadline("Item1", "2022-01-01");
            taskList.addDeadline("Item2", "2022-02-02");
            assertEquals("1.[D][ ] Item1 (by: Jan 01 2022)\n2.[D][ ] Item2 (by: Feb 02 2022)\n",
                    taskList.toString());
        } catch (TsohgException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void deleteItem() {
        try {
            TaskList taskList = new TaskList(new StorageStub("data.txt"));
            taskList.addTodo("Item1");
            taskList.addTodo("Item2");
            taskList.deleteItem(0);
            assertEquals("1.[T][ ] Item2\n", taskList.toString());
        } catch (TsohgException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void markItem() {
        try {
            TaskList taskList = new TaskList(new StorageStub("data.txt"));
            taskList.addTodo("Item1");
            taskList.addTodo("Item2");
            taskList.markItem(0);
            assertEquals("1.[T][X] Item1\n2.[T][ ] Item2\n", taskList.toString());
        } catch (TsohgException e) {
            e.printStackTrace();
        }
    }

}
