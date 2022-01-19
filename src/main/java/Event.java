class Event extends Task {
    private String dateTime;

    Event(String item, String dateTime) {
        super(item);
        this.dateTime = dateTime;
    }

    @Override
    public String getItemAndStatus() {
        String returned = "[E]" + super.getItemAndStatus() + " (at: " + this.dateTime + ")";
        return returned;
    }
}