import exceptions.DukeDeadlineException;

public class Deadline extends Task {
    protected String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public static Deadline setDeadline(String input) throws DukeDeadlineException { //method to call constructor solving
                                                    // "'this' should be called in first line" error
        String taskname;
        String taskby;
        try {
            String[] split = input.split("/by");
            taskname = split[0];
            taskby = split[1];
            Deadline d_line = new Deadline(taskname, taskby);
//            System.out.println("Got it. I've added this task: ");
//            System.out.println(d_line);
            return d_line;
        } catch (Exception e) {
            DukeDeadlineException error = new DukeDeadlineException("OOPS!!! Please enter in format: deadline <task>/by<due date>");
            System.out.println(error.getMessage());
        }
        return null;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.by + ")";
    }
}
