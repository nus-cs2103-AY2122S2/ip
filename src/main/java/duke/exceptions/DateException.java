package duke.exceptions;

public class DateException extends Exception {
    private String taskType;

    public DateException(String type){
        this.taskType = type;
    }

    @Override
    public String toString() {
        if (taskType.equals("deadline")) {
            return "OOPS!!! You didn't specify date!\n"
                    + "use the format:\n"
                    + "'deadline your task here /by date'";
        }
        else if (taskType.equals("event")) {
            return "OOPS!!! You didn't specify date!\n"
                    + "use the format:\n"
                    + "'event your event here /at date'";
        }
        return "";
    }
}
