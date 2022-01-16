public class DukeExceptions extends Exception{
}

class EmptyDescriptorExceptions extends Exception{
    public EmptyDescriptorExceptions(){
        super("☹ OOPS!!! The description of a task cannot be empty.");
    }
}
