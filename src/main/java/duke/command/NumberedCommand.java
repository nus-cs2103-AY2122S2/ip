package duke.command;

import duke.storage.Storage;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.task.Task;
import java.io.IOException;

public class NumberedCommand extends Command {

    private int number;
    private String taskType;
    private String textInput;

    public NumberedCommand(int val, String type, String input) {
        this.number = val;
        this.taskType = type;
        this.textInput = input;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(Storage stg, Ui ui, TaskList tasks) throws IOException {
        try {
            if (this.taskType.equals("mark")) {
                Task task = tasks.get(this.number - 1);
                String oldMark = task.formatText();
                task.markTask();
                System.out.println("Nice! I've marked this task as done!");
                System.out.println(task.toString());
                String replaceMark = task.formatText();
                stg.editData(oldMark, replaceMark);
            } else if (this.taskType.equals("unmark")) {
                Task task = tasks.get(this.number - 1);
                String oldMark = task.formatText();
                task.unmarkTask();
                System.out.println("OK, I've marked this task as not done yet");
                System.out.println(task.toString());
                String replaceMark = task.formatText();
                stg.editData(oldMark, replaceMark);
            } else {
                Task task = tasks.get(this.number - 1);
                String oldDelete = task.formatText();
                tasks.deleteTask(number - 1);
                stg.editData(oldDelete, " ");
                System.out.println("Noted! I've removed this task:");
                System.out.println(task.toString());
                ui.showCount(tasks);
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Please enter a valid number!");
        }
        ui.showLine();
    }
}
