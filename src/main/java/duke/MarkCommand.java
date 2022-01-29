package duke;

public class MarkCommand extends Command{

    enum MarkTypes {
        Mark, Unmark
    }

    MarkTypes markTypes;
    int index;

    MarkCommand (MarkTypes markTypes, int index) {
        this.markTypes = markTypes;
        this.index = index;
    }

    @Override
    void runCommand(TaskList taskList, Ui ui, Storage storage) {
        String indentation = "    ";
        Task t = taskList.getTask(index);
        String message;
        if (markTypes == MarkTypes.Mark) {
            taskList.markTask(index);
            message = indentation + "Nice! I've marked this task as done:\n"
                    + indentation + "  " + t.toString() + t.getStatus() + " " + t.getDescription();
        } else {
            taskList.unmarkTask(index);
            message = indentation + "OK, I've marked this task as not done yet:\n"
                    + indentation + "  " + t.toString() + t.getStatus() + " " + t.getDescription();
        }

        ui.outputMessage(message);
    }

}
