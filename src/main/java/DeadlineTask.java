public class DeadlineTask extends Task {
    protected String time;

    public DeadlineTask(String message) throws EmptyMessageException, DateFormatException {
        String[] taskArray = message.split("/by");

        if (taskArray[0].equals("")) {
            throw new EmptyMessageException();
        } else if (taskArray.length == 1) {
            throw new DateFormatException("Sorry Master, when is the due? (include /by)");
        }

        super.taskMessage = taskArray[0].stripLeading().stripTrailing();
        time = taskArray[1].stripLeading().stripTrailing();
    }

    public DeadlineTask(String message, String deadline) {
        super(message);
        time = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }

}
