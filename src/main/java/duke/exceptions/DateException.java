package duke.exceptions;

public class DateException extends Exception{
    private String s;
    public DateException(String type){
        this.s = type;
    }

    @Override
    public String toString() {
        if (s.equals("deadline")) {
            return "OOPS!!! You didn't specify date!\n" +
                    "use the format:\n" +
                    "'deadline your task here /by date'";
        }
        else if (s.equals("event")) {
            return "OOPS!!! You didn't specify date!\n"+
                                    "use the format:\n"+
                            "'event your event here /at date'";
        }
        return "";
    }
}
