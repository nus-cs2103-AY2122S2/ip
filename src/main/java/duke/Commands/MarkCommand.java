package duke.commands;

import duke.Deadlines;
import duke.DukeException;
import duke.DukeHistory;
import duke.DukeUi;
import duke.Event;
import duke.Task;
import duke.ToDos;

public class MarkCommand extends Commands {

    private int index;

    public MarkCommand(DukeHistory history, String[] userInput, DukeUi ui) {
        super(history, userInput, ui);
    }

    @Override
    public String validateAndExecute() {
        try {
            validate();
            return execute();
        } catch (NumberFormatException | IndexOutOfBoundsException ex1) {
            return this.getUi().printInvalidArgumentError();
        } catch (DukeException ex2) {
            return this.getUi().printFailedAccess();
        }
    }

    @Override
    public void validate() throws IndexOutOfBoundsException {
        this.index = Integer.parseInt(this.getUserInput()[1]) - 1;
        if (this.index < 0 || this.index > this.getHistory().getSize() - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * A method that, when called, checks the validity of a given entry index before getting the inputted instance
     * of DukeHistory to mark that entry in its records as done.
     *
     * @return Mark response.
     */
    @Override
    public String execute() throws DukeException {
        this.getHistory().getTask(index).isMarked();
        StringBuilder tasking = accessHistory();
        return "_______________________________________________________\n"
                + "Well done! You have completed the task:\n"
                + "    " + tasking.toString()
                + "_______________________________________________________\n";

    }

    /**
     * Method that searches an instance of Duke History for a specific entry and marks it.
     * @return A confirmation response on success or the relevant error response.
     * @throws DukeException
     */
    public StringBuilder accessHistory() throws DukeException {
        Task currTask = this.getHistory().getTask(this.index);
        if (currTask instanceof ToDos) {
            ToDos temp = (ToDos) currTask;
            return new StringBuilder(temp.getToDo());
        } else if (currTask instanceof Deadlines) {
            Deadlines temp = (Deadlines) currTask;
            return new StringBuilder(temp.getDeadline());
        } else if (currTask instanceof Event) {
            Event temp = (Event) currTask;
            return new StringBuilder(temp.getEvent());
        } else {
            throw new DukeException("Failed to access history!");
        }
    }
}
