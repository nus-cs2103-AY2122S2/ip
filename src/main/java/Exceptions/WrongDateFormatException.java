package Exceptions;

public class WrongDateFormatException extends DukeException{

    public WrongDateFormatException (String exceptionMessage) {
        super ("Wrong date format detected! Make sure it is in 'yyyy-mm-dd' format!");
    }
}
