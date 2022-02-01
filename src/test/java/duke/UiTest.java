package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import duke.exception.DukeIllegalArgumentException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeIoException;
import duke.testutil.PrinterStub;
import duke.ui.Ui;
import duke.util.Printable;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UiTest {
    private Ui initMockUi() throws DukeIoException {
        Ui ui = mock(Ui.class);
        Stage stage = mock(Stage.class);
        doNothing().when(stage).setScene(any(Scene.class));
        ui.buildStage(stage, (input) -> {});
        return ui;
    }

    @Test
    public void testGreet() throws DukeIoException {
        Ui ui = initMockUi();
        ArrayList<String> lines = new ArrayList<>();
        when(ui.printCommand(any())).thenAnswer((invocation) -> {
            invocation.<Function<Printable, Boolean>>getArgument(0)
                    .apply((message) -> {
                        lines.add(message);
                    });
            return true;
        });
        when(ui.greet()).thenCallRealMethod();
        ui.greet();
        String actual = lines.stream().reduce((x, y) -> x + "\n" + y).orElse("");
        assertEquals("Hi! I'm Megumin\n"
                + "What do you need?", actual);
    }

    @Test
    public void testPrintCommand() throws DukeIoException {
        Ui ui = initMockUi();
        ArrayList<String> lines = new ArrayList<>();
        when(ui.printCommand(any())).thenAnswer((invocation) -> {
            invocation.<Function<Printable, Boolean>>getArgument(0)
                    .apply((message) -> {
                        lines.add(message);
                    });
            return true;
        });

        ui.printCommand((linePrinter) -> {
            linePrinter.print("test line");
            return false;
        });

        String actual = lines.stream().reduce((x, y) -> x + "\n" + y).orElse("");
        assertEquals("test line", actual);
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
