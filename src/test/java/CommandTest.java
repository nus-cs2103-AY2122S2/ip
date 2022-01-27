import tesseract.command.Command;
import tesseract.main.TesseractException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandTest {
    @Test
    public void process() {
        try {
            Command.process("event /at 2022-01-34", 3);
        } catch (TesseractException e) {
            Assertions.fail(e.getErrMsg());
        }
    }
}
