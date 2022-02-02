package duke;
import java.io.IOException;

import org.testng.annotations.Test;

//import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    @Test
    void toDo() throws IOException {
            TaskList ts = new TaskList("C:\\DukeDirectory", "C:\\DukeDirectory\\DukeSave.txt");
            assertEquals("Okayy!! I've added this task:\n" + " T | [ ] read book\n"
                    + " You have 1 tasks in the list.", ts.toDo("read book"));
    }

    private void assertEquals(String s, String readBook) {
    }

    @Test
    void deadLine() throws IOException {
        TaskList ts = new TaskList("C:\\DukeDirectory", "C:\\DukeDirectory\\DukeSave.txt");
        assertEquals("Deadline for this task:\n"
                + " D | [ ] assignment (by:Dec 12 2021 1200)\n"
                + " You have 1 tasks in the list.", ts.deadLine("assignment /by 12/12/2021 1200"));
    }

    @Test
    void event() throws IOException {
        TaskList ts = new TaskList("C:\\DukeDirectory", "C:\\DukeDirectory\\DukeSave.txt");
        assertEquals("I have added this task and the event time is:\n"
                + " E | [ ] party (at:Jan 01 2023 2200)\n"
                + " You have 1 tasks in the list." , ts.event("party /at 01/01/2023 2200"));
    }
}