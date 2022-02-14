package baron;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import baron.util.DateTimeUtil;

public class BaronTest {
    @Test
    public void getResponse_testCommands_success() {
        Baron baron = new Baron();
        assertEquals("OOPS!!! The description of todo cannot be empty.", baron.getResponse("todo"));
        assertEquals("OOPS!!! The description of todo cannot be empty.", baron.getResponse("todo "));
        String todoTaskCommand = "todo bob b" + LocalDateTime.now();
        baron.getResponse(todoTaskCommand);
        assertEquals("OOPS!!! This todo has already been recorded.", baron.getResponse(todoTaskCommand));

        assertEquals("OOPS!!! The description of event cannot be empty.", baron.getResponse("event"));
        assertEquals("OOPS!!! The description of event cannot be empty.", baron.getResponse("event "));
        String eventTaskCommand = "event bob b " + LocalDateTime.now() + " /at "
                + DateTimeUtil.getSaveString(LocalDateTime.now());
        baron.getResponse(eventTaskCommand);
        assertEquals("OOPS!!! This event has already been recorded.", baron.getResponse(eventTaskCommand));
        assertEquals("OOPS!!! The description of event cannot be empty.",
                baron.getResponse("event /at 20/2/2011 20:11"));
        assertEquals("OOPS!!! The description of event cannot be empty.", baron.getResponse("event /at"));
        assertEquals("OOPS!!! The description of event cannot be empty.", baron.getResponse("event /at 20/2/2011"));
        assertEquals("OOPS!!! Please specify a date/time by the /at keyword.",
                baron.getResponse("event read book /at"));
        assertEquals("OOPS!!! Please specify a date/time by the /at keyword.", baron.getResponse("event read book "));
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("event read book /at zxx"));
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("event read book /at 20/2/2011"));
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("event read book /at 20:11"));

        assertEquals("OOPS!!! The description of deadline cannot be empty.", baron.getResponse("deadline"));
        assertEquals("OOPS!!! The description of deadline cannot be empty.", baron.getResponse("deadline "));
        String deadlineTaskCommand = "deadline bob b " + LocalDateTime.now() + " /by "
                + DateTimeUtil.getSaveString(LocalDateTime.now());
        baron.getResponse(deadlineTaskCommand);
        assertEquals("OOPS!!! This deadline has already been recorded.", baron.getResponse(deadlineTaskCommand));
        assertEquals("OOPS!!! The description of deadline cannot be empty.",
                baron.getResponse("deadline /by 20/2/2011 20:11"));
        assertEquals("OOPS!!! The description of deadline cannot be empty.",
                baron.getResponse("deadline /by"));
        assertEquals("OOPS!!! The description of deadline cannot be empty.",
                baron.getResponse("deadline /by 20/2/2011"));
        assertEquals("OOPS!!! Please specify a date/time by the /by keyword.",
                baron.getResponse("deadline read book /by"));
        assertEquals("OOPS!!! Please specify a date/time by the /by keyword.",
                baron.getResponse("deadline read book "));
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("deadline read book /by zxx"));
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("deadline read book /by 20/2/2011"));
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("deadline read book /by 20:11"));

        assertEquals("OOPS!!! I'm sorry, but I beg your pardon?", baron.getResponse("xyz"));
    }
}
