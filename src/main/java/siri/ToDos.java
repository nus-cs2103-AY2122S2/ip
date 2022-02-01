package siri;

class ToDos extends Task {

    /**
     * Constructor for ToDos class.
     * 
     * @param item a String to description of the ToDos task.
     * @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
     */
    ToDos (String item, int done) {
        super(item, done);
    }

    /**
     * Method to get String of task category, status and task name.
     */
    @Override
    public String getItemAndStatus() {
        String returned = "[T]" + super.getItemAndStatus();
        return returned;
    }

    /**
     * Method to return the string representation of the data for saving.
     * 
     * @return a string representation of the task for saving.
     */
    @Override
    public String saveData() {
        String returned = "T " + this.done + " " + this.item;
        return returned;
    }
}