package funbox.command;

import funbox.exception.FunBoxExceptions;
import funbox.util.Ui;
import funbox.util.TaskList;
import funbox.util.Storage;
import funbox.util.Parser;
import funbox.task.Deadline;

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
     * @throws FunBoxExceptions If description == ""
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws FunBoxExceptions {
        if (description.equals("")) {
            throw new FunBoxExceptions("`deadline` command is missing a field!");
        } else {
            String[] resultArr = this.parser.getDescriptionAndDate(this.description, "deadline");
            Deadline deadline = new Deadline(resultArr[0],
                    parser.stringToLocalDate(resultArr[1]),
                    parser.getTime(resultArr[1]), "deadline");
            taskList.add(deadline);
            ui.printTask(taskList.getSize(), deadline);
            storage.writeTaskToStorage(deadline, ui);
        }
    }
}
