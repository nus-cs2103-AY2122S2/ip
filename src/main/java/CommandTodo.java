class CommandTodo extends Command {
    TaskList taskList;
    String todoContent;

    public CommandTodo(TaskList taskList, String todoContent) {
        this.taskList = taskList;
        this.todoContent = todoContent;
    }

    @Override
    public void execute() {
        Todo newTask = new Todo(todoContent);
        taskList.addTask(newTask);
        Ui.wrapPrint(Response.RESPONSE_ADDED + "\n" + newTask.toString() + "\n"
                     + Response.taskNo(taskList.size()));
    }
}
