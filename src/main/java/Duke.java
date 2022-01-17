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
                    System.out.println(indentation + tempNum + ". "  + listOfThings[i].getStatus() + " " + listOfThings[i].getDescription());
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
                System.out.println(indentation + "  " + listOfThings[num-1].getStatus() + " " + listOfThings[num-1].getDescription());
                System.out.println(indentation + line);
            } else if (str.contains("mark")) {
                int num = Integer.parseInt(str.split(" ")[1]);
                listOfThings[num-1].markDone();
                System.out.println(indentation + line);
                System.out.println(indentation + "Nice! I've marked this task as done:") ;
                System.out.println(indentation + "  " + listOfThings[num-1].getStatus() + " " + listOfThings[num-1].getDescription());
                System.out.println(indentation + line);
            } else{
                System.out.println(indentation + line);
                System.out.println(indentation + "added: " + str);
                System.out.println(indentation + line);
                listOfThings[counter] = new Task(str);
                counter++;
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
