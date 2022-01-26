public class ShowAllCommands extends Command<String> {
    enum Commands {LIST, TODO, EVENT, DEADLINE, BYE, MARK, UNMARK, DELETE}

    public ShowAllCommands() {
        execute();
    }

    public void execute() {
        System.out.print(Ui.lineDivider + "COMMANDS:\n");
        switch (Commands.LIST) {
            case LIST:
                System.out.println("list    | Get a list of tasks you have");
            case TODO:
                System.out.println("todo    | One of the 3 tasks type. eg: todo_<yourtask>");
            case EVENT:
                System.out.println("event   | One of the 3 tasks type. eg: event_<yourtask>_/at_<time>");
            case DEADLINE:
                System.out.println("deadline| One of the 3 tasks type. eg: deadline_<yourtask>_/by_<time>");
            case BYE:
                System.out.println("bye     | End this phone call");
            case UNMARK:
                System.out.println("unmark  | Unmark the task as not done. eg: unmark_<task number>");
            case MARK:
                System.out.println("mark    | Mark the task as done. eg: mark_<task number>");
            case DELETE:
                System.out.println("delete  | Delete the task from list. eg: delete_<task number>");
        }
        System.out.println(Ui.lineDivider);
    }

    public boolean isExit(){
        return false;
    }
}
