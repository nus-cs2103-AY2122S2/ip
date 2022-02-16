package baron;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import baron.util.DateTimeUtil;

public class BaronTest {
    @Test
    public void getResponse_invalidCommand_invalidCommandWarningMessage() {
        Baron baron = new Baron();
        assertEquals("OOPS!!! I'm sorry, but I beg your pardon?", baron.getResponse("xyz"));
    }

    @Test
    public void getResponse_emptyTodoDescription_emptyTodoDescriptionWarningMessage() {
        Baron baron = new Baron();
        assertEquals("OOPS!!! The description of todo cannot be empty.", baron.getResponse("todo"));
        assertEquals("OOPS!!! The description of todo cannot be empty.", baron.getResponse("todo "));
    }

    @Test
    public void getResponse_emptyEventDescription_emptyEventDescriptionWarningMessage() {
        Baron baron = new Baron();
        assertEquals("OOPS!!! The description of event cannot be empty.", baron.getResponse("event"));
        assertEquals("OOPS!!! The description of event cannot be empty.", baron.getResponse("event "));
        assertEquals("OOPS!!! The description of event cannot be empty.",
                baron.getResponse("event /at 20/2/2011 20:11"));
        assertEquals("OOPS!!! The description of event cannot be empty.", baron.getResponse("event /at"));
        assertEquals("OOPS!!! The description of event cannot be empty.", baron.getResponse("event /at 20/2/2011"));
    }

    @Test
    public void getResponse_emptyDeadlineDescription_emptyDeadlineDescriptionWarningMessage() {
        Baron baron = new Baron();
        assertEquals("OOPS!!! The description of deadline cannot be empty.", baron.getResponse("deadline"));
        assertEquals("OOPS!!! The description of deadline cannot be empty.", baron.getResponse("deadline "));
        assertEquals("OOPS!!! The description of deadline cannot be empty.",
                baron.getResponse("deadline /by 20/2/2011 20:11"));
        assertEquals("OOPS!!! The description of deadline cannot be empty.",
                baron.getResponse("deadline /by"));
        assertEquals("OOPS!!! The description of deadline cannot be empty.",
                baron.getResponse("deadline /by 20/2/2011"));
    }

    @Test
    public void getResponse_duplicateTodo_duplicateTodoWarningMessage() {
        Baron baron = new Baron();
        String todoTaskCommand = "todo bob b" + LocalDateTime.now();
        baron.getResponse(todoTaskCommand);
        assertEquals("OOPS!!! This todo has already been recorded.", baron.getResponse(todoTaskCommand));
    }

    @Test
    public void getResponse_duplicateDeadline_duplicateDeadlineWarningMessage() {
        Baron baron = new Baron();
        String eventTaskCommand = "event bob b " + LocalDateTime.now() + " /at "
                + DateTimeUtil.getSaveString(LocalDateTime.now());
        baron.getResponse(eventTaskCommand);
        assertEquals("OOPS!!! This event has already been recorded.", baron.getResponse(eventTaskCommand));

    }

    @Test
    public void getResponse_duplicateEvent_duplicateEventWarningMessage() {
        Baron baron = new Baron();
        String deadlineTaskCommand = "deadline bob b " + LocalDateTime.now() + " /by "
                + DateTimeUtil.getSaveString(LocalDateTime.now());
        baron.getResponse(deadlineTaskCommand);
        assertEquals("OOPS!!! This deadline has already been recorded.", baron.getResponse(deadlineTaskCommand));
    }

    @Test
    public void getResponse_missingEventDateTime_missingEventDateTimeWarningMessage() {
        Baron baron = new Baron();
        assertEquals("OOPS!!! Please specify a date/time by the /at keyword.",
                baron.getResponse("event read book /at"));
        assertEquals("OOPS!!! Please specify a date/time by the /at keyword.", baron.getResponse("event read book "));
    }

    @Test
    public void getResponse_missingDeadlineDateTime_missingDeadlineDateTimeWarningMessage() {
        Baron baron = new Baron();
        assertEquals("OOPS!!! Please specify a date/time by the /by keyword.",
                baron.getResponse("deadline read book /by"));
        assertEquals("OOPS!!! Please specify a date/time by the /by keyword.",
                baron.getResponse("deadline read book "));
    }

    @Test
    public void getResponse_eventWrongDateTimeFormat_wrongDateTimeFormatWarningMessage() {
        Baron baron = new Baron();
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("event read book /at zxx"));
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("event read book /at 20/2/2011"));
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("event read book /at 20:11"));
    }

    @Test
    public void getResponse_deadlineWrongDateTimeFormat_wrongDateTimeFormatWarningMessage() {
        Baron baron = new Baron();
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("deadline read book /by zxx"));
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("deadline read book /by 20/2/2011"));
        assertEquals("OOPS!!! The date/time should be in the following format: d/M/yyyy HH:mm",
                baron.getResponse("deadline read book /by 20:11"));
    }
}
