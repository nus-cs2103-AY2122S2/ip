package mnsky.exceptions;

public class MnskyInvalidParameterException extends MnskyException {
    /**
     * Creates a MnskyInvalidParameterException object.
     * @param command The command that created and threw this exception.
     * @param parameterName The name of the parameter that caused this exception to be thrown.
     */
    public MnskyInvalidParameterException(String command, String parameterName) {
        super(String.format("[MNSKY can't figure out what to do with the given '%s' parameter for the '%s' command.]",
                parameterName, command));
    }
}