package SparkExceptions.TaskModificationExceptions;

import SparkExceptions.SparkException;

public class InvalidTaskIdException extends SparkException {
    public InvalidTaskIdException(String invalidInput) {
        super(String.format("Your input:\n  %s\ndoes not seem like an integer. Task numbers are integers!", invalidInput));
    }
}
