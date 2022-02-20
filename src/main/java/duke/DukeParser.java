package duke;

import java.io.IOException;

import duke.commands.AddContactsCommand;
import duke.commands.ByeCommand;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DeleteContactsCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.ListContactsCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;
import duke.commands.UpdateCommand;

public class DukeParser {

    private DukeContact contact;
    private DukeHistory history;
    private DukeStorage storage;
    private DukeUi ui;

    /**
     * Constructor for DukeParser class.
     * @param contact DukeContact object that keeps a record of contacts stored in Duke.
     * @param history DukeHistory object that keeps a record of tasks stored in Duke.
     * @param storage DukeStorage object that handles loading and saving of Duke.txt files on local hard drive.
     * @param ui DukeUi object that handles the general responses Duke has.
     */
    public DukeParser(DukeContact contact, DukeHistory history, DukeStorage storage, DukeUi ui) {
        this.contact = contact;
        this.history = history;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Method that the user can invoke when he/she is done using Duke.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String byeCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length == 1);
            response.append(new ByeCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printFoundArgumentError();
        } catch (IOException ex2) {
            return failedToUpdateStorage(response);
        }
    }

    /**
     * Method that the user can invoke to list all tasks currently in Duke's history.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String listCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length == 1);
            response.append(new ListCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printFoundArgumentError();
        } catch (IOException ex2) {
            return failedToUpdateStorage(response);
        }
    }

    /**
     * Method that the user can invoke to mark a specified task in Duke's history as done.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String markCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new MarkCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException ex2) {
            return failedToUpdateStorage(response);
        }
    }

    /**
     * Method that the user can invoke to unmark a specified tasks in Duke's history as not done.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String unmarkCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new UnmarkCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException ex2) {
            return failedToUpdateStorage(response);
        }
    }

    /**
     * Method that the user can invoke to add a To_Do task to Duke's history.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String todoCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new TodoCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException ex2) {
            return failedToUpdateStorage(response);
        }
    }

    /**
     * Method that the user can invoke to add a Deadline task to Duke's History.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String deadlineCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new DeadlineCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException ex2) {
            return failedToUpdateStorage(response);
        }
    }

    /**
     * Method that the user can invoke to add an Event task to Duke's History
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String eventCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new EventCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException ex2) {
            return failedToUpdateStorage(response);
        }
    }

    /**
     * Method that the user can invoke to delete a specified task in Duke's History
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String deleteCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new DeleteCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException ex2) {
            return ui.printWriteError();
        }
    }

    /**
     * Method that the user can invoke to manually update the local Duke.txt file.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String updateCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length == 1);
            response.append(new UpdateCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printFoundArgumentError();
        } catch (IOException ex2) {
            return failedToUpdateStorage(response);
        }
    }

    /**
     * Method that the user can invoke to find tasks in Duke's history that match a specified phrase.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String findCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new FindCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException ex2) {
            return ui.printWriteError();
        }
    }

    /**
     * Method that the user can invoke to list all Contacts currently stored in Duke.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String listcontactsCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length == 1);
            response.append(new ListContactsCommand(contact, tokens, ui).validateAndExecute());
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printFoundArgumentError();
        }
    }

    /**
     * Method that the user can invoke to add a Contact to Duke's contacts.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String addcontactsCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1 && tokens.length <= 3);
            response.append(new AddContactsCommand(contact, tokens, ui).validateAndExecute());
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        }
    }

    /**
     * Method that the user can invoke to delete a specified Contact in Duke's contacts.
     * @param tokens A String[] containing the user's input.
     * @return A confirmation response on success or the relevant error response.
     */
    public String deletecontactsCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new DeleteContactsCommand(contact, tokens, ui).validateAndExecute());
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        }
    }

    /**
     * Method that gets Duke to update the local Duke.txt file.
     * @throws IOException
     */
    public void updateStorage() throws IOException {
        try {
            storage.update(history);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    /**
     * Method that is called when DukeLCH fails to update the local Duke.txt file after any Command is executed.
     * @param response Response of the previous command.
     * @return An updated response that informs the user that DukeLCH failed to update the local Duke.txt file.
     */
    public String failedToUpdateStorage(StringBuilder response) {
        response.append("\n").append(ui.printWriteError());
        return response.toString();
    }
}
