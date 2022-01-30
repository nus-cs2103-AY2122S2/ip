class DeleteCommand extends Command {
    private int toDeleteIndex;

    DeleteCommand(int index) {
        toDeleteIndex = index -1;
    }

    @Override
    public String execute() {
        try {
            Task deletedTask = tasks.remove(toDeleteIndex);
            return String.format("I removed the following task:\n%s", deletedTask);
        } catch (IndexOutOfBoundsException e) {
            return "The task you are trying to delete is not in the archives.";
        }
    }
}
