import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Item> list;
    private int numItems;

    // Consider inheritance
    private class Item {
        private String content;
        private Boolean done;
        private Item(String content) {
            this.content = content;
            this.done = false;
        }
        private void mark() {
            this.done = true;
        }
        private void unmark() {
            this.done = false;
        }
        @Override
        public String toString() {
            return "[T]" + (this.done? "[X] " : "[ ] ") + this.content;
        }
    }
    private class Deadline extends Item{
        private String deadline;

        private Deadline(String content) {
            super(content);
        }
        private Deadline(String content, String deadline) {
            super(content);
            this.deadline = deadline;
        }
        private void addDeadline(String deadline) {
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return "[D]" + (super.done? "[X] " : "[ ] ") + super.content + " (by " + this.deadline + ")";
        }
    }
    private class Event extends Item {
        private String time;

        private Event(String content) {
            super(content);
        }
        private Event(String content, String time) {
            super(content);
            this.time = time;
        }
        private void addTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
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
            Item item = new Item (input.substring(5));
            this.list.add(item);
            System.out.println("Alright! I've added this task:");
            System.out.println(item.toString());
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
                Item item = new Deadline(components[0], components[1]);
                this.list.add(item);
                System.out.println("Alright! I've added this task:");
                System.out.println(item.toString());
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
                Item item = new Event(components[0], components[1]);
                this.list.add(item);
                System.out.println("Alright! I've added this task:");
                System.out.println(item.toString());
                System.out.println(numItems == 1 ? "Now there is 1 task in your list."
                        : "Now there are " + numItems + " tasks in your list.");
            }
        }
    }

    private void listItems() {
        if (numItems == 0) {
            System.out.println("Currently, you have no tasks :)");
        } else {
            System.out.println("Here are the tasks in your list:");
            int i = 1;
            for (Item item : this.list) {
                System.out.println(i + ". " + item.toString());
                i++;
            }
        }
    }

    private void mark(String index) {
        if(index.isBlank()) {
            System.out.println("Oops! Please use the following format:");
            System.out.println("mark [task number]");
        } else if (numItems == 0) {
            System.out.println("Oops! Currently, you have no tasks :)");
        } else {
            try {
                int i = Integer.parseInt(index.substring(1));
                if (i <= 0 || i > numItems) {
                    System.out.println("Oops! Please enter a number between 1 and " + numItems + "!");
                } else {
                    Item item = this.list.get(i - 1);
                    item.mark();
                    System.out.println("Alright! I've marked this task as done:");
                    System.out.println(item.toString());
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Oops! Please use the following format:");
                System.out.println("mark [task number]");
            }
        }
    }

    private void unmark(String index) {
        if(index.isBlank()) {
            System.out.println("Oops! Please use the following format:");
            System.out.println("unmark [task number]");
        } else if (numItems == 0) {
            System.out.println("Oops! Currently, you have no tasks :)");
        } else {
            try {
                int i = Integer.parseInt(index.substring(1));
                if (i <= 0 || i > numItems) {
                    System.out.println("Oops! Please enter a number between 1 and " + numItems + "!");
                } else {
                    Item item = this.list.get(i - 1);
                    item.unmark();
                    System.out.println("Alright! I've marked this task as not done yet:");
                    System.out.println(item.toString());
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Oops! Please use the following format:");
                System.out.println("unmark [task number]");
            }
        }
    }

    private void delete(String index) {
        if(index.isBlank()) {
            System.out.println("Oops! Please use the following format:");
            System.out.println("delete [task number]");
        } else if (numItems == 0) {
            System.out.println("Oops! Currently, you have no tasks :)");
        } else {
            try {
                int i = Integer.parseInt(index.substring(1));
                if (i <= 0 || i > numItems) {
                    System.out.println("Oops! Please enter a number between 1 and " + numItems + "!");
                } else {
                    Item item = this.list.get(i - 1);
                    this.list.remove(i - 1);
                    this.numItems--;
                    System.out.println("Alright! I've removed this task:");
                    System.out.println(item.toString());
                    System.out.println("Now you have " + this.numItems + " tasks in your list.");
                }
            } catch (NumberFormatException nfe) {
                System.out.println("Oops! Please use the following format:");
                System.out.println("delete [task number]");
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
            this.mark(input.substring(4));
        } else if(input.length() < 6) {
            this.addItem(input);
        } else if(input.substring(0,6).toLowerCase().equals("unmark")) {
            this.unmark(input.substring(6));
        } else if(input.substring(0,6).toLowerCase().equals("delete")) {
            this.delete(input.substring(6));
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