public class EventTask extends Task {
    protected String time;

    public EventTask(String message) throws EmptyMessageException, DateFormatException {
        String[] taskArray = message.split("/at");

        if (taskArray[0].equals("")) {
            throw new EmptyMessageException();
        } else if (taskArray.length == 1) {
            throw new DateFormatException("Sorry Master, when is the due? (include /at)");
        }

        super.taskMessage = taskArray[0].stripLeading().stripTrailing();
        time = taskArray[1].stripLeading().stripTrailing();
    }

    public EventTask(String message, String when) {
        super(message);
        time = when;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }
}
