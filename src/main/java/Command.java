class Command {
    private String commandName;
    protected String startInd = ">>> ";

    protected Command(String commandName) {
        this.commandName = commandName;
    }

    protected void run(){
        System.out.println(startInd + commandName);
    };

    static void runCommand(String cmd) {
        if (cmd.equals("bye"))
            new ByeCommand().run();
        else
            new Command(cmd).run();
    }

}

class ByeCommand extends Command{
    protected ByeCommand() {
        super("bye");
    }

    @Override
    protected void run() {
        System.out.println(startInd + "Arrivederci!");
        System.exit(0);
    }
}