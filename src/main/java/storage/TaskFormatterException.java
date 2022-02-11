package storage;

public class TaskFormatterException extends Exception {
    private static final String ERR_MSG_PREFIX = "TaskFormatterException: ";

    public TaskFormatterException(String message) {
        super(message);
    }

    public static class UnsupportedTaskEncodingException extends TaskFormatterException {
        private static final String MSG = "Unable to encode tasks as unsupported task type(s) is found" ;

        public UnsupportedTaskEncodingException() {
            super(UnsupportedTaskEncodingException.MSG);
        }
    }

    public static class IllegalDecodingInputException extends TaskFormatterException {
        private static final String MSG = "Unable to parse decoding input as format is illegal";

        public IllegalDecodingInputException() {
            super(IllegalDecodingInputException.MSG);
        }
    }

    @Override
    public String toString() {
        return TaskFormatterException.ERR_MSG_PREFIX + super.getMessage();
    }
}