class Todo extends Task {
    /**
     *
     * @param name
     */
    public Todo(String name) {

        super(name);
    }

    /**
     *
     * @param name
     * @param done
     */
    public Todo(String name, boolean done) {

        super(name, done);
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
    }
}