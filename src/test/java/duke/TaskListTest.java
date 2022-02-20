package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void list() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Event("party ", LocalDate.of(2022, 12, 12)));
        tasks.add(new ToDo("borrow book"));

        TaskList t = new TaskList(tasks);

        assertEquals("Here are the tasks in your list:\n    1. [E][ ] party (Dec 12 2022)\n    2. [T][ ] borrow book", t.list());
    }

    @Test
    void mark() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        tasks.add(new Event("party ", LocalDate.of(2022, 12, 12)));
        tasks.add(new ToDo("borrow book"));

        TaskList t = new TaskList(tasks);
        try {
            tasks.get(0).mark();
            assertEquals("Nice! I've marked this task as done:\n      " + tasks.get(0), t.mark(1));
        } catch (Exception e) {
            System.out.println("mark failure");
        }
    }
}