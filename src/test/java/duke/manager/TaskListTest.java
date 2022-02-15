package duke.manager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.task.ToDo;

public class TaskListTest {

    @Test
    public void testNumOfTasks() throws Exception {
        ToDo toDo = new ToDo("borrow book");
        TaskList taskList = new TaskList();

        //test case 1
        for (int i = 0; i < 10; i++) {
            taskList.add(toDo);
        }
        assertEquals(10, taskList.numOfTasks());

        //test case 2
        taskList = new TaskList();
        for (int i = 0; i < 101; i++) {
            taskList.add(toDo);
        }
        assertEquals(101, taskList.numOfTasks());

        //test case 3
        taskList = new TaskList();
        taskList.add(toDo);
        assertEquals(1, taskList.numOfTasks());
    }

    @Test
    public void testDeleteTask() throws Exception {
        ToDo toDo1 = new ToDo("1");
        ToDo toDo2 = new ToDo("2");
        ToDo toDo3 = new ToDo("3");
        TaskList taskList = new TaskList();

        //test case 1
        taskList.add(toDo1);
        assertEquals(toDo1, taskList.delete(0));

        //test case 1
        taskList.add(toDo3);
        taskList.add(toDo2);
        assertEquals(toDo3, taskList.delete(0));

        //test case 3
        taskList = new TaskList();
        taskList.add(toDo2);
        taskList.add(toDo1);
        taskList.add(toDo3);
        assertEquals(toDo1, taskList.delete(1));
    }

    @Test void testMarkDone() throws Exception {
        ToDo toDo = new ToDo("return book");
        TaskList taskList = new TaskList();

        //test case 1
        taskList.add(toDo);
        taskList.markDone(0);
        toDo.markDone();
        assertEquals(toDo.toString(), taskList.getTask(0).toString());
    }
}
