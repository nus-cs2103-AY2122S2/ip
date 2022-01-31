package siri;

class ToDos extends Task {

    /**
        Constructor for Deadline class.

        @param item a String to description of the ToDos task.
        @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
     */
    ToDos (String item, int done) {
        super(item, done);
    }

    /**
        Method to display the item name and status in listing standard.

        @return String showing the task category, status and name.
    */
    @Override
    public String getItemAndStatus() {
        String returned = "[T]" + super.getItemAndStatus();
        return returned;
    }
    /**
        Method to produce String for saving purposes.

        @return String that consist of the details of the ToDos task details.
     */
    @Override
    public String saveData() {
        String returned = "T " + this.done + " " + this.item;
        return returned;
    }
}