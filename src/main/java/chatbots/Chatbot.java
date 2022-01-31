package chatbots;

/**
 * This class represents a Chatbot with its associated methods.
 *
 * Ideally, this should contain an InstructionHandler to handle instructions
 * given. Raw hard-coded handling of instruction is allowed in this case.
 *
 * @author Ong Han Yang
 */
public abstract class Chatbot {
    /** Whether the chatbot is awake or asleep. */
    protected boolean isAwake;

    /**
     * Constructs a chatbot. Initial state of the chatbot is that it is asleep.
     */
    protected Chatbot() {
        this.isAwake = false;
    }

    /**
     * Feeds a command and gives a reply based on the command.
     *
     * @param input the command as input.
     * @return the reply based on the command.
     */
    public abstract String feedCommandAndReply(String input);

    /**
     * Gets the state of isAwake.
     *
     * @return whether the chatbot it awake or not.
     */
    public boolean isAwake() {
        return isAwake;
    }

    /**
     * Sets the state of isAwake.
     *
     * @param awake the state to set the chatbot to.
     */
    public void setAwake(boolean awake) {
        this.isAwake = awake;
    }


}
