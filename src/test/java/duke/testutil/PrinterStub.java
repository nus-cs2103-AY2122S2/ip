package duke.testutil;

import java.util.ArrayList;

import duke.util.Printable;

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

    /**
     * Clears all the lines that have been printed to this instance.
     *
     * @return This instance of PrinterStub.
     */
    public PrinterStub clear() {
        this.lines.clear();
        return this;
    }
}
