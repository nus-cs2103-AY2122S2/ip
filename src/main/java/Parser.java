public class Parser {

    TaskList tasks;

    Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public void userCommand(String[] inputStringArray, Storage storage) throws DukeException {
        switch (inputStringArray[0]) {
            case "list":
                tasks.displayList();
                break;
            case "mark":
                tasks.mark(inputStringArray[1]);
                storage.updateData(tasks);
                break;
            case "unmark":
                tasks.unmark(inputStringArray[1]);
                storage.updateData(tasks);
                break;
            case "todo":
            case "deadline":
            case "event":
                tasks.addToList(inputStringArray);
                storage.updateData(tasks);
                break;
            case "delete":
                tasks.delete(inputStringArray[1]);
                storage.updateData(tasks);
                break;
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means O.o");
        }
    }

}
