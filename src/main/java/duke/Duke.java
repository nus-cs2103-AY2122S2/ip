package duke;

import duke.command.Command;
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
        try {
            Command command = Parser.parse(userInput, taskList);
            String response = command.execute();
            storage.writeData(taskList);
            return response;
        } catch (NumberFormatException e) {
            //mark command
            //unmark command
            return "mark command must precede with a decimal number!";
            //delete command
        } catch (IndexOutOfBoundsException e) {
            //todo command
            return "please specify what to do";
        } catch (NoTimeGivenException e) {
            return "specify the time please\n";
        }
    }

    private void writeData() {
        storage.writeData(taskList);
    }
}
