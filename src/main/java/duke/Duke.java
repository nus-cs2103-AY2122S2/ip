package duke;

import command.Command;
import exception.DukeException;
import task.TaskList;
import utility.Input;
import utility.Parser;


public class Duke {

    public Input input;
    public TaskList tasks;

    public Duke(){
        this.input = new Input();
        this.tasks = new TaskList();
    }


    public void run() {
        this.input.startMessage();
        boolean bye = false;

        while(!bye){
            try {
                String commandString = input.readLine();
                Command command = Parser.parse(commandString);
                command.execute(this.tasks, this.input);
                bye = command.isExit();
            } catch (DukeException e) {
                this.input.printException(e);
            }

        }

    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
