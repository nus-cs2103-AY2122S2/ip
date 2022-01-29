public class Parser {

    public static StringBuilder parseInput(String input) {
        StringBuilder sb = new StringBuilder();
        String[] command = input.split(" ", 2);
        try {
            switch (command[0]) {
            case "list":
                sb.append(TaskList.printList());
                break;
            case "mark":
                sb.append(TaskList.toggleStatus("mark", Integer.parseInt(command[1]) - 1));
                break;
            case "unmark":
                sb.append(TaskList.toggleStatus("unmark", Integer.parseInt(command[1]) - 1));
                break;
            case "todo":
            case "event":
            case "deadline":
                sb.append(TaskList.addTask(command));
                break;
            case "delete":
                sb.append(TaskList.deleteTask(Integer.parseInt(command[1]) - 1));
                break;
            default:
                throw new DukeException(Error.INVALID);
            }
        } catch (DukeException e) {
            sb.append(e.invalidInput());
        }
        return sb;
    }
}
