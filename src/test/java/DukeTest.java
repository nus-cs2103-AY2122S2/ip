import org.junit.jupiter.api.Test;

import javax.swing.text.html.parser.Parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DukeTest {
    @Test
    public void initialisationTest(){
        try{
            Duke d = new Duke("Test/test.txt");
        } catch (Exception e) {
            fail("No exception expected");
        }
    }

}
