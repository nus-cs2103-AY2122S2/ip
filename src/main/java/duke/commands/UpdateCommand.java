package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.WrongDateFormatException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UpdateCommand extends Command{
    private TaskList tasks;
    private int index;
    private String type;
    private String update;

    /**
     * Constructor for Update Command class
     * Creates correct Task based on user input and adds to task list
     *
     * @param input detail to update and updated detail
     */
    public UpdateCommand(String input) {
        String[] processedInput = input.split("/",3);
        assert processedInput.length == 3;
        index = Integer.parseInt(processedInput[0])-1;
        type = processedInput[1];
        update = processedInput[2].trim();
    }

    /**
     * returns the modified task list after command execution
     *
     * @return TaskList
     */
    @Override
    public TaskList getList() {
        return tasks;
    }

    /**
     * returns true boolean if command execution ends program
     *
     * @return true if it ends main program
     */
    @Override
    public boolean endsProgram() {
        return false;
    }

    /**
     * executes the bye command
     * Prints bye message and saves the tasks created from the program loop to a text file
     *
     * @param tasks   tasks list to be modified
     * @param ui      to help with printing of messages
     * @param storage To deal with saving of task list
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        this.tasks = tasks;
        Task toUpdate = tasks.get(index);
        try {
            switch (type) {
            case "detail":
                toUpdate.updateDetail(update);
                break;
            case "time":
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                LocalDateTime formatUpdate = LocalDateTime.parse(update, formatter);
                toUpdate.updateDate(formatUpdate);
                break;
            }
        } catch (DateTimeParseException e) {
            throw new WrongDateFormatException(update);
        }
        this.tasks.set(index, toUpdate);
        storage.saveFile(this.tasks);
        return ui.updateMessage();
    }
}
