package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import duke.exception.DukeIllegalArgumentException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeIoException;
import duke.testutil.PrinterStub;

public class UiTest {
    @Test
    public void testGreet() throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Ui.getInstance().greet();
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("\t------------------------------------\r\n"
                + "\tHi! I'm Megumin\r\n"
                + "\tWhat do you need?\r\n"
                + "\t------------------------------------\r\n\r\n", lines);
    }

    @Test
    public void testPrintCommand() throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));

        Ui.getInstance().printCommand((linePrinter) -> {
            linePrinter.print("test line");
            return false;
        });
        outStream.flush();
        String lines = outStream.toString();
        assertEquals("\t------------------------------------\r\n"
                + "\ttest line\r\n"
                + "\t------------------------------------\r\n", lines);
    }

    @Test
    public void testPrintError() throws IOException {
        PrinterStub printer = new PrinterStub();
        Ui.getInstance().printError(printer, new DukeInvalidCommandException("error 1"));
        Ui.getInstance().printError(printer, new DukeIllegalArgumentException("error 2"));
        Ui.getInstance().printError(printer, new DukeIoException("error 3"));

        assertEquals("I do not understand you!", printer.getLines().get(0));
        assertEquals("There was a problem understanding you:", printer.getLines().get(1));
        assertEquals("error 2", printer.getLines().get(2));
        assertEquals("I had a problem reading / writing my memory!", printer.getLines().get(3));
    }
}
