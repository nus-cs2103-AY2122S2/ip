public class MnskyInvalidParameterException extends MnskyException {
    String parameterName;
    String command;

    /**
     * Constructor for the class.
     * @param command The command that created and threw this exception.
     * @param parameterName The name of the parameter that caused this exception to be thrown.
     */
    public MnskyInvalidParameterException(String command, String parameterName) {
        this.command = command;
        this.parameterName = parameterName;
    }

    /**
     * Overrides the string representation of the exception to its message.
     * @return The message of the exception.
     */
    @Override
    public String toString() {
        return String.format("[MNSKY can't figure out what to do with the given '%s' parameter for the '%s' command.]",
                this.parameterName, this.command);
    }
}