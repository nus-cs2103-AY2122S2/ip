package siri;

class ToDos extends Task {

    /**
     * Constructor for ToDos class.
     * 
     * @param item a String to description of the ToDos task.
     * @param done an int to indicate whether the item is done. 0 indicates not completed and 1 indicates completed.
     */
    ToDos (String item, boolean isDone) {
        super(item, isDone);
    }

    /**
     * Method to get String of task category, status and task name.
     */
    @Override
    public String getTaskDetails() {
        String taskDetails = "[T]" + super.getTaskDetails();
        return taskDetails;
    }

    /**
     * Method to return the string representation of the data for saving.
     * 
     * @return a string representation of the task for saving.
     */
    @Override
    public String saveData() {
        String dataString = "T " + String.valueOf(this.isDone) + " " + this.item;
        return dataString;
    }
}