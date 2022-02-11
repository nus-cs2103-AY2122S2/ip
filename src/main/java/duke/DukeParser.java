package duke;

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
            response.append(new byeCommand(history, tokens, ui).validateAndExecute());
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
            response.append(new listCommand(history, tokens, ui).validateAndExecute());
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
            response.append(new markCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException e) {
            return failedToUpdateStorage(response);
        }
    }

    public String unmarkCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new unmarkCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException e) {
            return failedToUpdateStorage(response);
        }
    }

    public String todoCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new todoCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException e) {
            return failedToUpdateStorage(response);
        }
    }

    public String deadlineCommand(String[] tokens) {
        StringBuilder response = new StringBuilder();
        try {
            assert (tokens.length > 1);
            response.append(new deadlineCommand(history, tokens, ui).validateAndExecute());
            updateStorage();
            return response.toString();
        } catch (AssertionError ex1) {
            return ui.printMissingArgumentError(tokens[0]);
        } catch (IOException e) {
            return failedToUpdateStorage(response);
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
