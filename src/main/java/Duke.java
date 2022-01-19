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
        if(input.length() < 4) {
            System.out.println("Oops! Please enter a valid command!");
        } else if(input.substring(0,4).equals("todo")) {
            handleTodo(input);
        } else if(input.length() < 5) {
            System.out.println("Oops! Please enter a valid command!");
        } else if(input.substring(0,5).equals("event")) {
            handleEvent(input);
        } else if(input.length() < 8) {
            System.out.println("Oops! Please enter a valid command!");
        } else if(input.substring(0,8).equals("deadline")) {
            handleDeadline(input);
        } else {
            System.out.println("Oops! Please enter a valid command!");
        }
    }

    private void handleTodo(String input) {
        if(input.substring(4).isBlank()) {
            System.out.println("Oops! Please add a description for the todo!");
        } else {
            this.numItems++;
            Item item = new Item (input.substring(5), this.numItems);
            this.list.add(item);
            System.out.println("Alright! I've added this task:");
            System.out.println(item.noIndexToString());
            System.out.println(numItems == 1 ? "Now there is 1 task in your list."
                    :"Now there are " + numItems + " tasks in your list.");
        }
    }

    private void handleDeadline(String input) {
        if (input.substring(8).isBlank()) {
            System.out.println("Oops! Please use the following format:");
            System.out.println("deadline [content] /by [deadline]");
        } else {
            String[] components = input.substring(9).split(" /by ");
            if(components.length < 2) {
                System.out.println("Oops! Please use the following format:");
                System.out.println("deadline [content] /by [deadline]");
            } else if (components[0].isBlank()) {
                System.out.println("Oops! Please add a description for the deadline!");
            } else if (components[1].isBlank()) {
                System.out.println("Oops! Please add a deadline for the deadline!");
            } else {
                this.numItems++;
                Item item = new Deadline(components[0], this.numItems, components[1]);
                this.list.add(item);
                System.out.println("Alright! I've added this task:");
                System.out.println(item.noIndexToString());
                System.out.println(numItems == 1 ? "Now there is 1 task in your list."
                        :"Now there are " + numItems + " tasks in your list.");
            }
        }
    }

    private void handleEvent(String input) {
        if (input.substring(5).isBlank()) {
            System.out.println("Oops! Please use the following format:");
            System.out.println("event [content] /at [time]");
        } else {
            String[] components = input.substring(6).split(" /at ");
            if (components.length < 2) {
                System.out.println("Oops! Please use the following format:");
                System.out.println("event [content] /at [time]");
            } else if (components[0].isBlank()) {
                System.out.println("Oops! Please add a description for the event!");
            } else if (components[1].isBlank()) {
                System.out.println("Oops! Please add a time for the event!");
            } else {
                this.numItems++;
                Item item = new Event(components[0], this.numItems, components[1]);
                this.list.add(item);
                System.out.println("Alright! I've added this task:");
                System.out.println(item.noIndexToString());
                System.out.println(numItems == 1 ? "Now there is 1 task in your list."
                        : "Now there are " + numItems + " tasks in your list.");
            }
        }
    }

    private void mark(String index) {
        try {
            int i = Integer.parseInt(index);
            if (i == 0 || i > numItems) {
                System.out.println("Oops! Please enter a number between 1 and " + numItems +"!");
            } else {
                Item item = this.list.get(i - 1);
                item.mark();
                System.out.println("Alright! I've marked this task as done:");
                System.out.println(item.noIndexToString());
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Oops! Please enter a valid task number!");
        }
    }

    private void unmark(String index) {
        try {
            int i = Integer.parseInt(index);
            if (i == 0 || i > numItems) {
                System.out.println("Oops! Please enter a number between 1 and " + numItems + "!");
            } else {
                Item item = this.list.get(i - 1);
                item.unmark();
                System.out.println("Alright! I've marked this task as not done yet:");
                System.out.println(item.noIndexToString());
            }
        } catch (NumberFormatException nfe) {
            System.out.println("Oops! Please enter a valid task number!");
        }
    }

    private void listItems() {
        if (numItems == 0) {
            System.out.println("Currently, you have no tasks :)");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (Item i : this.list) {
                System.out.println(i.toString());
            }
        }
    }

    private void handleInput(String input) {
        if(input.toLowerCase().equals("bye")) {
            System.out.println("Sad to see you go :( See you again soon!");
            System.exit(0);
        } else if (input.toLowerCase().equals("list")){
            this.listItems();
        } else if (input.length() < 4) {
            this.addItem(input);
        } else if(input.substring(0,4).toLowerCase().equals("mark")) {
            this.mark(input.substring(5));
        } else if(input.length() < 6) {
            this.addItem(input);
        } else if(input.substring(0,6).toLowerCase().equals("unmark")) {
            this.unmark(input.substring(7));
        } else {
            this.addItem(input);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String greetings = "Greetings from Ann\n" + "What can I do for you?";
        System.out.println(greetings);

        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();
            duke.handleInput(input);
        }
    }
}