public class Parser {

    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public void parseTaskList(String input) {
        String[] splitInput = input.split(" ");

        try {
            switch(splitInput[0]) {
                case "list":
                    taskList.listTasks();
                    break;
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                case "mark":
                    taskList.mark(input);
                    break;
                case "unmark":
                    taskList.unmark(input);
                    break;
                case "todo":
                    taskList.setTodo(input);
                    break;
                case "deadline":
                    taskList.setDeadline(input);
                    break;
                case "event":
                    taskList.setEvent(input);
                    break;
                case "delete":
                    taskList.delete(input);
                    break;
                default:
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means");
            }
        } catch (DukeException wrf) {
            wrf.printStackTrace();
        }
    }

    public boolean isBye(String command) {
        return command.equals("bye");
    }
}
