class UnmarkCommand extends Command {
    int toUnmarkIndex;

    public UnmarkCommand(int index) {
        toUnmarkIndex = index - 1;
    }

    @Override
    public String execute() {
        try{
            Task unmarkedTask = tasks.get(toUnmarkIndex).undone();
            return String.format("I assume you weren't done with this one:%s", unmarkedTask);
        } catch (IndexOutOfBoundsException e) {
            return "The archives contain no such task";
        }
    }

}
