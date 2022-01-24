class Deadline extends Task {
    private String dateTime;

    /**
        Constructor for Deadline class.

        @param item a String to description of the Deadline task.
        @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
        @param dateTime a String to describe the Deadline period.
     */
    Deadline(String item, int done, String dateTime) {
        super(item, done);
        this.dateTime = dateTime;
    }

    /**
        Method to display the item name and status in listing standard.

        @return String showing the task category, status and name.
    */
    @Override
    public String getItemAndStatus() {
        String returned = "[D]" + super.getItemAndStatus() + " (by: " + this.dateTime + ")";
        return returned;
    }

    /**
        Method to produce String for saving purposes.

        @return String that consist of the details of the Deadline task details.
     */
    @Override
    public String saveData() {
        String returned = "D " + this.done + " " + this.item + " /by " + this.dateTime;
        return returned;
    }
}