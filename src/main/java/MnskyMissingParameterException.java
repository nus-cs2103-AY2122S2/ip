public class MnskyMissingParameterException extends MnskyException {
    String parameterName;
    String command;

    /**
     * Constructor for the class.
     * @param command The command that created and threw this exception.
     * @param parameterName The name of the parameter that caused this exception to be thrown.
     */
    public MnskyMissingParameterException(String command, String parameterName) {
        this.command = command;
        this.parameterName = parameterName;
    }

    /**
     * Overrides the string representation of the exception to its message.
     * @return The message of the exception.
     */
    @Override
    public String toString() {
        return String.format("[MNSKY can't find the '%s' parameter for the '%s' command.]",
                this.parameterName, this.command);
    }
}
