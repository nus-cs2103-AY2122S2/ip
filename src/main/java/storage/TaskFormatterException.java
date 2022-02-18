package storage;

/**
 * Represents a failure that occurred when serializing/deserializing
 * a set of tasks.
 */
public class TaskFormatterException extends Exception {
    private static final String ERR_MSG_PREFIX = "TaskFormatterException: ";

    /**
     * Returns an exception which describes the reason for a failure
     * that occurred during the serialization/deserialization of a set of
     * tasks.
     *
     * @param message describes the reason for a serialization/deserialization failure.
     */
    public TaskFormatterException(String message) {
        super(message);
    }

    /**
     * Represents a failure that occurred as a result of serializing an unsupported
     * Task object type.
     */
    public static class UnsupportedTaskEncodingException extends TaskFormatterException {
        private static final String MSG = "Unable to encode tasks as unsupported task type(s) is found";

        /**
         * Returns an exception which describes the serialization of an unsupported
         * Task object type as the cause of failure.
         */
        public UnsupportedTaskEncodingException() {
            super(UnsupportedTaskEncodingException.MSG);
        }
    }

    /**
     * Represents a failure that occurred as a result of deserializing an input
     * that is represented in an incorrect format.
     */
    public static class IllegalDecodingInputException extends TaskFormatterException {
        private static final String MSG = "Unable to parse decoding input as format is illegal";

        /**
         * Returns an exception which describes the deserialization of an input
         * that is represented in an incorrect format as the cause of failure.
         */
        public IllegalDecodingInputException() {
            super(IllegalDecodingInputException.MSG);
        }
    }

    /**
     * Returns the string representation of the exception with a relevant
     * prefix for easier identification of exception type.
     *
     * @return A string representation of the exception.
     */
    @Override
    public String toString() {
        return TaskFormatterException.ERR_MSG_PREFIX + super.getMessage();
    }
}
