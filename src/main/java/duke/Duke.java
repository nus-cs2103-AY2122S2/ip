package duke;

import command.Command;
import exception.DukeException;
import task.TaskList;
import utility.Input;
import utility.Parser;
import utility.Storage;


public class Duke {

    public Input input;
    public TaskList tasks;
    public Storage storage;

    public Duke(String filePath){
        this.input = new Input();
        this.storage = new Storage(filePath);

        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            input.printException(e);
            tasks = new TaskList();
        }
    }


    public void run() {
        this.input.startMessage();
        boolean bye = false;

        while(!bye){
            try {
                String commandString = input.readLine();
                Command command = Parser.parse(commandString);
                command.execute(this.tasks, this.input, this.storage);
                bye = command.isExit();
            } catch (DukeException e) {
                this.input.printException(e);
            }

        }

    }

    public static void main(String[] args) {
        String filePath = "src/main/storage/save.text";
        new Duke(filePath).run();
    }
}
