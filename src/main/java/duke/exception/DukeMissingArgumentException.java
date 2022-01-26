package duke.exception;

import duke.exception.DukeException;

public class DukeMissingArgumentException extends DukeException {
    protected String keyword;
    public DukeMissingArgumentException(String keyword){
        super();
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        return "you are missing keyword: " + keyword;
    }
}
