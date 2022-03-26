package duke.commands;

import duke.Deadlines;
import duke.DukeHistory;
import duke.DukeUi;
import duke.Event;
import duke.Task;
import duke.ToDos;

public class DeleteCommand extends Commands {

    private int index;

    public DeleteCommand(DukeHistory history, String[] userInput, DukeUi ui) {
        super(history, userInput, ui);
    }

    @Override
    public String validateAndExecute() {
        try {
            validate();
            return execute();
        } catch (NumberFormatException | IndexOutOfBoundsException ex1) {
            return this.getUi().printInvalidArgumentError();
        }
    }

    @Override
    public void validate() {
        this.index = Integer.parseInt(this.getUserInput()[1]) - 1;
        if (this.index < 0 || this.index > this.getHistory().getSize() - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * A method that, when called, checks the validity of a given entry index before getting the inputted instance
     * of DukeHistory to delete that entry in its records.
     *
     * @return Delete response.
     */
    @Override
    public String execute() {
        StringBuilder description = new StringBuilder();
        Task temp = this.getHistory().deleteTask(index);
        if (temp instanceof ToDos) {
            ToDos tempToDos = (ToDos) temp;
            description.append(tempToDos.getToDo());
        } else if (temp instanceof Deadlines) {
            Deadlines tempDeadlines = (Deadlines) temp;
            description.append(tempDeadlines.getDeadline());
        } else if (temp instanceof Event) {
            Event tempEvent = (Event) temp;
            description.append(tempEvent.getEvent());
        } else {
            System.out.println("Error occurred while deleting " + temp.getTask());
        }
        return "_______________________________________________________\n"
                + "Understood, removing this task now:\n"
                + "    " + description
                + "Now you have " + this.getHistory().getSize() + " tasks in our records.\n"
                + "_______________________________________________________\n";
    }
}
