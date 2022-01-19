public class Deadline extends Task {

    public String dateTime;

    public Deadline(String item, String dateTime) {
        super(item);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTime + ")";
    }

    public static Deadline createTask(String[] tokens) {
        boolean found = false;
        String item = "";
        String dateTime = "";
        for (String token : tokens) {
            if (token.equals("deadline")) {
                continue;
            } else if (token.equals("/by")) {
                found = true;
                continue;
            }
            if (found) {
                dateTime += token + " ";
            } else {
                item += token + " ";
            }
        }
        return new Deadline(item.trim(), dateTime.trim());
    }
}
