/**
 * Represents the instruction 'quit'.
 */
final class Quit extends Instruction {

    private static final String SEE_YOU_MESSAGE = "Bye!";

    /**
     * Constructor, sets the description of the instruction.
     *
     * @param message The terminating command that signals the end of program.
     */
    protected Quit(String message) {
        super.setDescription(message);
    }

    /**
     * @return The see-you message.
     */
    @Override
    protected String act() {
        return Quit.SEE_YOU_MESSAGE;
    }
}
