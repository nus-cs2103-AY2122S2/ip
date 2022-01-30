package CommandSet;

import org.junit.jupiter.api.Test;

import static CommandSet.Commands.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class CommandCheckerTest {

    /**
     * tests if the yes or no userinput is properly recognised.
     */
    @Test
    public void yesOrNoChecker_userInputs_yesOrNo() {
        assertEquals(YES, CommandChecker.yesOrNoChecker("yes"));
        assertEquals(NO, CommandChecker.yesOrNoChecker("no"));
    }

    /**
     * tests if the commands are properly recognised.
     */
    @Test
    public void findAndCheck_userInputs_Commands() {
        assertEquals(ADD, CommandChecker.findAndCheck("todo return textbooks"));
        assertEquals(BYE, CommandChecker.findAndCheck("bye"));
        assertEquals(MARK, CommandChecker.findAndCheck("mark 4"));
        assertEquals(UNMARK, CommandChecker.findAndCheck("unmark 5"));
        assertEquals(DELETE, CommandChecker.findAndCheck("delete 3"));
        assertEquals(DUEON, CommandChecker.findAndCheck("due-on 2022-10-04"));
        assertEquals(DUEBEFORE, CommandChecker.findAndCheck("due-before 2022-11-05"));
        assertEquals(INVALID, CommandChecker.findAndCheck("random"));
    }
}
