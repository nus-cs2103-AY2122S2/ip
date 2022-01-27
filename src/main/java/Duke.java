import java.util.*;
import java.lang.*;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors


public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);

        List list = new ArrayList<Task>();

        while(sc.hasNext()) {
            String value = sc.nextLine();
            String[] splitStr = value.split("\\s+");

            if (value.equals("bye")){
                System.out.println(" Bye. Hope to see you again soon!");
                break;
            }
            else if (value.equals("list")) {
                for (int i=0; i<list.size(); i++) {
                    System.out.println(i+1 + ". " + list.get(i));
                }
            }
            else if (splitStr[0].equals("mark")){
                int index = Integer.parseInt(splitStr[1]);
                Task task = (Task) list.get(index-1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(list.get(index-1));
            }
            else if (splitStr[0].equals("unmark")){
                int index = Integer.parseInt(splitStr[1]);
                Task task = (Task) list.get(index-1);
                task.unmarkAsDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(list.get(index-1));
            }
            else if (splitStr[0].equals("delete")){
                int index = Integer.parseInt(splitStr[1]);
                Task task = (Task) list.get(index-1);
                list.remove(index-1);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(task);
                System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
            }


            else if (splitStr[0].equals("todo") || splitStr[0].equals("deadline") || splitStr[0].equals("event")){
                String[] parts = value.split("/");
                String description = parts[0];
                if (parts.length>1){ description+= "("+parts[1]+")";}

                try {
                    if (splitStr[0].equals("todo")) {
                        description = description.substring(5);
                        list.add(new ToDo(description));
                    }
                }
                catch (Exception e){
                    System.out.println(" ☹ OOPS!!! The description of a todo cannot be empty.");
                }
                try {
                    if (splitStr[0].equals("deadline")) {
                        description = description.substring(9);
                        list.add(new Deadline(description));
                    }
                }
                catch (Exception e){
                    System.out.println(" ☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                try {
                    if (splitStr[0].equals("event")) {
                        description = description.substring(6);
                        list.add(new Event(description));
                    }
                }
                catch (Exception e){
                    System.out.println(" ☹ OOPS!!! The description of a event cannot be empty.");
                }

                System.out.println("Got it. I've added this task: ");
                System.out.println(list.get(list.size()-1));
                System.out.println(String.format("Now you have %d tasks in the list.", list.size()));
            }

            else {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");


            }

            try {
                FileWriter myWriter = new FileWriter("duke.txt");
                for (int i=0; i<list.size(); i++) {
                    myWriter.write(i+1 + ". " + list.get(i));
                    myWriter.write(String.format("%n"));

                }
                myWriter.close();
                //System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }


        }







    }
}


