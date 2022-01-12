public class CortanaException extends Exception {
    protected String taskType;

    public CortanaException() {}

    public CortanaException(String taskType) {
        this.taskType = taskType;
    }

    public void EmptyDescriptionException() {
        switch (this.taskType) {
            case "deadline":
                System.out.println("OOPS!!! The description of a deadline cannot be empty! \uD83E\uDD21");
                break;
            case "event":
                System.out.println("OOPS!!! The description of an event cannot be empty! \uD83E\uDD21");
                break;
            case "todo":
                System.out.println("OOPS!!! The description of a todo cannot be empty! \uD83E\uDD21");
                break;
        }
    }

    public void UnidentifiedCommandException() {
        String taskType = this.taskType;
        if (taskType.equals("nothing")) {
            System.out.println("I don't know what that means \uD83D\uDE05");
        }
    }

    public void DidNotSpecifyTimeException() {
        String taskType = this.taskType;
        if (taskType.equals("deadline without time") || taskType.equals("event without time")) {
            System.out.println("Please specify the time! ⌚");
        }
    }
}
