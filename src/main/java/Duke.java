import java.util.Date;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] listOfThings = new Task[100];
        int counter = 0;

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
                System.out.println(indentation + line);

                for (int i = 0; i <  counter; i++) {
                    String tempNum = String.valueOf(i+1);
                    System.out.println(indentation + tempNum + ". "  + listOfThings[i].toString() + listOfThings[i].getStatus() + " " + listOfThings[i].getDescription());
                }
                System.out.println(indentation + line);
            }  else if(str.equals("bye")) {
                System.out.println(indentation + line);
                System.out.println(indentation + "Bye. Hope to see you again soon!");
                System.out.println(indentation + line);
                break;
            } else if (str.contains("unmark")) {
                int num = Integer.parseInt(str.split(" ")[1]);
                listOfThings[num-1].unmarkDone();
                System.out.println(indentation + line);
                System.out.println(indentation + "OK, I've marked this task as not done yet:") ;
                System.out.println(indentation + "  " + listOfThings[num-1].toString() + listOfThings[num-1].getStatus() + " " + listOfThings[num-1].getDescription());
                System.out.println(indentation + line);
            } else if (str.contains("mark")) {
                int num = Integer.parseInt(str.split(" ")[1]);
                listOfThings[num-1].markDone();
                System.out.println(indentation + line);
                System.out.println(indentation + "Nice! I've marked this task as done:") ;
                System.out.println(indentation + "  " + listOfThings[num-1].toString() + listOfThings[num-1].getStatus() + " " + listOfThings[num-1].getDescription());
                System.out.println(indentation + line);
            } else {

                if (str.contains("todo")) {
                    String newString = str.substring(5).trim();
                    listOfThings[counter] = new ToDos(newString);
                    System.out.println(indentation + line);
                    System.out.println(indentation + "Got it. I've added this task:");
                    System.out.println(indentation + "  " + listOfThings[counter].toString() + listOfThings[counter].getStatus() + " " + listOfThings[counter].getDescription());
                    System.out.println(indentation + "Now you have " + String.valueOf(counter + 1) + " tasks in the list.");
                    System.out.println(indentation + line);
                    counter++;

                } else if (str.contains("deadline")) {
                    String des = str.substring(9, str.indexOf('/')-1).trim();
                    String date = str.substring((str.indexOf('/')+1)).trim();
                    listOfThings[counter] = new Deadline(des,date);
                    System.out.println(indentation + line);
                    System.out.println(indentation + "Got it. I've added this task:");
                    System.out.println(indentation + "  " + listOfThings[counter].toString() + listOfThings[counter].getStatus() + " " + listOfThings[counter].getDescription());
                    System.out.println(indentation + "Now you have " + String.valueOf(counter + 1)+ " tasks in the list.");
                    System.out.println(indentation + line);
                    counter++;
                } else if (str.contains("event")) {
                    String des = str.substring(6, str.indexOf('/')-1).trim();
                    String date = str.substring((str.indexOf('/')+1)).trim();
                    listOfThings[counter] = new Event(des,date);
                    System.out.println(indentation + line);
                    System.out.println(indentation + "Got it. I've added this task:");
                    System.out.println(indentation + "  " + listOfThings[counter].toString() + listOfThings[counter].getStatus() + " " + listOfThings[counter].getDescription());
                    System.out.println(indentation + "Now you have " + String.valueOf(counter + 1) + " tasks in the list.");
                    System.out.println(indentation + line);
                    counter++;
                } else {
                    System.out.println("Unknown parameters, Try again");
                }
            }
        }
    }
}

class Task {

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
        return "[T]";
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
        return "[D]";
    }

    public String getDate() {
        return this.date;
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
        return "[E]";
    }

    public String getDate() {
        return this.date;
    }
}

