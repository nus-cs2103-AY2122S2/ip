import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import bro.Parser;
import bro.exceptions.BroException;
import bro.tasks.Deadline;
import bro.tasks.Event;
import bro.tasks.Todo;

public class ParserTest {

    @Test
    public void parseToTask_emptyInput_null() throws Exception {
        assertNull(Parser.parseToTask(""));
    }
    @Test
    public void parseToTask_invalidTaskType_null() throws Exception {
        assertNull(Parser.parseToTask("anyinput TaskName /by 11/11/2011 1500"));
        assertNull(Parser.parseToTask("anyinput TaskName /at 11/11/2011 1500"));
    }
    @Test
    public void parseToTask_emptyTaskName_exceptionThrown() {
        try {
            Parser.parseToTask("todo ");
        } catch (BroException e) {
            assertEquals("Todo Name is empty!", e.getMessage());
        }

        try {
            Parser.parseToTask("deadline  /by 11/11/2011 1500");
        } catch (BroException e) {
            assertEquals("Deadline Name is empty!", e.getMessage());
        }

        try {
            Parser.parseToTask("event  /at 11/11/2011 1500");
        } catch (BroException e) {
            assertEquals("Event Name is empty!", e.getMessage());
        }
    }
    @Test
    public void parseToTask_emptyDate_exceptionThrown() {
        try {
            Parser.parseToTask("deadline name /by ");
        } catch (BroException e) {
            assertEquals("No Date Specified!", e.getMessage());
        }

        try {
            Parser.parseToTask("deadline name /by");
        } catch (BroException e) {
            assertEquals("Wrong format entered! Please enter <Deadline Name> /by <Deadline>", e.getMessage());
        }

        try {
            Parser.parseToTask("event name /at");
        } catch (BroException e) {
            assertEquals("Wrong format entered! Please enter <Event Name> /at <Event Date>", e.getMessage());
        }

        try {
            Parser.parseToTask("event name /at ");
        } catch (BroException e) {
            assertEquals("No Date Specified!", e.getMessage());
        }
    }
    @Test
    public void parseToTask_wrongDateFormat_exceptionThrown() {
        try {
            Parser.parseToTask("deadline name /by 11112011 1500");
        } catch (BroException e) {
            assertEquals("Invalid Date format! Please enter Date/Time in the form DD/MM/YYYY HHMM",
                    e.getMessage());
        }

        try {
            Parser.parseToTask("deadline name /by 11-11-2011 1500");
        } catch (BroException e) {
            assertEquals("Invalid Date format! Please enter Date/Time in the form DD/MM/YYYY HHMM",
                    e.getMessage());
        }

        try {
            Parser.parseToTask("deadline name /by 11/11/2011 15:00");
        } catch (BroException e) {
            assertEquals("Invalid Date format! Please enter Date/Time in the form DD/MM/YYYY HHMM",
                    e.getMessage());
        }

        try {
            Parser.parseToTask("deadline name /by 11/11/11 1500");
        } catch (BroException e) {
            assertEquals("Invalid Date format! Please enter Date/Time in the form DD/MM/YYYY HHMM",
                    e.getMessage());
        }

    }
    @Test
    public void parseToTask_wrongSeparator_exceptionThrown() {
        try {
            Parser.parseToTask("deadline name /at 11/11/2011 1500");
        } catch (BroException e) {
            assertEquals("Wrong format entered! Please enter <Deadline Name> /by <Deadline>",
                    e.getMessage());
        }

        try {
            Parser.parseToTask("event name /by 11/11/2011 1500");
        } catch (BroException e) {
            assertEquals("Wrong format entered! Please enter <Event Name> /at <Event Date>",
                    e.getMessage());
        }
    }
    @Test
    public void parseToTask_success() throws Exception {
        Todo todo = new Todo("Todo Name");
        LocalDateTime date = LocalDateTime.parse("2011-11-11T15:00:00");
        Event event = new Event("Event Name", date);
        Deadline deadline = new Deadline("Deadline Name", date);

        assertEquals(todo.toString(),
                Parser.parseToTask("todo Todo Name").toString());
        assertEquals(event.toString(),
                Parser.parseToTask("event Event Name /at 11/11/2011 1500").toString());
        assertEquals(deadline.toString(),
                Parser.parseToTask("deadline Deadline Name /by 11/11/2011 1500").toString());

    }


    @Test
    public void parseToTaskFromFile_emptyWrongType_exceptionThrown() {

        try {
            Parser.parseToTaskFromFile("F\tX\tTask Name\tNone");
            fail();
        } catch (BroException e) {
            assertEquals("Unable to load task from file!", e.getMessage());
        }

        try {
            Parser.parseToTaskFromFile("G\tX\tTask Name\tNone");
            fail();
        } catch (BroException e) {
            assertEquals("Unable to load task from file!", e.getMessage());
        }

        try {
            Parser.parseToTaskFromFile("\tX\tTask Name\t11/11/2011 1500");
            fail();
        } catch (BroException e) {
            assertEquals("Unable to load task from file!", e.getMessage());
        }
    }


    @Test
    public void parseDateTime_success() {
        assertEquals(LocalDateTime.parse("2011-11-11T15:00:00"), Parser.parseDateTime("11/11/2011 1500"));
    }

    @Test
    public void parseToFileFromTask_success() {
        Todo todo = new Todo("Todo Name");
        todo.markDone();
        LocalDateTime date = LocalDateTime.parse("2011-11-11T15:00:00");
        Event event = new Event("Event Name", date);
        Event eventDateStr = new Event("Event Name String", "event date");
        Deadline deadline = new Deadline("Deadline Name", date);
        Deadline deadlineDateStr = new Deadline("Deadline Name String", "deadline");

        assertEquals("T\tX\tTodo Name\tNone", Parser.parseToFileFromTask(todo));
        assertEquals("E\t \tEvent Name\t11/11/2011 1500", Parser.parseToFileFromTask(event));
        assertEquals("E\t \tEvent Name String\tevent date", Parser.parseToFileFromTask(eventDateStr));
        assertEquals("D\t \tDeadline Name\t11/11/2011 1500", Parser.parseToFileFromTask(deadline));
        assertEquals("D\t \tDeadline Name String\tdeadline", Parser.parseToFileFromTask(deadlineDateStr));
    }
}
