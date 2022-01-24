package stevie;

import org.junit.jupiter.api.Test;
import stevie.command.ExitCommand;
import stevie.exception.StevieException;

import static org.junit.jupiter.api.Assertions.*;

class StevieParserTest {

    @Test
    void parse() throws StevieException {
        StevieUiStub ui = new StevieUiStub();
        assertEquals(new ExitCommand().execute(null, null, ui),
                StevieParser.parse("bye").execute(null, null, ui));
    }
}