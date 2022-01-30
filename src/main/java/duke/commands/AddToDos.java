package duke.commands;

import duke.tasklist.DukeList;
import duke.task.ToDos;
import duke.ui.Ui;

public class AddToDos extends Command {

    private String body;

    /**
     * Creates a new AddToDos Command
     * @param x Task description of the ToDo
     */
    public AddToDos(String x){
        this.body = x;
    }

    /**
     * Creates a new ToDo and adds it to the list
     * @param ui Ui to communicate with user
     * @param list DukeList that stores Tasks
     */
    @Override
    public void execute(Ui ui, DukeList list) {
        ToDos msg = new ToDos(body);
        list.add(msg);
    }

    /**
     * Returns false so program does not quit
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
