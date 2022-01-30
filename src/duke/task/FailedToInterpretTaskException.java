package task;

import main.DukeException;

class FailedToInterpretTaskException extends DukeException {


    protected FailedToInterpretTaskException(String message) {
        super(message);
    }
}
