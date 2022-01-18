public class BobException extends IllegalArgumentException {
    private String bobReply;
    public BobException(String error) {
        super(error);
        if (error.equalsIgnoreCase("todo")) {
            this.bobReply = """
                    Bob: Adding a Todo requires a task name! (ノಠ益ಠ)ノ彡┻━┻
                    \t[e.g. "todo Write a proper ToDo"]
                    """;
        } else if (error.equalsIgnoreCase("deadline")) {
            this.bobReply = """
                    Bob: Adding a deadline requires [task name] + [/by] + [deadline]! (ノಠ益ಠ)ノ彡┻━┻
                    \t[e.g. "deadline Learn how to add deadline /by today"]
                    """;
        } else if (error.equalsIgnoreCase("event")) {
            this.bobReply = """ 
                    Bob: Adding an event requires [event name] + [/at] + [day and time]! (ノಠ益ಠ)ノ彡┻━┻
                    \t[e.g. "event seminar for people who dk how to add events /at Mon 6-9pm"]
                    """;
        } else if (error.equalsIgnoreCase("invalid number")) {
            this.bobReply = """
                    Bob: You need to give me a valid task number! (ಥ﹏ಥ)
                        Try "list" if you want to see your tasks and their numbers.
                    """;
        } else {
            this.bobReply = "Bob: Your input was invalid (ㆆ _ ㆆ)\n";
        }
    }
    public String getBobReply() {
        return this.bobReply;
    }
}
