class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    public ToDos(String description, boolean hasCompleted) {
        super(description);
        this.status = hasCompleted;
    }



    @Override
    public String toString() {
        return "[" + Type.T + "]";
    }

}