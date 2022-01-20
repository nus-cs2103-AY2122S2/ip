public class DeadlineTask extends Task {
    public DeadlineTask(String input) {
        super(input);
        this.type = "deadline";
        this.updateDescription();
    }

    /**
     * Format DeadlineTask description into display format for Duke
     */
    private void updateDescription() {
        this.description += this.getInfo();
        this.description += "(by: ";
        this.description += this.getDate();
        this.description += ")";
    }

    /**
     * Find the index from which datetime information starts
     *
     * @return Integer index
     */
    private int findDeadlineIndex() {
        int index = this.input.indexOf("/by "); // "/" as delimiting character
        return index;
    }

    /**
     * Gets the task information
     *
     * @return Task information
     */
    private String getInfo() {
        int index = this.findDeadlineIndex();
        String info = this.input.substring(0, index);

        return info;
    }

    /**
     * Gets the deadline date
     *
     * @return Deadline date string
     */
    private String getDate() {
        int index = this.findDeadlineIndex() + 4;
        String date = this.input.substring(index);

        return date;
    }

}
