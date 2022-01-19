import java.util.ArrayList;

public class DukeEngine {
    
    ArrayList<Task> itemList = new ArrayList<>();

    public String addText(String text) {
        itemList.add(new Task(text));
        return "added: " + text;
    }

    public String markItem(int itemNumber) {
        String message = "";

        if (itemNumber <= 0 || itemNumber > itemList.size()) {
            message = String.format("Invalid item number specified. Are you sure item #%s exists?", itemNumber);
        } else {
            Task task = itemList.get(itemNumber - 1);
            task.markAsDone();
            message = "Nice! I've marked this as done:\n  " + task;
        }

        return message;
    }

    public String unmarkItem(int itemNumber) {
        String message = "";

        if (itemNumber <= 0 || itemNumber > itemList.size()) {
            message = String.format("Invalid item number specified. Are you sure item #%s exists?", itemNumber);
        } else {
            Task task = itemList.get(itemNumber - 1);
            task.unmarkAsDone();
            message = "OK, I've marked this task as not done yet:\n  " + task;
        }

        return message;
    }

    public String listItems() {
        StringBuilder sb = new StringBuilder();
        if (itemList.isEmpty()) {
            sb.append("There is nothing in the list!");
        }
        
        for (int i = 1; i <= itemList.size(); i++) {
            sb.append(i + ". ").append(itemList.get(i - 1)).append("\n");
        }
        return sb.toString();
    }

    public String greetingMessage() {
        StringBuilder greeting = new StringBuilder();
        greeting.append("Wow! Hello! I'm Duke.\n");
        greeting.append("What can I do for you?");

        return greeting.toString();
    }

    public String echoCommand(String msg) {
        return msg;
    }

    public String byeMessage() {
        return "Bye. Hope to see you again soon!";
    }

}
