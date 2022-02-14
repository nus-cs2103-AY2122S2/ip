package duke;

import duke.tasks.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TaskListTest {

//    @Test
//    public void addTaskTest(){
//        TaskList tasks = new TaskList();
//        Todo todo = new Todo("false", "Dummy Description");
//        tasks.addTask(todo);
//        assertEquals(todo.getIsDone(), true);
//    }
//
//    @Test
//    public void removeListTest(){
//
//    }


    @Test
    public void markTest(){
        TaskList tasks = new TaskList();
        Todo todo = new Todo("false", "read book");
        tasks.addTask(todo);
        tasks.mark(0);
        assertEquals(todo.getIsDone(), true);
    }

    @Test
    public void unmarkTest(){
        TaskList tasks = new TaskList();
        Todo todo = new Todo("true", "read book");
        tasks.addTask(todo);
        tasks.unmark(0);
        assertEquals(todo.getIsDone(), false);
    }

}
