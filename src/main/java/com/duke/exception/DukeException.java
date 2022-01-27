package com.duke.exception;

public class DukeException extends Exception{

    @Override
    public String getMessage() {
        return "\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
