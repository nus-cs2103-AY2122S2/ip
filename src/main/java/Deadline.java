class Deadline extends Task {
    private String dateTime;
    
    Deadline(String item, String dateTime) {
        super(item);
        this.dateTime = dateTime;
    }

    @Override
    public String getItemAndStatus() {
        String returned = "[D]" + super.getItemAndStatus() + " (by: " + this.dateTime + ")";
        return returned;
    }
}