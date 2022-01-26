import java.io.IOException;

public class AddDeadlineCommand extends Command<String> {
    private TaskList list;
    private String[] echo;
    private Storage storage;

    public AddDeadlineCommand(TaskList list, String[] echo, Storage storage) throws DukeException {
        this.list = list;
        this.echo = echo;
        this.storage = storage;
        execute();
    }
    public void execute() throws DukeException {
        String err = "Oh no! The description of deadline cannot be empty... Try again :)\n";
        String wrongFormat = "Oh no! The format for deadline task is wrong... Try again :)\n";
        if (echo.length == 1) {
            throw new DukeException(err);
        }
        String description = echo[1];
        if (description.isEmpty()) {
            throw new DukeException(err);
        }
        String[] details = description.split(" /by ", 2);
        if (details.length == 1) {
            throw new DukeException(wrongFormat);
        }
        String info = details[0];
        String date = details[1];
        Deadline curr = new Deadline(info, date);
        list.addTask(curr);
        Ui.showAddResponse(curr.toString(), list.getSize());
        try {
            storage.writeToFile(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isExit(){
        return false;
    }
}
