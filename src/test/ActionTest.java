import duke.action.Action;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionTest {
    @Test
    public void getStatus_unmarkedAction_singleWhiteSpace() {
        Action action = new Action("Action");
        String result = action.getStatus();
        assertEquals(" ", result);
    }

    @Test
    public void testSetDone() {
        Action action = new Action("Action");
        action = action.setDone();
        String result = action.getStatus();
        assertEquals("X", result);
    }

    @Test
    public void testSetUndone() {
        Action action = new Action("Action");
        action = action.setDone().setUnDone();
        String result = action.getStatus();
        assertEquals(" ", result);
    }
}
