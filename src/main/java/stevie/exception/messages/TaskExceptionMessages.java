package stevie.exception.messages;

public enum TaskExceptionMessages {
    InvalidTaskTypeError {
        @Override
        public String toString() {
            return "There is no such task!";
        }
    },
    InvalidTaskIndexError {
        @Override
        public String toString() {
            return "There is no such task!";
        }
    }
}
