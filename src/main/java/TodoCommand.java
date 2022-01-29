import java.util.ArrayList;

public class TodoCommand extends Command<String> {

    private TaskList list;
    private String description;
    private Storage storage;
    private Ui ui;

    public TodoCommand(String description, TaskList list, Storage storage, Ui ui) {
        this.list = list;
        this.description = description;
        this.storage = storage;
        this.ui = ui;
        runCommand();
    }


    @Override
    public void runCommand() {
        String splicedString = description.substring(5);
        ToDo freshTodo = new ToDo(splicedString);
        list.addTask(freshTodo);
        storage.writeToFile(list);
        ui.addTodo(freshTodo, list);
        System.out.println("   " + "ALRIGHTY. I've added this task:\n"
                + "    " + freshTodo + "\n" +  "   Now you have " + list.getSize() + " tasks in the list.");
    }
}