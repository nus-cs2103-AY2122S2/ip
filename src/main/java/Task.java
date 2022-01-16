/**
 * A task represents an instruction inputted to Duke by a user.
 * At this stage, a task has minimally a name (description).
 */
class Instruction {
    private String description;

    /**
     * Constructor of an instruction.
     *
     * @param description the description of the task as inputted by the user.
     */
    protected Instruction(String description) {
        this.description = description;
    }

    /**
     * @return the description of the instruction.
     */
    protected String getDescription() {
        return this.description;
    }

    /**
     * Retrieves the message of the task (when it is finished).
     *
     * @return the message.
     */
    protected String getMessage() {
        return this.description;
    }

    /**
     * Performs the associated action of the task. By default, there is no action associated to a task.
     */
    protected void act() {
        return;
    }
}
