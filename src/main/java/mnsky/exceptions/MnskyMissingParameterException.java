package mnsky.exceptions;

public class MnskyMissingParameterException extends MnskyException {
    /**
     * Creates a MnskyMissingParameterException object.
     * @param command The command that created and threw this exception.
     * @param parameterName The name of the parameter that caused this exception to be thrown.
     */
    public MnskyMissingParameterException(String command, String parameterName) {
        super(String.format("[MNSKY can't find the '%s' parameter for the '%s' command.]",
                parameterName, command));
    }
}
