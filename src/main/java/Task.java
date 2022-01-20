public  class Task {
    private String input;
    private boolean isComplete = false;
    private String type;
    private String description;
    
    public Task(String input) {
        this.input = input;
    }

    public void markAsDone() {
        this.isComplete = true;
    }

    public void unmarkAsDone() {
        this.isComplete = false;
    }

    /**
     * Gets the symbol for whether this task is complete or not
     * @return A string symbol
     */
    private String getDoneSymbol() {
        if (this.isComplete) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns description of task
     * @return Description string
     */
    private String getDescription() {
        return this.description;
    }

    /**
     * Gets the symbol for the type of this task
     * @return A string symbol
     */
    private String getTaskTypeSymbol() {
        if (this.type.equals("todo")) {
            return "[T]";
        } else if (this.type.equals("deadline")) {
            return "[D]";
        } else if (this.type.equals("event")) {
            return "[E]";
        } else return null;
    }

    @Override
    public String toString() {
        String doneSymbol = this.getDoneSymbol();
        String taskTypeSymbol = this.getTaskTypeSymbol();
        String description = this.getDescription();

        String output = doneSymbol + taskTypeSymbol + " " + description;

        return output;
    }

}
