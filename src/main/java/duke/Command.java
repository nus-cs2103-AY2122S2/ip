package duke;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

abstract class Command {

    abstract void runCommand(TaskList taskList, Ui ui, Storage storage)
            throws IOException, ParseException;

    @Override
    public String toString() {
        return null;
    }
}
