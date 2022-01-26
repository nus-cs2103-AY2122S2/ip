public class Command {
    private String userInput;
    private String[] words;
    private String firstWord;

    public Command(String userInput) {
        this.userInput = userInput;
        words = userInput.split(" ");
        firstWord = words[0];
    }

    public boolean isBye() {
        return firstWord.equals("bye");
    }

    public boolean isList() {
        return firstWord.equals("list");
    }

    public boolean isMark() {
        return firstWord.equals("mark") && words.length == 2;
    }

    public boolean isUnmark() {
        return firstWord.equals("unmark") && words.length == 2;
    }

    public boolean isTodo() {
        return firstWord.equals("todo");
    }

    public boolean isDeadline() {
        return firstWord.equals("deadline");
    }

    public boolean isEvent() {
        return firstWord.equals("event");
    }

    public boolean isDelete() {
        return firstWord.equals("delete") && words.length == 2;
    }

    public boolean isBlah() {
        return firstWord.equals("blah");
    }

    public String deadlineContent() {
        String content = words[1];
        for (int i = 2; i < words.length; i++) {
            if (words[i].equals("/by") )
                break;
            content = content + " " + words[i];
        }
        return content;
    }

    public String deadlineDate() {
        String date = userInput.split("/by ")[1];
        return date;
    }

    public String eventContent() {
        String content = words[1];
        for (int i = 2; i < words.length; i++) {
            if (words[i].equals("/at"))
                break;
            content = content + " " + words[i];
        }
        return content;
    }

    public String eventDate() {
        String time = userInput.split("/at ")[1];
        return time;
    }

    public String todoContent() {
        String content = words[1];
        for (int i = 2; i < words.length; i++) {
            content = content + " " + words[i];
        }
        return content;
    }

    public int taskNo() {
        return Integer.parseInt(words[1]);
    }
}
