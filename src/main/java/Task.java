public  class Task {
    private String input;
    private boolean isComplete = false;
    
    public Task(String input) {
        this.input = input;
    }

    public void markAsDone() {
        this.isComplete = true;
    }

    public void unmarkAsDone() {
        this.isComplete = false;
    }

    @Override
    public String toString() {
        String returnString;
        if (this.isComplete) {
            returnString = "[X] " + input;
        } else {
            returnString  = "[ ] " + input;
        }

        return returnString;
    }

}
