package duke.commands;

import duke.tasklist.DukeList;
import duke.task.ToDos;
import duke.ui.Ui;

public class AddToDos extends Command{

    private String body;

    public AddToDos(String x){
        this.body = x;
    }

    @Override
    public void execute(Ui ui, DukeList list) {
        ToDos msg = new ToDos(body);
        list.add(msg);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
