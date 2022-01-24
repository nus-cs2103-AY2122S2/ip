public class ExitCommand extends Command {

    boolean isExit = false;


    ExitCommand(String directory, String filePath) {
        super(directory, filePath);
    }


    public String quit() {
        isExit = true;
        return "Bye. Hope to see you again soon!";
    }
}