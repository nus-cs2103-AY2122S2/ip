package duke;

import java.io.IOException;

public class UnmarkCommand extends Command {
    UnmarkCommand(String commandArgument) {
        super(commandArgument);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        try {
            int indexItemToBeUnmarked = Integer.parseInt(commandArgument) +
                    ui.TURN_ONE_BASED_INDEXING_TO_ZERO_BASED_INDEXING;
            taskList.getAllTask().get(indexItemToBeUnmarked).markTaskAsUndone();
            storage.save(taskList);
        } catch (NumberFormatException nfe) {
            throw new NumberFormatException("something");
        } catch (IndexOutOfBoundsException iobe) {
            throw new IndexOutOfBoundsException("something later");
        } catch (IOException ioe) {
            throw new IOException("running out of things to say");
        }
    }
}
