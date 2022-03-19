import duke.Checker;
import duke.DukeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckerTest {

    @Test
    public void checkerTest() throws DukeException {
        try {
            Checker checker = new Checker("delete");
            assertEquals(Checker.Status.DELETE, checker.getStatus());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        try {
            Checker checker = new Checker("event");
            assertEquals(Checker.Status.EVENT, checker.getStatus());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        try {
            Checker checker = new Checker("bye");
            assertEquals(Checker.Status.BYE, checker.getStatus());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        try {
            Checker checker = new Checker("help");
            assertEquals(Checker.Status.HELP, checker.getStatus());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        try {
            Checker checker = new Checker("deadline");
            assertEquals(Checker.Status.DEADLINE, checker.getStatus());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        try {
            Checker checker = new Checker("list");
            assertEquals(Checker.Status.LIST, checker.getStatus());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

        try {
            Checker checker = new Checker("unmark");
            assertEquals(Checker.Status.UNMARK, checker.getStatus());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        try {
            Checker checker = new Checker("mark");
            assertEquals(Checker.Status.MARK, checker.getStatus());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
