class Command {
    protected String commandName;
    protected String startInd = ">>> ";

    protected Command(String commandName) {
        this.commandName = commandName;
    }

    protected void run(){
        System.out.println(startInd + commandName);
    };

    static void runCommand(String cmd, ItemList itemList) {
        if (cmd.equals("bye"))
            new ByeCommand().run();
        else if (cmd.equals("list"))
            new showListCommand(itemList).run();
        else
            new addListCommand(cmd, itemList).run();
    }
}

class listCommand extends Command {
    protected ItemList itemList;

    protected listCommand(String commandName, ItemList itemList) {
        super(commandName);
        this.itemList = itemList;
    }

}

class ByeCommand extends Command {
    protected ByeCommand() {
        super("bye");
    }

    @Override
    protected void run() {
        System.out.println(startInd + "Arrivederci!");
        System.exit(0);
    }
}

class addListCommand extends listCommand {

    protected addListCommand(String commandName, ItemList itemList) {
        super(commandName, itemList);
    }

    @Override
    protected void run() {
        itemList.addItem(this.commandName);
        System.out.println(startInd + "added: " + this.commandName);
    }

}

class showListCommand extends listCommand {

    protected showListCommand(ItemList itemList) {
        super("list", itemList);
    }

    @Override
    protected void run() {
        this.itemList.printList();
    }

}