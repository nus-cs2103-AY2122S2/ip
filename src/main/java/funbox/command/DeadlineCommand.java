package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;
import funbox.util.Parser;
import funbox.task.Deadline;

import java.time.LocalDate;

/**
 * Deal with handling command for Deadline.
 */
public class DeadlineCommand extends Command {
    String description;
    Parser parser;

    /**
     * The constructor for DeadlineCommand.
     *
     * @param description The description of the task.
     */
    public DeadlineCommand(String description) {
        super(false);
        this.description = description;
        this.parser = new Parser();
    }

    /**
     * Execute the command which add Deadline task.
     *
     * @param taskList List of tasks.
     * @param ui Interface which interact with users.
     * @param storage Stores user tasks locally.
     * @return Returns a string to be displayed to the user.
     * @throws FunBoxExceptions Returns exceptions related to FunBox.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        String result;
        assert ui != null : "ui should not be null";
        assert storage != null : "ui should not be null";
        if (description.equals("")) {
            throw new FunBoxExceptions("`deadline` command usage: deadline <task> /by <date> <time>");
        }

        String[] resultArr = this.parser.getDescAndDate(this.description, "deadline");

        if (resultArr.length < 2) {
            throw new FunBoxExceptions("`deadline` command usage: deadline <task> /by <date> <time>");
        }

        LocalDate date = parser.stringToLocalDate(resultArr[1]);
        String time = parser.getTime(resultArr[1]);

        Deadline deadline = new Deadline(resultArr[0], date, time,"deadline");
        taskList.add(deadline);

        result = ui.taskPrefix().concat(ui.printTask(taskList.getSize(), deadline));
        storage.writeTaskToStorage(deadline);

        return result;
    }
}
