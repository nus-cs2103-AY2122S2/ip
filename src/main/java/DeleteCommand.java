import java.io.IOException;

public class DeleteCommand extends Command {
    DeleteCommand(String commandArgument) {
        super(commandArgument);
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws IOException {
        try {
            int indexItemToBeDeleted = Integer.parseInt(commandArgument) +
                    ui.TURN_ONE_BASED_INDEXING_TO_ZERO_BASED_INDEXING;
            taskList.deleteTaskFromTaskList(indexItemToBeDeleted);
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
