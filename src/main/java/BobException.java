public class BobException extends IllegalArgumentException {
    private String bobReply;
    public BobException(String error) {
        super(error);
        if (Command.isTodo(error)) {
            this.bobReply = "Bob: Adding a Todo requires a task name! (ノಠ益ಠ)ノ彡┻━┻\n" +
                    "\t[e.g. \"todo Write a proper ToDo\"]";
        } else if (Command.isDeadline(error)) {
            this.bobReply = "Bob: Adding a deadline requires [task name] + [/by] + [date] + [T] + [time]! " +
                    "(ノಠ益ಠ)ノ彡┻━┻\n" +
                    "\t[e.g. \"deadline Learn how to add deadline /by 2022-02-22T02:22\"]";
        } else if (Command.isEvent(error)) {
            this.bobReply = "Bob: Adding an event requires [event name] + [/at] + [date] + [T] + " +
                    "[start time] + [-] + [end time]! (ノಠ益ಠ)ノ彡┻━┻\n" +
                    "\t[e.g. \"event seminar for people who dk how to add events /at 2022-02-22T02:22-22:22\"]";
        } else if (Command.isDelete(error) || Command.isMark(error)) {
            this.bobReply = "Bob: You need to give me a valid task number! (ಥ﹏ಥ) \n" +
                    "Try \"list\" if you want to see your tasks and their numbers.";
        } else {
            this.bobReply = "Bob: Your input was invalid (ㆆ _ ㆆ)\n";
        }
    }
    public String getBobReply() {
        return this.bobReply;
    }
}
