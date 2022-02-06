package duke;

public class EventTask extends Task {
    public EventTask(String input) {
        super(input);
        this.type = "event";
        this.updateDescription();
    }

    /**
     * Format EventTask description into display format for Duke
     */
    private void updateDescription() {
        this.description += this.getInfo();
        this.description += "(at: ";
        this.description += this.getDate();
        this.description += ")";
    }

    /**
     * Find the index from which datetime information starts
     *
     * @return Integer index
     */
    private int findEventIndex() {
        int index = this.input.indexOf("/at "); // "/at" as delimiting character for events
        try {
            if (index == -1) {
                throw new DukeException();
            }
        } catch (DukeException e) {
            System.out.println("No /at date specified.");
            System.exit(2);
        }
        return index;
    }

    /**
     * Gets the task information
     *
     * @return Task information
     */
    private String getInfo() {
        int index = this.findEventIndex();
        String info = this.input.substring(0, index);

        return info;
    }

    /**
     * Gets the deadline date
     *
     * @return Deadline date string
     */
    private String getDate() {
        int index = this.findEventIndex() + 4; // Offset of the string "/at "
        String date = this.input.substring(index);

        return date;
    }

}


