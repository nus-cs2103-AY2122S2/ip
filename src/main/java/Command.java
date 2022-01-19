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
        else
            new addListCommand(cmd, itemList).run();
    }
}

abstract class listCommand extends Command {
    protected ItemList<Task> itemList;

    protected listCommand(String commandName, ItemList<Task> itemList) {
        super(commandName);
        this.itemList = itemList;
    }

    @Override
    abstract protected void run();

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

class addListCommand extends listCommand {

    protected addListCommand(String commandName, ItemList<Task> itemList) {
        super(commandName, itemList);
    }

    @Override
    protected void run() {
        itemList.addItem(new Task(this.commandName));
        System.out.println("added: " + this.commandName);
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
        System.out.println("Sweet! It's done:");
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
        System.out.println("Aw Man! Quickly get it done!:");
        this.itemList.getItem(index).setUndone();
        System.out.println(this.itemList.getItem(index).toString());
    }
    
}