import main.DukeException;
import main.commands.CBye;
import main.commands.CDeadline;
import main.commands.Command;
import main.io.Parser;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

public class DukeTest {
    private static Parser parser = new Parser();

    @Test
    public void parseByeTest(){
        Command bye = new CBye();
        try{
            Command parseBye = parser.parse("bye");
            assertEquals(bye.getClass(), parseBye.getClass());
        }
        catch(DukeException e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void parseDeadlineTest() {
        try {
            Command parseDeadline = parser.parse("deadline test /by 1111-11-11 1111");
            assertEquals(parseDeadline.getClass(), CDeadline.class);
        } catch (DukeException dukeException) {
            System.out.println(dukeException.getMessage());
        }
    }
}