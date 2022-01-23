public class Parser {
    public static String parse(String line, TaskList taskList) throws DukeException {
        String[] input = line.strip().split(" ", 2);

        if (!Constants.TASKS.contains(input[0])) {
            throw new CommandNotFoundException();
        }

        if (input[0].equals("bye")) {
            return "BYE";
        } else if (input[0].equals("list")) {
            return taskList.getTasks();
        } else {
            if (input.length != 2) {
                throw new InvalidArgumentException();
            }

            if (input[0].equals("mark") || input[0].equals("unmark")) {
                int taskId = Integer.parseInt(input[1]);
                return taskList.mark(taskId, input[0]);
            } else if (input[0].equals("todo")) {
                String toDo = input[1].strip();
                return taskList.addTask(new ToDo(toDo));
            } else if (input[0].equals("event")) {
                String[] eventDetails = input[1].strip().split(" /at ", 2);
                return taskList.addTask(new Event(eventDetails[0], eventDetails[1]));
            } else if (input[0].equals("deadline")) {
                String[] deadlineDetails = input[1].strip().split(" /by ", 2);
                return taskList.addTask(new Deadline(deadlineDetails[0], deadlineDetails[1]));
            } else if (input[0].equals("delete")) {
                int taskId = Integer.parseInt(input[1]);
                return taskList.remove(taskId);
            }
        }

        return null;
    }
}
