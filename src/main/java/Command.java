public class Command {
    protected String cmdWord;

    Command(String keyword) {
        this.cmdWord = keyword;
    }

    public void execute(TaskList taskList, TessUi ui, Storage storage) {
        ui.admitBug();
    };

    public static void process(String fullCmd, int numOfTasks) throws TesseractException {
        String[] cmdArr = fullCmd.split(" ");
        int cmdLen = cmdArr.length;
        switch (cmdArr[0]) {
            case "bye":
            case "list":
                if (cmdLen > 1) {
                    throw new TesseractException("You have entered an invalid command leh~");
                }
                break;
            case "delete":
            case "mark":
            case "unmark":
                if (cmdLen == 1) {
                    throw new TesseractException("Which task you want to remove again?");
                } else if (cmdLen > 2 || !Command.isInteger(cmdArr[1]) || Integer.parseInt(cmdArr[1]) > numOfTasks
                || Integer.parseInt(cmdArr[1]) < 1) {
                    throw new TesseractException("You need to enter a valid list number mah~");
                }
                break;
            case "filter":
                if (cmdLen < 3 || fullCmd.indexOf("/by") < 0) {
                    throw new TesseractException("What do you want to filter by again?");
                } else if (cmdArr[2].equals("/by")) {
                    Date.checkValidTime(cmdArr[3]);
                }
                break;
            case "event":
                if (cmdLen == 1) {
                    throw new TesseractException("Nah you need to provide me with the details of this event *_*");
                } else if (fullCmd.indexOf("/at") < 0) {
                    throw new TesseractException("When is the timing for your event again?");
                }
                Date.checkValidTime(cmdArr[cmdLen - 1]);
                break;
            case "deadline":
                if (cmdLen == 1) {
                    throw new TesseractException("Sorry I can't create deadline without its details )-:");
                } else if (fullCmd.indexOf("/by") < 0) {
                    throw new TesseractException("When do you need to do this by again?");
                }
                Date.checkValidTime(cmdArr[cmdLen - 1]);
                break;
            case "todo":
                if (cmdLen == 1) {
                    throw new TesseractException("I cannot create todo if you don't tell me what it's about eh :-(");
                }
                break;
            default:
                throw new TesseractException("Hey bro, not sure if this command is valid eh #_#");
        }

    }

    public static Command generate(String fullCmd) {
        String[] cmdArr = fullCmd.split(" ");
        switch (cmdArr[0]) {
            case "bye":
                return new ExitCommand(cmdArr);
            case "list":
                return new ListCommand(cmdArr);
            case "filter":
                return new FilterCommand(cmdArr);
            case "delete":
                return new DeleteCommand(cmdArr);
            case "mark":
                return new MarkCommand(cmdArr);
            case "unmark":
                return new UnmarkCommand(cmdArr);
            case "todo":
            case "deadline":
            case "event":
                return new CreateTaskCommand(cmdArr);
        }
        return new Command(""); // dummy command
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    boolean isExit() {
        return false;
    }

}
