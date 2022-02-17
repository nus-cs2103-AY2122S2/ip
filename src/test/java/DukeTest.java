import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import duke.sonautil.DukeException;
import duke.sonautil.Parser;
import duke.sonautil.TaskList;

public class DukeTest {

    @Test
    public void testParser() throws DukeException {

        Parser p = new Parser();

        //test case 1: "list"
        assertTrue(Arrays.equals(new String[]{"list", null, null, null}, p.processMessage("list")));

        //test case 2: "mark 1"
        assertTrue(Arrays.equals(new String[]{"mark", "0", null, null}, p.processMessage("mark 1")));

        //test case 3: "unmark 1"
        assertTrue(Arrays.equals(new String[]{"unmark", "0", null, null}, p.processMessage("unmark 1")));

        //test case 4: "todo play"
        assertTrue(Arrays.equals(new String[]{"todo", "play", null, null}, p.processMessage("todo play")));

        //test case 5: "deadline homework /2022-01-01"
        assertTrue(Arrays.equals(new String[]{"deadline", "homework ", "2022-01-01T23:59", "false"},
                p.processMessage("deadline homework /2022-01-01")));

        //test case 6: "deadline homework/2022-01-01 1800"
        assertTrue(Arrays.equals(new String[]{"deadline", "homework ", "2022-01-01T18:00", "true"},
                p.processMessage("deadline homework /2022-01-01 1800")));

        //test case 7: "event party /2022-01-03"
        assertTrue(Arrays.equals(new String[]{"event", "party ", "2022-01-03T00:00", "false"},
                p.processMessage("event party /2022-01-03")));

        //test case 8: "event party /2022-01-03 1930"
        assertTrue(Arrays.equals(new String[]{"event", "party ", "2022-01-03T19:30", "true"},
                p.processMessage("event party /2022-01-03 1930")));

        //test case 9: "delete 2"
        assertTrue(Arrays.equals(new String[]{"delete", "1", null, null},
                p.processMessage("delete 2")));

        //test case 10: "schedule 2022-01-02"
        assertTrue(Arrays.equals(new String[]{"schedule", "2022-01-02", null, null},
                p.processMessage("schedule 2022-01-02")));
    }

    @Test
    public void testTaskList() throws DukeException {

        TaskList list = new TaskList();

        //test case 1.1: add todo Duke.task
        list.executeCommand(new String[]{"todo", "play", null, null});
        assertEquals("[T][ ] play", list.getTask(0).toString());

        //test case 1.2: add deadline
        list.executeCommand(new String[]{"deadline", "homework ", "2022-03-01T23:59", "false"});
        assertEquals("[D][ ] homework (by: Mar 1 2022 23:59)", list.getTask(1).toString());

        //test case 1.3: add event
        list.executeCommand(new String[]{"event", "party", "2022-02-02T18:30", "true"});
        assertEquals("[E][ ] party (at: Feb 2 2022 18:30)", list.getTask(2).toString());

        //test case 2: mark
        list.executeCommand(new String[]{"mark", "1", null, null});
        assertEquals("[D][X] homework (by: Mar 1 2022 23:59)", list.getTask(1).toString());

        //test case 3: unmark
        list.executeCommand(new String[]{"unmark", "1", null, null});
        assertEquals("[D][ ] homework (by: Mar 1 2022 23:59)", list.getTask(1).toString());

        //test case 4: delete
        list.executeCommand(new String[]{"delete", "0", null, null});
        assertEquals("[D][ ] homework (by: Mar 1 2022 23:59)", list.getTask(0).toString());

    }


}
