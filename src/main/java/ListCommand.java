class ListCommand extends Command {
    @Override
    public String execute() {
        return tasks.toString();
    }
}
