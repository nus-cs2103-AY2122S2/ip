package duke.testutil;

import duke.util.IPrintable;

import java.util.ArrayList;

public class PrinterStub implements IPrintable {
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
