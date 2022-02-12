package duke;

import duke.Commands.*;

import java.io.IOException;

public class DukeParser {

    private DukeHistory history;
    private DukeStorage storage;
    private DukeUi ui;

    public DukeParser(DukeHistory history, DukeStorage storage, DukeUi ui) {
        this.history = history;
        this.storage = storage;
        this.ui = ui;
    }

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

    public void updateStorage() throws IOException {
        try {
            storage.update(history);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    public String failedToUpdateStorage(StringBuilder response) {
        response.append("\n").append(ui.printWriteError());
        return response.toString();
    }
}
