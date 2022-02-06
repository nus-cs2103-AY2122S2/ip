package tasks;

import duke.DukeException;

public class ToDo extends Task {
    /**
     * Creates a ToDo task from user input.
     *
     * @param description Description of the ToDo task generated from user input.
     */
    public ToDo(String description) {
        super(description);
        super.saveFormat = "T," + this.description;
    }
    /**
     * Creates a ToDo task from previously saved list of tasks during initialisation of chat-bot.
     *
     * @param saveFormat Data saved in tasks list file.
     * @param blean A Boolean value set to True to differentiate the creation of task from saved list and user input.
     * @throws DukeException If the format is not followed or there are missing information.
     */
    public ToDo(String saveFormat, boolean blean) throws DukeException {
        super(saveFormat);
        try {
            String[] strArr = description.split(",");
            this.description = strArr[1];
            if (Boolean.parseBoolean(strArr[2])) {
                super.setDone();
            }
            super.saveFormat = strArr[0] + "," + strArr[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }
    /**
     * Returns the string representation of the ToDo task.
     *
     * @return a description of the task including its type and status.
     */
    @Override
    public String toString() {
        return "T " + "| " + super.toString();
    }
}
