public abstract class MickeyException {
    public static class InvalidCommandException extends BaseException {
        public InvalidCommandException(String msg) {
            super(msg);
        }
    }

    public static class MissingInputException extends BaseException {
        public MissingInputException(String msg) {
            super(msg);
        }
    }

    public static class InvalidMarkException extends BaseException {
        public InvalidMarkException(String msg) {
            super(msg);
        }
    }
}