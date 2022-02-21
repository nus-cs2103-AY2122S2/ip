package duke;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.dukeexception.NoTimeGivenException;

/**
 * The main logic of Duke
 */
class Duke {
    private TaskList taskList;
    private Storage storage;

    public Duke(Storage storage, TaskList taskList) {
        this.storage = storage;
        this.taskList = taskList;
        storage.readData(taskList);
    }

    public String getResponse(String userInput) {
        assert this.storage != null;
        assert this.taskList != null;
        try {
            Command command = Parser.parse(userInput, taskList);
            command.execute();
            String response = command.getResponse();
            storage.writeData(taskList);
            return response;
        } catch (NumberFormatException e) {
            return "mark command must precede with a decimal number!";
        } catch (IndexOutOfBoundsException e) {
            return "please specify what to do";
        } catch (NoTimeGivenException e) {
            return "specify the time please\n";
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    private void writeData() {
        storage.writeData(taskList);
    }
}
