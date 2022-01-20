abstract class Command {
    protected String commandName;

    protected Command(String commandName) {
        this.commandName = commandName;
    }

    abstract protected void run() throws DukeExceptions;

    static void runCommand(String cmd, ItemList<Task> itemList) throws DukeExceptions {
        try {
            System.out.println("");
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
            else if (cmd.startsWith("delete"))
                new deleteItemFromList(cmd, itemList).run();
            else
                throw InvalidCommand.createInvalidCommand(cmd);
            System.out.println("");
        } catch (IndexOutOfBoundsException e) {
            throw new ListIndexOutOfBound();
        }
        
    }
}

abstract class listCommand extends Command {
    protected ItemList<Task> itemList;

    protected listCommand(String commandName, ItemList<Task> itemList) {
        super(commandName);
        this.itemList = itemList;
    }

    protected void printAddEndRun(Task task) {
        System.out.println("Alright! Added that to the list:");
        System.out.println(task.toString());
        this.itemList.printNoItems();
    }

}

final class ByeCommand extends Command {
    protected ByeCommand() {
        super("bye");
    }

    @Override
    protected void run() {
        System.out.println("Arrivederci!");
        System.exit(0);
    }
}

final class showListCommand extends listCommand {

    protected showListCommand(ItemList<Task> itemList) {
        super("list", itemList);
    }

    @Override
    protected void run() {
        System.out.println("Here you go sir:");
        this.itemList.printList();
    }

}

final class markListCommand extends listCommand {
    int index;

    protected markListCommand(String commandName, ItemList<Task> itemList) throws EmptyParameters {
        super(commandName, itemList);
        String[] number = commandName.split("\\s+");
        if (number.length < 2)
            throw EmptyNumber.createEmptyNumber("Mark");
        this.index = Integer.valueOf(number[1]);
    }

    @Override
    protected void run() {
        this.itemList.getItem(index).setDone();
        System.out.println("Alright! It's done:");
        System.out.println(this.itemList.getItem(index).toString());
    }
    
}

final class unmarkListCommand extends listCommand {
    int index;

    protected unmarkListCommand(String commandName, ItemList<Task> itemList) throws EmptyParameters {
        super(commandName, itemList);
        String[] number = commandName.split("\\s+");
        if (number.length < 2)
            throw EmptyNumber.createEmptyNumber("Mark");
        this.index = Integer.valueOf(number[1]);
    }

    @Override
    protected void run() {
        this.itemList.getItem(index).setUndone();
        System.out.println("Thats unfortunate!:");
        System.out.println(this.itemList.getItem(index).toString());
    }
    
}

final class addTodoListCommand extends listCommand {

    protected addTodoListCommand(String commandName, ItemList<Task> itemList) {
        super(commandName, itemList);
    }

    @Override
    protected void run() throws EmptyParameters {
        if (!this.commandName.startsWith("todo "))
            throw EmptyTask.createEmptyTask("todo");
        String taskName = this.commandName.replaceFirst("todo ", "");
        if (taskName.isBlank())
            throw EmptyTask.createEmptyTask("todo");
        Task newTask = new ToDo(taskName);
        itemList.addItem(newTask);
        this.printAddEndRun(newTask);
    }

}

final class addDeadlineListCommand extends listCommand {

    protected addDeadlineListCommand(String commandName, ItemList<Task> itemList) {
        super(commandName, itemList);
    }

    @Override
    protected void run() throws EmptyParameters {
        if (!this.commandName.startsWith("deadline "))
            throw EmptyTask.createEmptyTask("deadline");
        String filtered = this.commandName.replaceFirst("deadline ", "");
        if (filtered.isBlank())
            throw EmptyTask.createEmptyTask("deadline");
        int index = filtered.indexOf("/by");
        if (index < 0)
            throw EmptyDate.createEmptyDate("Deadline");
        String taskName = filtered.substring(0, index);
        String deadline = filtered.substring(index + 3, filtered.length());
        if (deadline.isBlank())
            throw EmptyDate.createEmptyDate("Deadline");
        Task newTask = new Deadline(taskName, deadline);
        itemList.addItem(newTask);
        this.printAddEndRun(newTask);
    }

}

final class addEventListCommand extends listCommand {

    protected addEventListCommand(String commandName, ItemList<Task> itemList) {
        super(commandName, itemList);
    }

    @Override
    protected void run() throws EmptyParameters {
        if (!this.commandName.startsWith("event "))
            throw EmptyTask.createEmptyTask("event");
        String filtered = this.commandName.replaceFirst("event ", "");
        if (filtered.isBlank())
            throw EmptyTask.createEmptyTask("event");
        int index = filtered.indexOf("/at");
        if (index < 0)
            throw EmptyDate.createEmptyDate("Event");
        String taskName = filtered.substring(0, index);
        String eventDate = filtered.substring(index + 3, filtered.length());
        if (eventDate.isBlank())
            throw EmptyDate.createEmptyDate("Event");
        Task newTask = new Event(taskName, eventDate);
        itemList.addItem(newTask);
        this.printAddEndRun(newTask);
    }

}

final class deleteItemFromList extends listCommand {

    protected deleteItemFromList(String commandName, ItemList<Task> itemList) {
        super(commandName, itemList);
        //TODO Auto-generated constructor stub
    }

    @Override
    protected void run() throws DukeExceptions {
        // TODO Auto-generated method stub
        String[] number = commandName.split("\\s+");
        if (number.length < 2)
            throw EmptyNumber.createEmptyNumber("Delete");
        int index = Integer.valueOf(number[1]);
        Task deletedTask = this.itemList.getItem(index);
        this.itemList.deleteFromIndex(index);
        System.out.println("Alright, I've deleted this from the list:");
        System.out.println(deletedTask.toString());
        this.itemList.printNoItems();
    }

}