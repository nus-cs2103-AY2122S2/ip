import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Item> list;
    private int numItems;

    // Consider inheritance
    private class Item {
        private String content;
        private Boolean done;
        private int index;
        private Item(String content, int index) {
            this.content = content;
            this.done = false;
            this.index = index;
        }
        private void mark() {
            this.done = true;
        }
        private void unmark() {
            this.done = false;
        }
        @Override
        public String toString() {
            return String.valueOf(this.index) + ". " + this.noIndexToString();
        }
        public String noIndexToString() {
            return "[T]" + (this.done? "[X] " : "[ ] ") + this.content;
        }
    }
    private class Deadline extends Item{
        private String deadline;

        private Deadline(String content, int index) {
            super(content, index);
        }
        private Deadline(String content, int index, String deadline) {
            super(content, index);
            this.deadline = deadline;
        }
        private void addDeadline(String deadline) {
            this.deadline = deadline;
        }
        @Override
        public String toString() {
            return String.valueOf(super.index) + ". " + this.noIndexToString();
        }
        @Override
        public String noIndexToString() {
            return "[D]" + (super.done? "[X] " : "[ ] ") + super.content + " (by " + this.deadline + ")";
        }
    }
    private class Event extends Item {
        private String time;

        private Event(String content, int index) {
            super(content, index);
        }
        private Event(String content, int index, String time) {
            super(content, index);
            this.time = time;
        }
        private void addTime(String time) {
            this.time = time;
        }
        @Override
        public String toString() {
            return String.valueOf(super.index) + ". " + this.noIndexToString();
        }
        @Override
        public String noIndexToString() {
            return "[E]" + (super.done? "[X] " : "[ ] ") + super.content + " (at " + this.time + ")";
        }
    }
    private Duke() {
        this.list = new ArrayList<>();
        this.numItems = 0;
    }

    private void addItem(String input) {
        // 3 cases: todo, deadline, event
        Item item = null;
        this.numItems++;
        if(input.substring(0,4).equals("todo")) {
            item = new Item (input.substring(5), this.numItems);
            list.add(item);
        } else if(input.substring(0,8).equals("deadline")) {
            String[] components = input.substring(9).split(" /by ");
            item = new Deadline(components[0], this.numItems, components[1]);
            list.add(item);
        } else if(input.substring(0,5).equals("event")) {
            String[] components = input.substring(6).split(" /at ");
            item = new Event(components[0], this.numItems, components[1]);
            list.add(item);
        }
        System.out.println("Alright! I've added this task:");
        // potentially throw an error - beware
        System.out.println(item.noIndexToString());
        System.out.println(numItems == 1 ? "Now there is 1 task in your list."
                :"Now there are " + numItems + " tasks in your list.");
    }

    private void mark(int index) {
        Item i = this.list.get(index - 1);
        i.mark();
        System.out.println("Alright! I've marked this task as done:");
        System.out.println(i.noIndexToString());
    }

    private void unmark(int index) {
        Item i = this.list.get(index - 1);
        i.unmark();
        System.out.println("Alright! I've marked this task as not done yet:");
        System.out.println(i.noIndexToString());
    }

    private void listItems() {
        System.out.println("Here are the tasks in your list:");
        for (Item i : this.list) {
            System.out.println(i.toString());
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String greetings = "Greetings from Ann\n" + "What can I do for you?";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();

            if(input.toLowerCase().equals("bye")) {
                System.out.println("Sad to see you go :( See you again soon!");
                break;
            } else if (input.toLowerCase().equals("list")){
                duke.listItems();
            } else if(input.substring(0,4).toLowerCase().equals("mark")) {
                duke.mark(Integer.parseInt(input.substring(5)));
            } else if(input.substring(0,6).toLowerCase().equals("unmark")) {
                duke.unmark(Integer.parseInt(input.substring(7)));
            } else {
                duke.addItem(input);
            }
        }
    }
}
