class Event extends Task {
    private String dateTime;

    /**
        Constructor for Event class.

        @param item a String to description of the Event task.
        @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
        @param dateTime a String to describe the Event period.
     */
    Event(String item, int done, String dateTime) {
        super(item, done);
        this.dateTime = dateTime;
    }

    /**
        Method to display the item name and status in listing standard.

        @return String showing the task category, status and name.
    */
    @Override
    public String getItemAndStatus() {
        String returned = "[E]" + super.getItemAndStatus() + " (at: " + this.dateTime + ")";
        return returned;
    }

    /**
        Method to produce String for saving purposes.

        @return String that consist of the details of the Event task details.
     */
    @Override
    public String saveData() {
        String returned = "E " + this.done + " " + this.item + " /at " + this.dateTime;
        return returned;
    }
}