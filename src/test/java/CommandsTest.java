import chibot.commands.AddCommand;
import chibot.commands.MarkCommand;
import chibot.commands.UnmarkCommand;
import chibot.tasklist.TaskListStub;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CommandsTest {

    @Test
    void outputString_getDescriptionOfDeadline_correctStringReturned() {
        String[] tokens = {"place", "holder"};
        AddCommand toTest = new AddCommand(tokens);
        assertEquals("correct", toTest.getDescription("correct /by 2022-01-01 12:00", "deadline"));
    }

    @Test
    void boolean_invalidMarkCommandBody_falseReturned() {
        String[] tokens = {"12"};
        MarkCommand toTest = new MarkCommand(tokens);
        TaskListStub tl = new TaskListStub();
        assertFalse(toTest.validateMessageBody("12", tl));
    }

    @Test
    void boolean_nanPassedIntoUnmarkCommand_falseReturned() {
        String[] tokens = {"Non-number"};
        UnmarkCommand toTest = new UnmarkCommand(tokens);
        TaskListStub tl = new TaskListStub();
        assertFalse(toTest.validateMessageBody("Non-number", tl));
    }
}
