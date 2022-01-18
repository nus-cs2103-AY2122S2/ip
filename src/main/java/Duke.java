import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        //Task[] listOfThings = new Task[100];
        ArrayList<Task> listOfThings = new ArrayList<>();

        String line = "____________________________________________________________";
        String indentation = "    ";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";


        System.out.println("Hello from\n" + logo);
        System.out.println(indentation + line);
        System.out.println(indentation + "Hello! I'm Duke\n" + indentation + "What can I do for you?");
        System.out.println(indentation + line);

        Scanner sc= new Scanner(System.in);
        String str = " ";

        while(true){
            str = sc.nextLine();
            if (str.equals("list")) {

                if (listOfThings.size() == 0) {
                    System.out.println(indentation + line);
                    System.out.println(indentation + "List is currently empty");
                    System.out.println(indentation + line);
                    continue;

                }
                System.out.println(indentation + line);
                int numbering = 1;
                for (Task t: listOfThings) {
                    System.out.println(indentation + String.valueOf(numbering) + ". "  + t.toString() + t.getStatus() + " " + t.getDescription());
                    numbering++;
                }
                System.out.println(indentation + line);
            }  else if(str.equals("bye")) {
                System.out.println(indentation + line);
                System.out.println(indentation + "Bye. Hope to see you again soon!");
                System.out.println(indentation + line);
                break;
            } else if (str.contains("unmark")) {
                try {
                    int num = Integer.parseInt(str.split(" ")[1]);
                    listOfThings.get(num - 1).unmarkDone();
                    System.out.println(indentation + line);
                    System.out.println(indentation + "OK, I've marked this task as not done yet:");
                    System.out.println(indentation + "  " + listOfThings.get(num - 1).toString() + listOfThings.get(num - 1).getStatus() + " " + listOfThings.get(num - 1).getDescription());
                    System.out.println(indentation + line);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indentation + line);
                    System.out.println(indentation + "No such element in the list. Try again");
                    System.out.println(indentation + line);
                }
            } else if (str.contains("mark")) {
                try {
                    int num = Integer.parseInt(str.split(" ")[1]);
                    listOfThings.get(num - 1).markDone();
                    System.out.println(indentation + line);
                    System.out.println(indentation + "Nice! I've marked this task as done:");
                    System.out.println(indentation + "  " + listOfThings.get(num - 1).toString() + listOfThings.get(num - 1).getStatus() + " " + listOfThings.get(num - 1).getDescription());
                    System.out.println(indentation + line);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indentation + line);
                    System.out.println(indentation + "No such element in the list. Try again");
                    System.out.println(indentation + line);
                }
            } else if (str.contains("delete")) {
                int num = Integer.parseInt(str.substring(7));
                System.out.println(indentation + line);
                System.out.println(indentation + "Noted. I've removed this task: ");
                Task task = listOfThings.get(num - 1);
                System.out.println(indentation + "  " + task.toString() + task.getStatus() +  " " + task.getDescription());
                listOfThings.remove(num - 1);
                System.out.println(indentation + "Now you have " + listOfThings.size() +" tasks in the list.");
                System.out.println(indentation + line);
            } else {
                    if (str.contains("todo")) {
                        try {
                            String newString = str.substring(5).trim();
                            if (newString.length() == 0) {
                                throw new StringIndexOutOfBoundsException();
                            }

                            ToDos newToDo = new ToDos(newString);
                            listOfThings.add(newToDo);
                            System.out.println(indentation + line);
                            System.out.println(indentation + "Got it. I've added this task:");
                            System.out.println(indentation + "  " + newToDo.toString() + newToDo.getStatus() + " " + newToDo.getDescription());
                            System.out.println(indentation + "Now you have " + String.valueOf(listOfThings.size()) + " tasks in the list.");
                            System.out.println(indentation + line);
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println(indentation + line);
                            System.out.println("      OOPS!!! The description of a todo cannot be empty.");
                            System.out.println(indentation + line);

                        }
                    } else if (str.contains("deadline")) {
                        try {
                            String des = str.substring(9, str.indexOf('/') - 1).trim();
                            String date = str.substring((str.indexOf('/') + 4)).trim();
                            Deadline newDeadline = new Deadline(des, date);
                            listOfThings.add(newDeadline);
                            System.out.println(indentation + line);
                            System.out.println(indentation + "Got it. I've added this task:");
                            System.out.println(indentation + "  " + newDeadline.toString() + newDeadline.getStatus() + " " + newDeadline.getDescription());
                            System.out.println(indentation + "Now you have " + String.valueOf(listOfThings.size()) + " tasks in the list.");
                            System.out.println(indentation + line);

                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println(indentation + line);
                            System.out.println("      OOPS!!! Incorrect format for entering deadlines");
                            System.out.println(indentation + line);

                        }
                    } else if (str.contains("event")) {
                        try {
                            String des = str.substring(6, str.indexOf('/') - 1).trim();
                            String date = str.substring((str.indexOf('/') + 4)).trim();
                            Event newEvent = new Event(des, date);
                            listOfThings.add(newEvent);
                            //listOfThings[counter] = new Event(des, date);
                            System.out.println(indentation + line);
                            System.out.println(indentation + "Got it. I've added this task:");
                            System.out.println(indentation + "  " + newEvent.toString() + newEvent.getStatus() + " " + newEvent.getDescription());
                            System.out.println(indentation + "Now you have " + String.valueOf(listOfThings.size()) + " tasks in the list.");
                            System.out.println(indentation + line);
                        } catch (StringIndexOutOfBoundsException e) {
                            System.out.println(indentation + line);
                            System.out.println("      OOPS!!! Incorrect format for entering events");
                            System.out.println(indentation + line);
                        }
                    } else {
                        System.out.println(indentation + line);
                        System.out.println(indentation + " OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(indentation + line);

                    }
            }
        }
    }
}

class Task {
    enum Type {
        E, T, D
    }

    protected String description;
    protected boolean status;

    public Task(String description) {
        this.description = description;
        status = false;
    }

    String getStatus() {
        return status ? "[X]" : "[ ]";
    }

    void markDone() {
        this.status = true;
    }

    void unmarkDone() {
        this.status = false;
    }

    String getDescription(){
        return this.description;
    }

}

class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + Type.T + "]";
    }

}

class Deadline extends Task {

    private String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + Type.D + "]";
    }

    public String getDate() {
        return this.date;
    }

    @Override
    String getDescription() {
        return this.description + " (by: " + date + ")";
    }
}

class Event extends Task {

    private String date;

    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String toString() {
        return "[" + Type.E + "]";
    }
    public String getDate() {
        return this.date;
    }

    @Override
    String getDescription() {
        return this.description + " (at: " + date + ")";
    }
}