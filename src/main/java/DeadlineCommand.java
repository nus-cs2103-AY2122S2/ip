import java.time.LocalDate;

public class DeadlineCommand extends Command {
    String description;
    Parser parser;
    public DeadlineCommand(String description) {
        super(false);
        this.description = description;
        this.parser = new Parser();
    }

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
