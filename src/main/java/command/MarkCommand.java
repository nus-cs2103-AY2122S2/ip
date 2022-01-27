package command;

import exception.DukeException;
import task.Task;
import utility.Input;
import task.TaskList;

public class MarkCommand extends Command {

    boolean isMark;
    int index;
    DukeException dukeException;

    public MarkCommand(String command) {
        super(command);
        String[] stringSplit = command.split(" ");
        String prefix = stringSplit[0];
        if(prefix.equals("mark")){
            this.isMark = true;
        } else if(prefix.equals("unmark")){
            this.isMark = false;
        }

        try {
            this.index = Integer.parseInt(stringSplit[1]);
        } catch (ArrayIndexOutOfBoundsException e){
            this.dukeException = new DukeException("input number within range!");
        }
    }

    @Override
    public void execute(TaskList tasks, Input input) throws DukeException {
        if (this.dukeException != null) {
            throw this.dukeException;
        } else if (this.index <= 0 || this.index > tasks.getSize()) {
            throw new DukeException("Please only input integers within the range of your tasks");
        }

        Task task = tasks.getByNumber(this.index);
        if (this.isMark) {
            task.setMarked(true);
            input.print(String.format("I've marked task %d!", this.index));
        } else {
            task.setMarked(false);
            input.print(String.format("I've marked task %d!", this.index));
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
