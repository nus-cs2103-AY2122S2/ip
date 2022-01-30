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
     * Constructs a chatbot.
     */
    protected Chatbot() {
        this.isAwake = true;
    }

    /**
     * Asks the chatbot to speak with given input.
     *
     * @param input the input words to speak.
     * @return the formatted string of the input words.
     */
    protected abstract String speak(String input);

    /**
     * Greets the user.
     *
     * @return a greeting.
     */
    protected abstract String greet();

    /**
     * Says goodbye to the user.
     *
     * @return a farewell message.
     */
    protected abstract String farewell();

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
