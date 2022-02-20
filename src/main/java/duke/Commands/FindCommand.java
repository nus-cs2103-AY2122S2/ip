package duke.commands;

import duke.DukeException;
import duke.DukeHistory;
import duke.DukeUi;

public class FindCommand extends Commands {

    public FindCommand(DukeHistory history, String[] userInput, DukeUi ui) {
        super(history, userInput, ui);
    }

    @Override
    public String validateAndExecute() {
        validate();
        try {
            return execute();
        } catch (DukeException ex1) {
            return this.getUi().printFoundNothing();
        }
    }

    @Override
    public void validate() {

    }

    /**
     * A method that, when called, builds a String phrase with the inputted String[] tokens before getting the
     * inputted instance of DukeHistory to call findPhrase().
     *
     * It then prints the returned String for the user to see.
     *
     * @return Find response.
     */
    @Override
    public String execute() throws DukeException {
        StringBuilder phrase = new StringBuilder();
        for (int i = 1; i < this.getUserInput().length; i++) {
            phrase.append(this.getUserInput()[i]);
            if (i != (this.getUserInput().length - 1)) {
                phrase.append(" ");
            }
        }
        String border = "_______________________________________________________\n";
        return border
                + "Here is what we found:\n"
                + this.getHistory().findPhrase(phrase.toString()) + border;
    }
}
