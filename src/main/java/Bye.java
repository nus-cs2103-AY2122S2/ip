/**
 * Bye is a task that quits the program and sends a see-you message.
 */
class Bye extends Instruction {

    /**
     * Constructor of a terminating instruction.
     *
     * @param description the description of the task as inputted by the user.
     */
    protected Bye(String description) {
        super(description);
    }
}
