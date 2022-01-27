package duke.testutil;

import duke.util.Printable;

import java.util.ArrayList;

/**
 * Provides a dummy printable object for test scripts to print into.
 */
public class PrinterStub implements Printable {
    private final ArrayList<String> lines;

    public PrinterStub() {
        this.lines = new ArrayList<>();
    }

    @Override
    public void print(String input) {
        lines.add(input);
    }

    public ArrayList<String> getLines() {
        return this.lines;
    }

    public int lineCount() {
        return this.lines.size();
    }

    public PrinterStub clear() {
        this.lines.clear();
        return this;
    }
}
