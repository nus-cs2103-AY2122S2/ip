abstract class Command {
    protected String commandName;

    protected Command(String commandName) {
        this.commandName = commandName;
    }

    abstract protected void run();

    static void runCommand(String cmd, ItemList<Task> itemList) {
        if (cmd.equals("bye"))
            new ByeCommand().run();
        else if (cmd.equals("list"))
            new showListCommand(itemList).run();
        else if (cmd.startsWith("mark"))
            new markListCommand(cmd, itemList).run();
        else if (cmd.startsWith("unmark"))
            new unmarkListCommand(cmd, itemList).run();
        else if (cmd.startsWith("todo"))
            new addTodoListCommand(cmd, itemList).run();
        else if (cmd.startsWith("deadline"))
            new addDeadlineListCommand(cmd, itemList).run();
        else if (cmd.startsWith("event"))
            new addEventListCommand(cmd, itemList).run();
    }
}

abstract class listCommand extends Command {
    protected ItemList<Task> itemList;

    protected listCommand(String commandName, ItemList<Task> itemList) {
        super(commandName);
        this.itemList = itemList;
    }

    protected void printEndRun(Task task) {
        System.out.println("Alright! Added this to the list:");
        System.out.println(task.toString());
        this.itemList.printNoItems();
    }

}

class ByeCommand extends Command {
    protected ByeCommand() {
        super("bye");
    }

    @Override
    protected void run() {
        System.out.println("Arrivederci!");
        System.exit(0);
    }
}

class showListCommand extends listCommand {

    protected showListCommand(ItemList<Task> itemList) {
        super("list", itemList);
    }

    @Override
    protected void run() {
        System.out.println("Here you go sir:");
        this.itemList.printList();
    }

}

class markListCommand extends listCommand {
    int index;

    protected markListCommand(String commandName, ItemList<Task> itemList) {
        super(commandName, itemList);
        this.index = Integer.valueOf(commandName.split("\\s+")[1]);
    }

    @Override
    protected void run() {
        System.out.println("Alright! It's done:");
        this.itemList.getItem(index).setDone();
        System.out.println(this.itemList.getItem(index).toString());
    }
    
}

class unmarkListCommand extends listCommand {
    int index;

    protected unmarkListCommand(String commandName, ItemList<Task> itemList) {
        super(commandName, itemList);
        this.index = Integer.valueOf(commandName.split("\\s+")[1]);
    }

    @Override
    protected void run() {
        System.out.println("Thats unfortunate!:");
        this.itemList.getItem(index).setUndone();
        System.out.println(this.itemList.getItem(index).toString());
    }
    
}

class addTodoListCommand extends listCommand {

    protected addTodoListCommand(String commandName, ItemList<Task> itemList) {
        super(commandName, itemList);
    }

    @Override
    protected void run() {
        String taskName = this.commandName.replaceFirst("todo ", "");
        Task newTask = new ToDo(taskName);
        itemList.addItem(newTask);
        this.printEndRun(newTask);
    }

}

class addDeadlineListCommand extends listCommand {

    protected addDeadlineListCommand(String commandName, ItemList<Task> itemList) {
        super(commandName, itemList);
    }

    @Override
    protected void run() {
        String filtered = this.commandName.replaceFirst("deadline ", "");
        int index = filtered.indexOf("/by");
        String taskName = filtered.substring(0, index);
        String deadline = filtered.substring(index + 1, filtered.length());
        Task newTask = new Deadline(taskName, deadline);
        itemList.addItem(newTask);
        this.printEndRun(newTask);
    }

}

class addEventListCommand extends listCommand {

    protected addEventListCommand(String commandName, ItemList<Task> itemList) {
        super(commandName, itemList);
    }

    @Override
    protected void run() {
        String filtered = this.commandName.replaceFirst("event ", "");
        int index = filtered.indexOf("/at");
        String taskName = filtered.substring(0, index);
        String eventDate = filtered.substring(index + 1, filtered.length());
        Task newTask = new Event(taskName, eventDate);
        itemList.addItem(newTask);
        this.printEndRun(newTask);
    }

}