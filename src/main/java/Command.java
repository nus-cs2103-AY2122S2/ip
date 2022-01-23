public abstract class Command {
  protected TaskList taskList;

  public abstract CommandResult execute();
}

