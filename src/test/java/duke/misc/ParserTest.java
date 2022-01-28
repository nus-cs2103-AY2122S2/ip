package duke.misc;

import static duke.misc.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    public void testParse_listSuccess() {
        TaskList listOfTasks = new TaskList(100);
        try {
            Storage.initFileFolder();
            parse("list", listOfTasks);
            assertEquals(0, listOfTasks.getNumberOfTasks());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_listExceptionThrown() {
        TaskList listOfTasks = new TaskList(100);
        try {
            Storage.initFileFolder();
            parse("list x", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("This command should not have any arguments :(", e.getMessage());
        }
    }

    @Test
    public void testParse_todoSuccess() {
        TaskList listOfTasks = new TaskList(100);
        try {
            Storage.initFileFolder();
            parse("todo x", listOfTasks);
            assertEquals("[[T][ ] x]", listOfTasks.getListOfTasks().toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_todoExceptionThrown() {
        TaskList listOfTasks = new TaskList(100);
        try {
            Storage.initFileFolder();
            parse("todo", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("The description of a todo cannot be empty :(", e.getMessage());
        }
    }

    @Test
    public void testParse_deadlineDateOnlySuccess() {
        TaskList listOfTasks = new TaskList(100);
        try {
            Storage.initFileFolder();
            parse("deadline correct format date only /by 2022-12-12", listOfTasks);
            assertEquals("[[D][ ] correct format date only (by: Dec 12 2022)]",
                    listOfTasks.getListOfTasks().toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_deadlineDateTimeSuccess() {
        TaskList listOfTasks = new TaskList(100);
        try {
            Storage.initFileFolder();
            parse("deadline correct format date and time /by 2022-12-12 1200", listOfTasks);
            assertEquals("[[D][ ] correct format date and time (by: Dec 12 2022 12:00)]",
                    listOfTasks.getListOfTasks().toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_deadlineExceptionThrown() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("deadline", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Incorrect number of arguments supplied :(", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("deadline task without /_by", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please follow this format: deadline <task> "
                    + "/by <date in yyyy-MM-dd> <time in 24hrs format>", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("deadline incomplete without date /by", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter date in this format: <yyyy-MM-dd>", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("deadline wrong date format /by 2022/12/12", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter date in this format: <yyyy-MM-dd>", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("deadline invalid date /by 1111-11-11", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("You cannot travel back in time!", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("deadline wrong time format /by 2022-12-12 12:10pm", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter time in this 24hrs-format: <HHmm>", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("deadline invalid time /by 2022-12-12 9999", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter time in this 24hrs-format: <HHmm>", e.getMessage());
        }

    }

    @Test
    public void testParse_eventDateOnlySuccess() {
        TaskList listOfTasks = new TaskList(100);
        try {
            Storage.initFileFolder();
            parse("event correct format date only /at 2022-12-12", listOfTasks);
            assertEquals("[[E][ ] correct format date only (at: Dec 12 2022)]",
                    listOfTasks.getListOfTasks().toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_eventStartTimeSuccess() {
        TaskList listOfTasks = new TaskList(100);
        try {
            Storage.initFileFolder();
            parse("event correct format date and start time /at 2022-12-12 1200", listOfTasks);
            assertEquals("[[E][ ] correct format date and start time (at: Dec 12 2022 12:00)]",
                    listOfTasks.getListOfTasks().toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_eventStartEndTimeSuccess() {
        TaskList listOfTasks = new TaskList(100);
        try {
            Storage.initFileFolder();
            parse("event correct format date and start/end time /at 2022-12-12 1200 1300", listOfTasks);
            assertEquals("[[E][ ] correct format date and start/end time (at: Dec 12 2022 12:00-13:00)]",
                    listOfTasks.getListOfTasks().toString());
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    public void testParse_eventExceptionThrown() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("event", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Incorrect number of arguments supplied :(", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("event task without /_at", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please follow this format: event <task> "
                    + "/at <date in yyyy-MM-dd> <time in 24hrs format>", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("event incomplete without date /at", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter date in this format: <yyyy-MM-dd>", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("event wrong date format /at 2022/12/12", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter date in this format: <yyyy-MM-dd>", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("event invalid date /at 1111-11-11", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("You cannot travel back in time!", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("event wrong start time format /at 2022-12-12 12:10pm", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter time in this 24hrs-format: <HHmm>", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("event wrong end time format /at 2022-12-12 1200 12:22pm", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter time in this 24hrs-format: <HHmm>", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("event invalid start time /at 2022-12-12 9999", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("Please enter time in this 24hrs-format: <HHmm>", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("event invalid end time /at 2022-12-12 1200 1100", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("The end time must be after the start time!", e.getMessage());
        }
    }

    @Test
    public void testParse_markSuccess() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("mark 1", listOfTasks);
            assertEquals("[[T][X] borrow book]",
                    listOfTasks.getListOfTasks().toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_markExceptionThrown() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("mark", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("This command should have exactly 1 argument.", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("mark 0", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("The specified task ID is out of range. Please enter a number from 0 to 1.",
                    e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("mark x", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("The argument MUST contain a single integer.", e.getMessage());
        }
    }

    @Test
    public void testParse_unmarkSuccess() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("mark 1", listOfTasks);
            parse("unmark 1", listOfTasks);
            assertEquals("[[T][ ] borrow book]",
                    listOfTasks.getListOfTasks().toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_unmarkExceptionThrown() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("mark 1", listOfTasks);
            parse("unmark", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("This command should have exactly 1 argument.", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("mark 1", listOfTasks);
            parse("unmark 0", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("The specified task ID is out of range. Please enter a number from 0 to 1.",
                    e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("mark 1", listOfTasks);
            parse("unmark x", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("The argument MUST contain a single integer.", e.getMessage());
        }
    }

    @Test
    public void testParse_deleteSuccess() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("delete 1", listOfTasks);
            assertEquals("[]",
                    listOfTasks.getListOfTasks().toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_deleteExceptionThrown() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("delete", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("This command should have exactly 1 argument.", e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("delete 0", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("The specified task ID is out of range. Please enter a number from 0 to 1.",
                    e.getMessage());
        }

        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("delete x", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("The argument MUST contain a single integer.", e.getMessage());
        }
    }

    @Test
    public void testParse_exceptionThrown() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("this is an invalid command", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("I'm sorry, but I don't know what that means :(", e.getMessage());
        }
    }

    @Test
    public void testParse_findSuccess() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("todo read book", listOfTasks);
            parse("find book", listOfTasks);
            assertEquals("[[T][ ] borrow book, [T][ ] read book]",
                    listOfTasks.getListOfTasks().toString());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParse_findExceptionThrown() {
        try {
            TaskList listOfTasks = new TaskList(100);
            Storage.initFileFolder();
            parse("todo borrow book", listOfTasks);
            parse("todo read book", listOfTasks);
            parse("find", listOfTasks);
            fail();
        } catch (Exception e) {
            assertEquals("The search field cannot be empty :(", e.getMessage());
        }
    }
}

