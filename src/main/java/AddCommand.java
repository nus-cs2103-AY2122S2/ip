import java.io.IOException;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    private Action action;
    private String input;

    public AddCommand(Action action, String input) {
        this.action = action;
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            switch (action) {
                case TODO:
                    try {
                        String[] todoArr = input.split("\\s", 2);
                        if (todoArr.length <= 1) {
                            throw new InvalidArgumentException(Messages.UNKNOWN_TODO);
                        }
                        tasks.add(new Todo(todoArr[1].trim()));
                        tasks.printTaskAdded();
                    } catch (InvalidArgumentException e) {
                        ui.showError(e.getMessage());
                    } finally {
                        break;
                    }
                case DEADLINE:
                    //deadline do hw /by no idea :-p
                    try {
                        String[] deadlineArr = input.split("/by", 2);
                        String[] deadlineSplit = deadlineArr[0].split("\\s", 2);
                        if (deadlineSplit.length <= 1) {    // no description
                            throw new InvalidArgumentException(Messages.UNKNOWN_DEADLINE);
                        }
                        if (deadlineArr.length <= 1) { // don't have /by keyword
                            throw new InvalidArgumentException(Messages.UNKNOWN_DATETIME);
                        }
                        String description = deadlineSplit[1].trim();
                        tasks.add(new Deadline(description, deadlineArr[1].trim()));
                        tasks.printTaskAdded();
                    } catch (InvalidArgumentException e) {
                        ui.showError(e.getMessage());
                    } catch (DateTimeParseException e) {
                        ui.showError(Messages.UNKNOWN_DATETIME);
                    } finally {
                        break;
                    }
                case EVENT:
                    //event project meeting /at Mon 2-4pm
                    try {
                        String[] eventArr = input.split("/at", 2);
                        String[] eventSplit = eventArr[0].split("\\s", 2);
                        if (eventSplit.length <= 1) {
                            throw new InvalidArgumentException(Messages.UNKNOWN_EVENT);
                        }
                        if (eventArr.length <= 1) {
                            throw new InvalidArgumentException(Messages.UNKNOWN_LOCATION);
                        }
                        String description = eventSplit[1].trim();
                        tasks.add(new Event(description, eventArr[1].trim()));
                        tasks.printTaskAdded();
                    } catch (InvalidArgumentException e) {
                        ui.showError(e.getMessage());
                    } finally {
                        break;
                    }
            }
            storage.save(tasks);
        } catch(IOException e) {
            ui.showError(e.getMessage());
        }
    }
}
