class ToDos extends Task {
    ToDos (String item) {
        super(item);
    }

    /*
        Override to extend the category of task to be displayed in status.
    */
    @Override
    public String getItemAndStatus() {
        String returned = "[T]" + super.getItemAndStatus();
        return returned;
    }
}