package stevie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import stevie.command.ExitCommand;
import stevie.exception.StevieException;

class StevieParserTest {
    @Test
    void parse() throws StevieException {
        StevieUiStub ui = new StevieUiStub();
        assertEquals(new ExitCommand().execute(null, null, ui),
                StevieParser.parse("bye").execute(null, null, ui));
    }
}
